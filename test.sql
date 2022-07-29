
DROP TABLE IF EXISTS bookingsystem.booking;
DROP TABLE IF EXISTS bookingsystem.hotel_rooms;
DROP TABLE IF EXISTS bookingsystem.room_types;
DROP TABLE IF EXISTS bookingsystem.clients;

CREATE TABLE IF NOT EXISTS bookingsystem.clients 
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	name character varying COLLATE pg_catalog."default" NOT NULL,
	surname character varying COLLATE pg_catalog."default" NOT NULL,
	patronymic character varying COLLATE pg_catalog."default",
	phone_number character varying(20) COLLATE pg_catalog."default",
	email character varying(100) COLLATE pg_catalog."default",
	CONSTRAINT clients_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS bookingsystem.clients
	OWNER to postgres;

INSERT INTO bookingsystem.clients(name, surname, phone_number)
VALUES ('Borya', 'Vasiliev', '7898564545')
	, ('Valera', 'Borisov', '7898564545')
	, ('Evgeniy', 'Aleshin', '7898564545')
	, ('Dima', 'Dmitriev', '7898564545')
	, ('Egor', 'Novoselov', '7898564545')
	, ('Vity', 'Sidorov', '7898564545')
	, ('Oleg', 'Ivanov', '7898564545');
	
CREATE TABLE IF NOT EXISTS bookingsystem.room_types
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	type_name character varying COLLATE pg_catalog."default" NOT NULL,
	CONSTRAINT room_types_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS booksystem.room_types
	OWNER to postgres;
	
INSERT INTO bookingsystem.room_types(type_name)
VALUES ('sdf')
	, ('df')
	, ('wer');

CREATE TABLE IF NOT EXISTS bookingsystem.hotel_rooms
(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	room_number numeric NOT NULL,
	title character varying(25) COLLATE pg_catalog."default",
	type_id bigint NOT NULL,
	CONSTRAINT hotel_rooms_pkey PRIMARY KEY (id),
	CONSTRAINT room_type_fkey FOREIGN KEY (type_id)
		REFERENCES bookingsystem.room_types (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
		NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS bookingsystem.hotel_rooms
	OWNER to postgres;
	
INSERT INTO bookingsystem.hotel_rooms(room_number, type_id)
VALUES (1, 1)
	, (2, 1)
	, (3, 2)
	, (4, 2)
	, (5, 2)
	, (6, 3)
	, (7, 3)
	, (8, 2)
	, (9, 3)
	, (10, 3);
	
CREATE TABLE IF NOT EXISTS bookingsystem.booking(
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
	client_id bigint NOT NULL,
	room_id bigint NOT NULL,
	date_start date NOT NULL,
	date_end date NOT NULL,
	description character varying(512) COLLATE pg_catalog."default",
	children numeric,
	adults numeric NOT NULL,
	CONSTRAINT booking_pkey PRIMARY KEY (id),
	CONSTRAINT clients_fkey FOREIGN KEY (client_id)
		REFERENCES bookingsystem.clients (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
		NOT VALID,
	CONSTRAINT hotel_rooms FOREIGN KEY (room_id)
		REFERENCES bookingsystem.hotel_rooms (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
		NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS bookingsystem.booking
	OWNER to postgres;
	
INSERT INTO bookingsystem.booking(client_id, room_id, date_start, date_end, description, children, adults)
VALUES (1, 1, '2022-07-21', '2022-08-03', '', 2, 2)
	, (2, 2, '2022-07-15', '2022-07-05', '', 1, 2)
	, (1, 3, '2022-08-01', '2022-08-05', '', 1, 2)
	, (4, 4, '2022-08-15', '2022-08-25', '', 1, 2)
	, (7, 5, '2022-08-11', '2022-08-18', '', 1, 2)
	, (1, 6, '2022-07-25', '2022-08-05', '', 1, 2)
	, (7, 7, '2022-08-01', '2022-08-13', '', 1, 2);