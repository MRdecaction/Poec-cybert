package com.vasaal.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vasaal.crm.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // @Query(value = "SELECT * FROM Product ORDER")
    // List<Product> findTop10ByOrderByLevelDesc();
}