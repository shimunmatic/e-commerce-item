INSERT INTO sc_ecommerce_item.item (id, name, description, category_id, base_price)
VALUES (1, 'Alloy cleaner', 'Clean your alloys in a heartbeat', 1, 17.99),
       (2, 'Windscreen wipers - set of 2', 'Windscreen wipers - fits VW Golf, Audi A3', 1, 9.99),
       (3, 'Harry Potter 1', 'First Harry Potter', 2, 8.99),
       (4, 'Harry Potter 2', 'Second Harry Potter', 2, 8.99),
       (5, 'Samsung Galaxy Buds', 'Wireless headphones', 3, 59.99),
       (6, 'HP Omen Obelisk PC', 'Intel i7, 16GB RAM, 512 GB .M2 SSD', 4, 959.99),
       (7, 'Polo T-Shirt', 'Basic Polo T-Shirt', 5, 4.99),
       (8, 'Kitchen Table', 'Kitchen table 210x150x80', 6, 59.99);

BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE sc_ecommerce_item.item IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval((select pg_get_serial_sequence('sc_ecommerce_item.item', 'id')), COALESCE((SELECT MAX(id)+1 FROM  sc_ecommerce_item.item), 1), false);
COMMIT;