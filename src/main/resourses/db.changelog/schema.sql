create schema shop;

create table shop.products (
    id              uuid default random_uuid(),
    name            text not null,
    description     text,
    price           numeric not null check (price > 0),
    quantity        numeric not null check (quantity >= 0),
    primary key (id)
);

create table shop.customers (
    id          uuid default random_uuid(),
    first_name  text not null,
    last_name   text not null,
    email       text not null,
    number      text not null,
    primary key (id)
);

create table shop.orders (
    id          uuid default random_uuid(),
    order_date  timestamp default current_timestamp,
    address     text not null,
    total       numeric not null,
    status      text not null,
    customer_id uuid references shop.customers(id) on delete cascade,
    primary key (id)
);

create table shop.order_products (
    order_id    uuid,
    product_id  uuid,
    quantity    int not null,
    primary key (order_id, product_id),
    foreign key (order_id) references shop.orders(id) on delete cascade,
    foreign key (product_id) references shop.products(id) on delete cascade
);

--insert into shop.products values
--('a903945c-43a3-4cdb-b741-104645b5e441', 'f17918e2-e18a-11ef-9bd3-0242ac180002');
--insert into shop.products values
--('901d8a68-0777-419b-8b2d-bff353e34438', 'f17918e2-e18a-11ef-9bd3-0242ac180002');
--insert into shop.orders values
--('035b00d4-c797-4b5a-8638-25dc8f4263cf', '002f15a8-e492-11ef-be5d-0242ac140002');
--insert into shop.customers values
--('d2a4196e-8e07-4b40-ba85-f99c2f13dc1a', '002f15a8-e492-11ef-be5d-0242ac140002');