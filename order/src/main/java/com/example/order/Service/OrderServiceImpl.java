package com.example.order.Service;

import com.example.order.Model.OrderDao;
import com.example.order.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public void saveOrderDao(OrderDao orderDao){ orderRepository.save(orderDao); }

    public void saveAllOrderDao(List<OrderDao> listOrderDaos){ orderRepository.saveAll(listOrderDaos); }
}
