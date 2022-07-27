create table clients
(
    id           bigint generated always as identity
        primary key,
    name         varchar not null,
    surname      varchar not null,
    patronymic   varchar,
    phone_number varchar(20),
    email        varchar(100)
);

comment on table clients is 'Таблица для хранения иформации о клиентах
id - primary key
name - имя
surname - фамилия 
patronymic - отчество (необязательное поле)
phone_number - номер телефона';

alter table clients
    owner to postgres;

INSERT INTO bookingsystem.clients (name, surname, patronymic, phone_number, email) VALUES ('Ivanov', 'Ivan', null, '212312', null);
