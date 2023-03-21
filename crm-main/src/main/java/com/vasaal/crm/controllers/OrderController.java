package com.vasaal.crm.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vasaal.crm.entities.Customer;
import com.vasaal.crm.entities.Order;
import com.vasaal.crm.entities.Product;

import com.vasaal.crm.repository.CustomerRepository;
import com.vasaal.crm.repository.OrderRepository;
import com.vasaal.crm.repository.ProductRepository;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    // LISTER COMMANDE
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    // AJOUTER COMMANDE
    @PostMapping(path = "/add")
    public @ResponseBody String addNewOrder(@RequestParam Map<Long, Integer> ordoredProducts,
            @RequestParam long customerid) {
        Order order = new Order();
        for (Map.Entry<Long, Integer> entry : ordoredProducts.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            Product product = productRepository.findById(productId).orElseThrow();
            order.addItem(product, quantity);
        }
        Customer customer = customerRepository.findById(customerid).orElseThrow();
        order.setCustomer(customer);
        return "Order added";
    }

}
