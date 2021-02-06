create table employee
(
    id   uuid not null
        constraint employee_pkey
            primary key,
    name varchar(255),
    role varchar(255)
);

insert into employee values('ee2d8e04-8c1b-46e6-a110-34feafd62128', 'Bilbo Baggins', 'burglar');
insert into employee values('201fbd19-f3ff-435e-8fe0-058b6b68cc19', 'Frodo Baggins', 'thief');
