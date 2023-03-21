package com.vasaal.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasaal.crm.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}