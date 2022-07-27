create table booking
(
    id         bigint generated always as identity
        primary key,
    client_id  bigint  not null
        constraint clients_fkey
            references clients,
    room_id    bigint  not null
        constraint hotel_rooms_fkey
            references hotel_rooms,
    date_start date    not null,
    date_end   date    not null,
    desription varchar(512),
    children   numeric,
    adults     numeric not null
);

comment on table booking is 'Хранение бронирований
  id - primary key
  client_id foreyng key на таблицу clients
  room_id foreyng key на таблицу hotel_rooms
  date_start дата въезда (только дата)
  date_end дата выезда (только дата)
  description заметки (512 символов)
  children количество детей (необязательное поле)
  adults количесвот взрослых (обязательное поле)';

alter table booking
    owner to postgres;

INSERT INTO bookingsystem.booking (client_id, room_id, date_start, date_end, desription, children, adults) VALUES (3, 1, '2022-07-27', '2022-07-31', null, 0, 5);
