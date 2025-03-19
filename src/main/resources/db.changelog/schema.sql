create schema security;

create table security.users (
    id              uuid default random_uuid(),
    username        text not null,
    password        text not null,
    role            text not null,
    primary key(id)
);