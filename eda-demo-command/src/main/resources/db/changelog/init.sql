create extension if not exists pgcrypto;
create table events(id uuid, aggregate_id uuid, type varchar, payload jsonb, timestamp timestamp default current_timestamp, version bigint, PRIMARY KEY(id));