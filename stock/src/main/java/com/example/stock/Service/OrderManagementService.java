package com.example.stock.Service;

import com.example.stock.Producer.KafkaProducer;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

public class OrderManagementService {
    private final StockService stockService;
    private final KafkaProducer kafkaProducer;

    public OrderManagementService(StockService stockService, KafkaProducer kafkaProducer) {
        this.stockService = stockService;
        this.kafkaProducer = kafkaProducer;
    }

    public Order processStockRequest(Order order) {
        if (order.getStockStatus().equalsIgnoreCase(OrderStatus.NEW)) {
            return newOrder(order);
        } else if (order.getStockStatus().equalsIgnoreCase(OrderStatus.ROLLBACK)) {
            return rollbackOrder(order);
        }
        return order;

    }

    private Order rollbackOrder(Order order) {
        log.debug("rollback stock request {} ", order.getOrderNumber());
        List<Item> rollbackStockItems = order.getItems();
        List<StockItem> currentStockItems = stockService.findByItemNumbers(
                rollbackStockItems.stream().map(Item::getItemNumber).collect(Collectors.toList()),
                true);
        List<StockItem> stockItemsUpdated = rollbackStock(rollbackStockItems, currentStockItems);
        if (currentStockItems.isEmpty()) {
            log.error("one of the items wasn't found on stock rollback{}", order);
            throw new StockException("one of the items wasn't found on stock rollback" + order);
        }

        return order;
    }

    private Order newOrder(Order order) {
        log.debug("new stock request {} ", order.getOrderNumber());
        List<Item> newStockItems = new ArrayList<>(order.getItems());

        //check if all the items received are truly present in the DB
        List<StockItem> currentStockItems = stockService.findByItemNumbers(
                newStockItems.stream().map(Item::getItemNumber).collect(Collectors.toList()),
                true);
        if (!currentStockItems.isEmpty()) {
            //if all items received are present,
            //check if quantity of received stock items is bigger then current stock
            List<StockItem> stockItemsUpdated = reserveStock(newStockItems, currentStockItems);
            if (stockItemsUpdated.isEmpty()) {
                log.debug("stock reserve failed for order {} " +
                        "(quantity requested for one of the items exceeds stock)", order);
                order.setStockStatus(OrderStatus.FAILED);
                order.setStockStatusReason("Quantity for one of the items ordered exceed quantity in stock");
            } else {
                stockService.saveAll(stockItemsUpdated);
                order.setStockStatus(OrderStatus.SUCCESS);
            }
        } else {
            order.setStockStatus(OrderStatus.FAILED);
            log.debug("one of the items wasn't found on stock {}", order);
            order.setStockStatusReason("One of the items wasn't found on stock");
        }
        kafkaProducer.send(order.getOrderNumber(), order);
        log.debug("new order result {} ", order);
        return order;
    }

    private List<StockItem> changeStockQuantity(List<Item> itemsReceived, List<StockItem> currentStockItems, boolean newOrder) {
        itemsReceived = new ArrayList<>(itemsReceived);
        itemsReceived.sort(Comparator.comparing(Item::getItemNumber));
        currentStockItems.sort(Comparator.comparing(StockItem::getItemNumber));
        List<StockItem> stockUpdated = List.copyOf(currentStockItems);

        for (int i = 0; i < itemsReceived.size(); i++) {
            //decrease quantity for new order
            if (newOrder) {
                if (itemsReceived.get(i).getQuantity() > currentStockItems.get(i).getQuantity()) {
                    return List.of();
                }
                stockUpdated.get(i).setQuantity(currentStockItems.get(i).getQuantity() - itemsReceived.get(i).getQuantity());
                //increase quantity for rollback
            } else {
                stockUpdated.get(i).setQuantity(currentStockItems.get(i).getQuantity() + itemsReceived.get(i).getQuantity());
            }
        }
        return stockUpdated;
    }

    private List<StockItem> reserveStock(List<Item> itemsReceived, List<StockItem> currentStockItems) {
        return changeStockQuantity(itemsReceived, currentStockItems, true);
    }

    private List<StockItem> rollbackStock(List<Item> itemsReceived, List<StockItem> currentStockItems) {
        List<StockItem> stockItemsUpdated = changeStockQuantity(itemsReceived, currentStockItems, false);
        return stockService.saveAll(stockItemsUpdated);
    }

}
