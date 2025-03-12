create schema library;

create table library.books (
    id                 uuid default random_uuid(),
    title              text not null,
    author             text not null,
    publication_year   int not null,
    primary key(id)
);

insert into library.books values ('d2a4196e-8e07-4b40-ba85-f99c2f13dc1a','Каштанка', 'А.П. Чехов', 1887)