package com.vasaal.crm.services;

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class OrderService {
    private Connection connectionBdd;

    public void createConnection() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crm",
                    "root",
                    "");
            if (connection != null) {
                System.out.println("Connexion à la base de donnée réussi !");
            }
            this.connectionBdd = connection;

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnectionBdd() {
        return connectionBdd;
    }

    

}