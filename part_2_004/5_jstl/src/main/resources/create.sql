DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE role (
  code CHAR(4) PRIMARY KEY,
  descript VARCHAR(16)
);
INSERT INTO role VALUES ('root', 'Администратор');
INSERT INTO role VALUES ('user', 'Пользователь');

DROP TABLE IF EXISTS country CASCADE;
CREATE TABLE country (
  code CHAR(2) NOT NULL PRIMARY KEY,
  name VARCHAR(16) NOT NULL
);
INSERT INTO country VALUES ('7', 'РФ');
INSERT INTO country VALUES ('20', 'Египет');
INSERT INTO country VALUES ('30', 'Греция');

DROP TABLE IF EXISTS city CASCADE;
CREATE TABLE city (
  country_code CHAR(2) NOT NULL REFERENCES country,
  code         CHAR(5) NOT NULL PRIMARY KEY,
  name         VARCHAR(16) NOT NULL
);
INSERT INTO city VALUES ('7', '7495', 'Москва');
INSERT INTO city VALUES ('7', '74832', 'Брянск');
INSERT INTO city VALUES ('7', '7812', 'СПб');
INSERT INTO city VALUES ('20', '202', 'Каир');
INSERT INTO city VALUES ('20', '203', 'Александрия');
INSERT INTO city VALUES ('20', '2040', 'Котур');
INSERT INTO city VALUES ('30', '301', 'Афины');
INSERT INTO city VALUES ('30', '30241', 'Родос');
INSERT INTO city VALUES ('30', '30261', 'Ливадия');

DROP TABLE IF EXISTS uzer;
CREATE TABLE uzer (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(16),
  login     VARCHAR(16),
  email     VARCHAR(16),
  create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  password  VARCHAR(16),
  role_code CHAR(4) NOT NULL REFERENCES role,
  city_code CHAR(5) NOT NULL REFERENCES city
);
INSERT INTO uzer (name, login, email, password, role_code, city_code)
VALUES ('Руфь', 'root', 'ruth@ya.ru', 'ruth', 'root', '202');