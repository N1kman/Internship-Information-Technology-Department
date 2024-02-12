CREATE TABLE aircrafts
(
	code varchar(15) PRIMARY KEY,
	model varchar(50) NOT NULL,
	company varchar(50) NOT NULL
);


CREATE TABLE seats
(
	id serial PRIMARY KEY,
	code varchar(15) NOT NULL,
	seat_type varchar(50) NOT NULL,
	fk_seats_aircrafts varchar(15) REFERENCES aircrafts(code)
);

CREATE TABLE flights
(
	code varchar(15) PRIMARY KEY,
	place_departure varchar(50) NOT NULL,
	place_arrival varchar(50) NOT NULL,
	flight_date varchar(50) NOT NULL,
	status int NOT NULL,
	fk_flights_aircrafts varchar(15) REFERENCES aircrafts(code)
);

CREATE TABLE clients
(
	passport_id varchar(15) PRIMARY KEY,
	firstname varchar(50) NOT NULL,
	surname varchar(50) NOT NULL,
	patronymic varchar(50) NOT NULL
);

CREATE TABLE history
(
	flight_id varchar(15) REFERENCES flights(code),
	client_id varchar(15) REFERENCES clients(passport_id),
	CONSTRAINT flights_clients_id PRIMARY KEY (flight_id, client_id)
);

CREATE TABLE tickets
(
	code varchar(15) PRIMARY KEY,
	seat_code varchar(15) NOT NULL,
	status boolean NOT NULL,
	fk_tickets_flights varchar(15) REFERENCES flights(code)
);

CREATE TABLE reservations
(
	id serial PRIMARY KEY,
	reservation_date varchar(50) NOT NULL,
	fk_reservations_tickets varchar(15) UNIQUE REFERENCES tickets(code),
	fk_reservations_clients varchar(15) REFERENCES clients(passport_id)
);