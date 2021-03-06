INSERT INTO sc_ecommerce_item.item_variant (id, name, description, item_id, base_price)
VALUES (1, 'PaperBack - Harry Potter 1', 'First Harry Potter - paperback', 3, 8.99),
       (2, 'HardBack - Harry Potter 1', 'First Harry Potter - hardback', 3, 10.99),
       (3, 'Black - Samsung Galaxy Buds', 'Wireless headphones - Black', 5, 59.99),
       (4, 'Gold - Samsung Galaxy Buds', 'Wireless headphones - Gold', 5, 59.99);

BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE sc_ecommerce_item.item_variant IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval((select pg_get_serial_sequence('sc_ecommerce_item.item_variant', 'id')), COALESCE((SELECT MAX(id)+1 FROM  sc_ecommerce_item.item_variant), 1), false);
COMMIT;
