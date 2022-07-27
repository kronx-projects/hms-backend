create table user_data
(
    id       bigint generated always as identity
        primary key,
    username varchar not null,
    password varchar not null,
    enabled  bigint  not null
);

comment on table user_data is 'Таблица для хранения пользователей для доступа (Spring Security)
username - имя пользователя
password - пароль
enabled - статус пароля (1 - активен, 0 - заблокирован)';

alter table user_data
    owner to postgres;

