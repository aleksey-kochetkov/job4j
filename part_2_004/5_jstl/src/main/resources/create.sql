DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE role (
  code CHAR(4) PRIMARY KEY,
  descript VARCHAR(16)
);
INSERT INTO role VALUES ('root', 'Администратор');
INSERT INTO role VALUES ('user', 'Пользователь');

DROP TABLE IF EXISTS uzer;
CREATE TABLE uzer (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(16),
  login     VARCHAR(16),
  email     VARCHAR(16),
  create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  password  VARCHAR(16),
  role_code CHAR(4) NOT NULL REFERENCES role
);
INSERT INTO uzer (name, login, email, password, role_code) VALUES (
  'Руфь', 'root', 'ruth@ya.ru', 'ruth', 'root');
