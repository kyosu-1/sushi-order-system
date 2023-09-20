-- tablesテーブル
INSERT INTO
    tables (id, name)
VALUES
    ('a5581beb-3662-4564-9d1c-3cfbccb4ecd1', 'Table 1'),
    ('b5581beb-3662-4564-9d1c-3cfbccb4ecd2', 'Table 2');

-- categoriesテーブル
INSERT INTO
    categories (id, name)
VALUES
    ('c5581beb-3662-4564-9d1c-3cfbccb4ecd1', '定番にぎり'),
    ('d5581beb-3662-4564-9d1c-3cfbccb4ecd2', '特選にぎり');

-- itemsテーブル
INSERT INTO
    items (id, name, category_id, price, available)
VALUES
    ('e5581beb-3662-4564-9d1c-3cfbccb4ecd1', 'サーモン', 'c5581beb-3662-4564-9d1c-3cfbccb4ecd1', 150, true),
    ('f5581beb-3662-4564-9d1c-3cfbccb4ecd2', 'まぐろ', 'c5581beb-3662-4564-9d1c-3cfbccb4ecd1', 200, true);

-- item_options
INSERT INTO
    item_options (id, name)
VALUES
    ('g5581beb-3662-4564-9d1c-3cfbccb4ecd1', 'しゃり大'),
    ('h5581beb-3662-4564-9d1c-3cfbccb4ecd2', 'のり増し');

-- available_options
INSERT INTO
    available_options (item_id, item_option_id)
VALUES
    ('e5581beb-3662-4564-9d1c-3cfbccb4ecd1', 'g5581beb-3662-4564-9d1c-3cfbccb4ecd1'),
    ('f5581beb-3662-4564-9d1c-3cfbccb4ecd2', 'h5581beb-3662-4564-9d1c-3cfbccb4ecd2');

-- customersテーブル
INSERT INTO
    customers (id, table_id, checked_in_at, checked_out_at)
VALUES
    ('i5581beb-3662-4564-9d1c-3cfbccb4ecd1', 'a5581beb-3662-4564-9d1c-3cfbccb4ecd1', '2021-09-14 14:22:17', NULL),
    ('j5581beb-3662-4564-9d1c-3cfbccb4ecd2', 'b5581beb-3662-4564-9d1c-3cfbccb4ecd2', '2021-09-14 14:22:17', '2021-09-14 14:42:17')
