INSERT INTO sc_ecommerce_item.discount (id, name, description, active, value, value_type)
VALUES (1, '10%', 'Discount 10%', true, 10, 'PERCENT'),
       (2, '5€', 'Five euros off', true, 5,'ABSOLUTE');

INSERT INTO sc_ecommerce_item.discounted_item (id, item_id, discount_id)
VALUES (1, 1, 1),
       (2, 2, 2);

BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE sc_ecommerce_item.discount IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval((select pg_get_serial_sequence('sc_ecommerce_item.discount', 'id')), COALESCE((SELECT MAX(id)+1 FROM  sc_ecommerce_item.discount), 1), false);
COMMIT;

BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE sc_ecommerce_item.discounted_item IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval((select pg_get_serial_sequence('sc_ecommerce_item.discounted_item', 'id')), COALESCE((SELECT MAX(id)+1 FROM  sc_ecommerce_item.discounted_item), 1), false);
COMMIT;