DROP TABLE IF EXISTS car_body CASCADE;
CREATE TABLE car_body (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS engine CASCADE;
CREATE TABLE engine (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS transmission CASCADE;
CREATE TABLE transmission (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(32)
);
DROP TABLE IF EXISTS car CASCADE;
CREATE TABLE car (
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(16),
  car_body_id     INT NOT NULL REFERENCES car_body,
  engine_id       INT NOT NULL REFERENCES engine,
  transmission_id INT NOT NULL REFERENCES transmission
);