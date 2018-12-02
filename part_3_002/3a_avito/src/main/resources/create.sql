DROP TABLE IF EXISTS uzer CASCADE;
CREATE TABLE uzer (
  login    CHAR(8) PRIMARY KEY,
  password CHAR(8) NOT NULL,
  name     VARCHAR(16) NOT NULL
);
INSERT INTO uzer VALUES ('irkalii', 'irkalii', 'Иркалий');
INSERT INTO uzer VALUES ('seller', 'seller', 'Селлер');
INSERT INTO uzer VALUES ('princess', 'princess', 'Принцесса');

DROP TABLE IF EXISTS mark CASCADE;
CREATE TABLE mark (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(16) NOT NULL
);
INSERT INTO mark (name) VALUES ('Mach');
INSERT INTO mark (name) VALUES ('Avito');
INSERT INTO mark (name) VALUES ('Shaur');
INSERT INTO mark (name) VALUES ('Happy');

DROP TABLE IF EXISTS model CASCADE;
CREATE TABLE model (
  id      SERIAL PRIMARY KEY,
  mark_id INT NOT NULL REFERENCES mark,
  name    VARCHAR(16) NOT NULL
);
INSERT INTO model (mark_id, name) VALUES (1, 'Eene');
INSERT INTO model (mark_id, name) VALUES (1, 'No');
INSERT INTO model (mark_id, name) VALUES (2, 'Kushney');
INSERT INTO model (mark_id, name) VALUES (2, 'Beever');
INSERT INTO model (mark_id, name) VALUES (2, 'Car');
INSERT INTO model (mark_id, name) VALUES (3, 'Mah');
INSERT INTO model (mark_id, name) VALUES (3, 'Porebrich');
INSERT INTO model (mark_id, name) VALUES (4, 'Car');
INSERT INTO model (mark_id, name) VALUES (4, 'Automobile');
INSERT INTO model (mark_id, name) VALUES (4, 'Jaba Three');

DROP TABLE IF EXISTS car_body CASCADE;
CREATE TABLE car_body (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16) NOT NULL
);
INSERT INTO car_body (descript) VALUES ('Седан');
INSERT INTO car_body (descript) VALUES ('Хетчбэк');
INSERT INTO car_body (descript) VALUES ('Универсал');
INSERT INTO car_body (descript) VALUES ('Внедорожник');
INSERT INTO car_body (descript) VALUES ('Кабриолет');
INSERT INTO car_body (descript) VALUES ('Купе');
INSERT INTO car_body (descript) VALUES ('Лимузин');
INSERT INTO car_body (descript) VALUES ('Минивэн');
INSERT INTO car_body (descript) VALUES ('Пикап');
INSERT INTO car_body (descript) VALUES ('Фургон');
INSERT INTO car_body (descript) VALUES ('Микроавтобус');

DROP TABLE IF EXISTS engine CASCADE;
CREATE TABLE engine (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16) NOT NULL
);
INSERT INTO engine (descript) VALUES ('Бензин');
INSERT INTO engine (descript) VALUES ('Дизель');
INSERT INTO engine (descript) VALUES ('Гибрид');
INSERT INTO engine (descript) VALUES ('Электро');
INSERT INTO engine (descript) VALUES ('Газ');

DROP TABLE IF EXISTS transmission CASCADE;
CREATE TABLE transmission (
  id       SERIAL PRIMARY KEY,
  descript VARCHAR(16) NOT NULL
);
INSERT INTO transmission (descript) VALUES ('Механика');
INSERT INTO transmission (descript) VALUES ('Автомат');
INSERT INTO transmission (descript) VALUES ('Робот');
INSERT INTO transmission (descript) VALUES ('Вариатор');

DROP TABLE IF EXISTS ad CASCADE;
CREATE TABLE ad (
  id              SERIAL PRIMARY KEY,
  login           CHAR(8) NOT NULL REFERENCES uzer,
  model_id        INT NOT NULL REFERENCES model,
  car_body_id     INT NOT NULL REFERENCES car_body,
  engine_id       INT NOT NULL REFERENCES engine,
  transmission_id INT NOT NULL REFERENCES transmission,
  create_dt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  closed          BOOLEAN NOT NULL DEFAULT FALSE,
  year            INT NOT NULL,
  km              INT NOT NULL,
  price           INT NOT NULL
);

DROP TABLE IF EXISTS image CASCADE;
CREATE TABLE image (
  ad_id INT NOT NULL REFERENCES ad,
  image BYTEA
);