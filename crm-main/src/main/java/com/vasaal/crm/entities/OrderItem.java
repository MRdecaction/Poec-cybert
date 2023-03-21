package com.vasaal.crm.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orderItem")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String name;

    private BigDecimal price;

    private int quantity;

    @Transient
    private Product product;

    public OrderItem(Order order, Product product) {
        this.product = product; // Tempory relation for count on save.
        this.name = product.getName();
        this.price = product.getPrice();
        this.order = order;
    }

    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(quantity));
    }

}