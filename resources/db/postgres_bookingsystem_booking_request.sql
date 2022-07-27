create table booking_request
(
    id          bigint generated always as identity
        primary key,
    client_id   bigint  not null
        constraint client_fkey
            references clients,
    room_id     bigint  not null
        constraint hotel_rooms_fkey
            references hotel_rooms,
    date_start  date    not null,
    date_end    date    not null,
    description varchar(512),
    children    numeric,
    adults      numeric not null
);

comment on table booking_request is 'Хранение заявки на бронирование
  id - primary key
  client_id foreyng key на таблицу clients
  room_id foreyng key на таблицу hotel_rooms
  date_start дата въезда (только дата)
  date_end дата выезда (только дата)
  descriptionзаметки (512 символов)
  children количество детей (необязательное поле)
  adults количесвот взрослых (обязательное поле)';

alter table booking_request
    owner to postgres;

