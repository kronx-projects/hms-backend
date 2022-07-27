create table hotel_rooms
(
    id          bigint generated always as identity
        primary key,
    room_number numeric not null,
    title       varchar(25),
    type_id     bigint  not null
        constraint room_type_fkey
            references room_types
);

comment on table hotel_rooms is 'Хранение информации о комнотах
id - primary key
room_number - номер комнаты
title- название комнаты
type_id - тип комнаты foreing key из таблицы room_types';

alter table hotel_rooms
    owner to postgres;

INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (1, null, 1);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (3, null, 1);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (4, null, 1);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (5, null, 1);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (7, null, 1);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (2, null, 2);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (6, null, 2);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (8, null, 3);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (9, null, 4);
INSERT INTO bookingsystem.hotel_rooms (room_number, title, type_id) VALUES (10, null, 1);
