package com.example.pos.api.stock.Repository;

import com.example.pos.api.stock.Model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
    ArrayList<Stock> findAll();
    Optional<Stock> findByName(String name);

}