INSERT INTO customer (id, firstname, lastname, address, email, phone_number)
VALUES 
  (1, 'John', 'Doe', '123 Main St, Anytown USA', 'johndoe@example.com', '555-1234'),
  (2, 'Jane', 'Dogshit', '456 Elm St, Anytown USA', 'janedoe@example.com', '555-5678'),
  (3, 'Bob', 'Smith', '789 Oak St, Anytown USA', 'bobsmith@example.com', '555-9012'),
  (4, 'Alice', 'Johnson', '111 Pine St, Anytown USA', 'alicejohnson@example.com', '555-3456'),
  (5, 'David', 'Lee', '222 Cedar St, Anytown USA', 'davidlee@example.com', '555-7890');


INSERT INTO order_product (id, ordered_at, total_price, customer_id)
VALUES 
  (1, '2022-01-01 10:00:00', 100.00, 1),
  (2, '2022-01-02 12:00:00', 200.00, 2),
  (3, '2022-01-03 14:00:00', 150.00, 3),
  (4, '2022-01-04 16:00:00', 120.00, 4),
  (5, '2022-01-05 18:00:00', 80.00, 5);

INSERT INTO product (id, name, surface, hex_color, price)
VALUES 
  (1, 'Produit 1', 'Bois', '#ff0000', 10.99),
  (2, 'Produit 2', 'Métal', '#00ff00', 19.99),
  (3, 'Produit 3', 'Verre', '#0000ff', 15.49),
  (4, 'Produit 4', 'Céramique', '#ffff00', 8.99),
  (5, 'Produit 5', 'Plastique', '#ff00ff', 12.99);




INSERT INTO order_item (id, name, price, quantity, order_id)
VALUES 
  (1, 'Produit 1', 10.99, 2, 1),
  (2, 'Produit 2', 19.99, 1, 1),
  (3, 'Produit 3', 15.49, 3, 2),
  (4, 'Produit 4', 8.99, 4, 3),
  (5, 'Produit 5', 12.99, 2, 4);
