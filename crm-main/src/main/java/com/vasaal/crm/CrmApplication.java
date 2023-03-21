package com.vasaal.crm;

import com.vasaal.crm.entities.Customer;
import com.vasaal.crm.entities.Order;
import com.vasaal.crm.entities.Product;
import com.vasaal.crm.repository.CustomerRepository;
import com.vasaal.crm.repository.OrderRepository;
import com.vasaal.crm.repository.ProductRepository;
import com.vasaal.crm.services.OrderService;

import java.math.BigDecimal;
import java.sql.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
		OrderService orderService = new OrderService();
		orderService.createConnection();
		Connection connection = orderService.getConnectionBdd();
		try {
			Statement st = connection.createStatement();
			// ResultSet rs = st.executeQuery("SELECT * FROM `customer` ORDER BY
			// `customer`.`id` ASC;");
			// rs.next();
			// System.out.print("id: " + rs.getInt("id") + ", ");
			// System.out.print("surname: " + rs.getString("surname") + ", ");
			// System.out.print("name: " + rs.getString("name") + ", ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
