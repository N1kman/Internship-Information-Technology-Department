CREATE TABLE IF NOT EXISTS public.aircraft
(
	id BIGSERIAL PRIMARY KEY,
	registration_number VARCHAR(15),
	model VARCHAR(50) NOT NULL,
	company VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS public.seat
(
	id BIGSERIAL PRIMARY KEY,
	seat_number VARCHAR(15) NOT NULL,
	seat_type VARCHAR(50) NOT NULL,
	id_aircraft BIGINT NOT NULL,
	CONSTRAINT fk_aircraft FOREIGN KEY(id_aircraft) REFERENCES aircraft(id)
);

CREATE TABLE IF NOT EXISTS public.flight
(
	id BIGSERIAL PRIMARY KEY,
	code VARCHAR(15) NOT NULL,
	place_departure VARCHAR(50) NOT NULL,
	place_arrival VARCHAR(50) NOT NULL,
	date_departure TIMESTAMP NOT NULL,
	date_arrival TIMESTAMP NOT NULL,
	id_aircraft BIGINT NOT NULL,
	CONSTRAINT fk_aircraft FOREIGN KEY(id_aircraft) REFERENCES aircraft(id)
);

CREATE TABLE IF NOT EXISTS public.client
(
	id BIGSERIAL PRIMARY KEY,
	passport_id VARCHAR(15) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	surname VARCHAR(50) NOT NULL,
	patronymic VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.history
(
	id_flight BIGINT NOT NULL,
	id_client BIGINT NOT NULL,
	PRIMARY KEY (id_flight, id_client),
	CONSTRAINT fk_flight FOREIGN KEY(id_flight) REFERENCES flight(id),
	CONSTRAINT fk_client FOREIGN KEY(id_client) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS public.ticket
(
	id BIGSERIAL PRIMARY KEY,
	ticket_number VARCHAR(15) NOT NULL,
	seat_number VARCHAR(15) NOT NULL,
	ticket_status BOOLEAN NOT NULL,
	id_flight BIGINT NOT NULL,
	CONSTRAINT fk_flight FOREIGN KEY(id_flight) REFERENCES flight(id)
);

CREATE TABLE IF NOT EXISTS public.reservation
(
	id_ticket BIGINT PRIMARY KEY,
	reservation_date TIMESTAMP DEFAULT now(),
	id_client BIGINT NOT NULL,
	CONSTRAINT fk_ticket FOREIGN KEY(id_ticket) REFERENCES ticket(id),
	CONSTRAINT fk_client FOREIGN KEY(id_client) REFERENCES client(id)
);