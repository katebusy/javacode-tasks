create schema shop;

create table shop.users (
    id         uuid default random_uuid(),
    name       text not null,
    email      text not null,
    primary key (id)
);

create table shop.orders (
    id            uuid default random_uuid(),
    status        text,
    order_sum     numeric,
    user_id       uuid references shop.users(id) on delete cascade,
    products      text,
    primary key (id)
);

insert into shop.users values
('002f15a8-e492-11ef-be5d-0242ac140002', 'Ivan', 'example@email.com');
insert into shop.users values
('c9f0df5e-9ac5-47b3-ab56-4dbaa869a070','Boris', 'example@email.com');
insert into shop.users values
('901d8a68-0777-419b-8b2d-bff353e34438', 'Gleb', 'example@email.com');

insert into shop.orders values
('f17918e2-e18a-11ef-9bd3-0242ac180002', 'In progress', 1000, '002f15a8-e492-11ef-be5d-0242ac140002', 'молоко, мука');
insert into shop.orders values
('a903945c-43a3-4cdb-b741-104645b5e441', 'In progress', 1010, '002f15a8-e492-11ef-be5d-0242ac140002', 'хлеб, сыр');
insert into shop.orders values
('035b00d4-c797-4b5a-8638-25dc8f4263cf', 'In progress', 2000, 'c9f0df5e-9ac5-47b3-ab56-4dbaa869a070', 'яйца, мука');
insert into shop.orders values
('d2a4196e-8e07-4b40-ba85-f99c2f13dc1a', 'In progress', 4000, '901d8a68-0777-419b-8b2d-bff353e34438', 'сковордка');