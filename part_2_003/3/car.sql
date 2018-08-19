CREATE TABLE car_body (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16)
);
CREATE TABLE engine (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16)
);
CREATE TABLE transmission (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(32)
);
CREATE TABLE car (
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(16),
  car_body_id     INT NOT NULL REFERENCES car_body,
  engine_id       INT NOT NULL REFERENCES engine,
  transmission_id INT NOT NULL REFERENCES transmission
);

INSERT INTO car_body (descript) VALUES ('Кузов 01');
INSERT INTO car_body (descript) VALUES ('Кузов 02');
INSERT INTO car_body (descript) VALUES ('Кузов 03');
INSERT INTO car_body (descript) VALUES ('Кузов 04');
INSERT INTO car_body (descript) VALUES ('Кузов 05');

INSERT INTO engine (descript) VALUES ('Двигатель 01');
INSERT INTO engine (descript) VALUES ('Двигатель 02');
INSERT INTO engine (descript) VALUES ('Двигатель 03');

INSERT INTO transmission (descript) VALUES ('Коробка передач 01');
INSERT INTO transmission (descript) VALUES ('Коробка передач 02');
INSERT INTO transmission (descript) VALUES ('Коробка передач 03');

INSERT INTO car (name, car_body_id, engine_id, transmission_id)
VALUES ('Машина 1 1 1', 1, 1, 1);
INSERT INTO car (name, car_body_id, engine_id, transmission_id)
VALUES ('Машина 1 1 2', 1, 1, 2);
INSERT INTO car (name, car_body_id, engine_id, transmission_id)
VALUES ('Машина 3 2 1', 3, 2, 1);