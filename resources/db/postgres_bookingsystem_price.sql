create table price
(
    id              bigint generated always as identity
        primary key,
    "room_type_id " bigint  not null
        constraint rooms_type_fkey
            references room_types,
    price           numeric not null,
    data_start      date    not null,
    data_end        date    not null
);

comment on table price is 'Таблица отвечающая за хранение данных о стоимости бронирования в определенные даты
Поля:
room_id - id номера foreing key из таблицы hotel_rooms (обязательное поле)
price - цена бронирования (обязательное поле)
data_start - дата начала действия цены
data_end - дата окончания действия цены';

alter table price
    owner to postgres;

