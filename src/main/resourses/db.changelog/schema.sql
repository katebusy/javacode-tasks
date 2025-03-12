create schema office;

create table office.departments (
    id              uuid default random_uuid(),
    name            text not null,
    primary key(id)
);

create table office.employee (
    id              uuid default random_uuid(),
    first_name      text not null,
    last_name       text not null,
    job_title       text not null,
    salary          numeric not null,
    department_id   uuid references office.departments(id) on delete cascade,
    primary key(id)
);

insert into office.departments values ('d2a4196e-8e07-4b40-ba85-f99c2f13dc1a', 'HR');
insert into office.departments values ('f17918e2-e18a-11ef-9bd3-0242ac180002', 'Marketing');

insert into office.employee values
('002f15a8-e492-11ef-be5d-0242ac140002', 'Ivan', 'Ivanov', 'position1', 95000, 'd2a4196e-8e07-4b40-ba85-f99c2f13dc1a');
insert into office.employee values
('a903945c-43a3-4cdb-b741-104645b5e441', 'Boris', 'Borisov', 'position2', 100000, 'd2a4196e-8e07-4b40-ba85-f99c2f13dc1a');
insert into office.employee values
('901d8a68-0777-419b-8b2d-bff353e34438', 'Gleb', 'Glebov', 'position3', 95000, 'f17918e2-e18a-11ef-9bd3-0242ac180002');
insert into office.employee values
('035b00d4-c797-4b5a-8638-25dc8f4263cf', 'Maxim', 'Maximov', 'position4', 100000, 'f17918e2-e18a-11ef-9bd3-0242ac180002');