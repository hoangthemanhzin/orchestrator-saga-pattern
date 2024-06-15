package com.example.stock.Service;

import com.example.stock.Model.StockItem;
import com.example.stock.Repository.StockRepository;

import java.util.List;

public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockItem> findAll() {
        return stockRepository.findAll();
    }
    public <S extends StockItem> S save(S entity) {
        return stockRepository.save(entity);
    }

    public <S extends StockItem> List<S> saveAll(Iterable<S> entities) {
        return stockRepository.saveAll(entities);
    }

    public long count() {
        return stockRepository.count();
    }

    public List<StockItem> findByItemNumbers(List<String> itemNumbers, boolean matchAll) {
        if (itemNumbers.isEmpty()) {
            log.error("no item number passed");
            throw new StockException("no Item number passed");
        }
        List<StockItem> itemList = stockRepository.findByItemNumberIn(itemNumbers);
        if (!matchAll) return itemList;

        if (itemList.size()==itemNumbers.size()) return itemList;
        log.debug("no StockItem found for itemBumbers {}", itemNumbers);
        return List.of();
    }

}
