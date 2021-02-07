create table employee
(
    id   uuid not null
        constraint employee_pkey
            primary key,
    name varchar(255),
    role varchar(255)
);
