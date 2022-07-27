create table room_types
(
    id        bigint generated always as identity
        primary key,
    type_name varchar not null
);

comment on table room_types is 'Хранение типов комнат
id - primary key, increment
type_name - название типа комнаты';

alter table room_types
    owner to postgres;

INSERT INTO bookingsystem.room_types (type_name) VALUES ('Стандарт 2-у местный с одной кроватью');
INSERT INTO bookingsystem.room_types (type_name) VALUES ('Стандарт 2-у местный с двумя кроватями');
INSERT INTO bookingsystem.room_types (type_name) VALUES ('Комфорт 4-х местный');
INSERT INTO bookingsystem.room_types (type_name) VALUES ('Комфорт 1 местный');
INSERT INTO bookingsystem.room_types (type_name) VALUES ('Стандарт 1 местный');
