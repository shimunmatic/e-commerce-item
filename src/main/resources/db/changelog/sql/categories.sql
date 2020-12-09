INSERT INTO sc_ecommerce_item.category (id, name, description, parent_id)
VALUES (1, 'Automotive', 'All for your four wheel friend', NULL),
       (2, 'Books', 'Brain food', NULL),
       (3, 'Electronics', 'Electronics', NULL),
       (4, 'Computers', 'Computers', 3),
       (5, 'Fashion', 'Clothes', NULL),
       (6, 'Home', 'Furniture', NULL);
BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE sc_ecommerce_item.category IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval((select pg_get_serial_sequence('sc_ecommerce_item.category', 'id')), COALESCE((SELECT MAX(id)+1 FROM  sc_ecommerce_item.category), 1), false);
COMMIT;