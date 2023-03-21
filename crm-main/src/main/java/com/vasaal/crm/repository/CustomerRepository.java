package com.vasaal.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasaal.crm.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}