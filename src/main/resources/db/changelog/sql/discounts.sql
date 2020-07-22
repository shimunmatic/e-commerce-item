INSERT INTO sc_ecommerce_item.discount (id, name, description, active, value, value_type)
VALUES (1, '10%', 'Discount 10%', true, 10, 'PERCENT'),
       (2, '5€', 'Five euros off', true, 5,'ABSOLUTE');

INSERT INTO sc_ecommerce_item.discounted_item (id, item_id, discount_id)
VALUES (1, 1, 1),
       (2, 2, 2);

