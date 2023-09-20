# 寿司オーダーシステム

ゆめみサーバーサイドインターンシップ2023の課題

## Requirements

- Go 1.18以上(for sql-migrate)
- Docker

## SetUp

1: envファイルを作成する

```bash
cp .env.example .env
```

2: DBを起動する

```bash
make up
```

3: DBにテーブルを作成する

```bash
make migrate-up
```

4: アプリケーションサーバーを起動する

5: OpenAPIを確認する

http://localhost:8080/swagger-ui/index.html

## テーブルの追加・変更

1: Migration Fileを作成する

```bash
make create-migration name={migration_name}
```

2: 生成されたMigration Fileを編集する

3: Run Migration

```bash
make migrate-up
```

## ER図

```mermaid
erDiagram

tables {
    string id
    string name
}

customers {
    string id
    string table_id
    datetime checked_in_at
    datetime checked_out_at
}

categories {
    string id
    string name
}

items {
    string id
    string name
    string category_id
    int price
    bool available
}

available_options {
    string item_id
    string item_option_id
}

item_options {
    string id
    string name
}

order_items {
    string id
    string customer_id
    string item_id
    int    price
    datetime ordered_at
    datetime delivered_at
	datetime cancelled_at
}

order_item_options {
    string order_item_id
    string item_option_id
}

    tables ||--o{ customers: ""
    categories ||--o{ items: ""
    customers ||--o{ order_items: ""
    items ||--o{ available_options: ""
    item_options ||--o{ available_options: ""
    order_items ||--o{ order_item_options: ""
    items ||--o{ order_items: ""
    item_options ||--o{ order_item_options: ""
```
