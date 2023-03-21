package com.vasaal.crm.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orderProduct")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime orderedAt = LocalDateTime.now();

    private BigDecimal totalPrice = new BigDecimal("0.0");

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order addItem(Product product, int quantity) {
        final OrderItem item = new OrderItem(this, product);
        item.setQuantity(quantity);
        this.items.add(item);
        this.refreshTotalPrice();

        return this;
    }

    public void refreshTotalPrice() {
        BigDecimal totalPriceTmp = new BigDecimal("0.0");
        for (final OrderItem orderItem : this.items) {
            totalPriceTmp = totalPriceTmp.add(orderItem.getTotalPrice());
        }
        this.totalPrice = totalPriceTmp;
    }
}