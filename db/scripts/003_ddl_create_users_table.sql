create table users(
id serial primary key,
name varchar (200) not null,
login varchar unique not null,
password varchar not null
);