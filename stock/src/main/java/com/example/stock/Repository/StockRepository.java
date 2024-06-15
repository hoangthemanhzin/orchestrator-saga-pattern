package com.example.stock.Repository;

import com.example.stock.Model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockItem, String> {
    List<StockItem>findByItemNumberIn(List<String> itemNumbers);
}
