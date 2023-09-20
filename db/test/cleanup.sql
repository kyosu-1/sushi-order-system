-- delete all data

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE order_item_options;

TRUNCATE TABLE order_items;

TRUNCATE TABLE available_options;

TRUNCATE TABLE item_options;

TRUNCATE TABLE items;

TRUNCATE TABLE categories;

TRUNCATE TABLE customers;

TRUNCATE TABLE tables;

SET FOREIGN_KEY_CHECKS = 1;
