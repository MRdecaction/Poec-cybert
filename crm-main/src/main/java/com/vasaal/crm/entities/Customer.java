package com.vasaal.crm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String firstname;

    @Column(unique = true, nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String phoneNumber;


    @OneToMany(mappedBy = "customer")
    private final List<Order> orders = new ArrayList<>();



    public String fullname() {
        return String.format("%s %s", this.firstname, this.lastname.toUpperCase());
    }



    public static int size() {
        return 0;
    }

}