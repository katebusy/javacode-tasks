create schema library;

create table library.authors (
    id          uuid default random_uuid(),
    full_name   text not null,
    primary key (id)
);

create table library.books (
    id          uuid default random_uuid(),
    title       text not null,
    author_id   uuid references library.authors(id) on delete cascade,
    primary key (id)
);

insert into library.authors values ('f17918e2-e18a-11ef-9bd3-0242ac180002','А.С. Пушкин');
insert into library.authors values ('002f15a8-e492-11ef-be5d-0242ac140002','А.П. Чехов');

insert into library.books values
('a903945c-43a3-4cdb-b741-104645b5e441','Руслан и Людмила', 'f17918e2-e18a-11ef-9bd3-0242ac180002');
insert into library.books values
('901d8a68-0777-419b-8b2d-bff353e34438','Капитанская дочка', 'f17918e2-e18a-11ef-9bd3-0242ac180002');
insert into library.books values
('035b00d4-c797-4b5a-8638-25dc8f4263cf','Палата № 6', '002f15a8-e492-11ef-be5d-0242ac140002');
insert into library.books values
('d2a4196e-8e07-4b40-ba85-f99c2f13dc1a','Каштанка', '002f15a8-e492-11ef-be5d-0242ac140002');