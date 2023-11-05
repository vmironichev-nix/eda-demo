create extension if not exists pgcrypto;
create table orders(id uuid, modified_at timestamp default current_timestamp, status varchar, PRIMARY KEY(id));
create table orders_items(id uuid, order_id uuid, name varchar, quantity int, price_id uuid, modified_at timestamp default current_timestamp, PRIMARY KEY(id));