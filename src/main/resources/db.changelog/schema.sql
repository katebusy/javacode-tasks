create schema security;

create table security.users (
    id              uuid default random_uuid(),
    name            text not null,
    email           text not null,
    role            text not null,
    primary key(id)
);