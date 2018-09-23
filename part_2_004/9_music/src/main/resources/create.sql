DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE role (
  code     CHAR(8) PRIMARY KEY,
  descript VARCHAR(16) NOT NULL
);
INSERT INTO role VALUES ('ADMIN', 'Администратор');
INSERT INTO role VALUES ('MANDATOR', 'Чудо-зверь');
INSERT INTO role VALUES ('USER', 'Пользователь');

DROP TABLE IF EXISTS music_type CASCADE;
CREATE TABLE music_type (
  code     CHAR(8) PRIMARY KEY,
  descript VARCHAR(16) NOT NULL
);
INSERT INTO music_type VALUES ('BLUES', 'Блюз');
INSERT INTO music_type VALUES ('JAZZ', 'Джаз');
INSERT INTO music_type VALUES ('CLASSIC', 'Классическая');
INSERT INTO music_type VALUES ('POP', 'Популярная');
INSERT INTO music_type VALUES ('RAP', 'Рап');
INSERT INTO music_type VALUES ('ROCK', 'Рок');
INSERT INTO music_type VALUES ('ACID', 'Клубная');
INSERT INTO music_type VALUES ('PUNK', 'Частушки');

DROP TABLE IF EXISTS uzer CASCADE;
CREATE TABLE uzer (
  login      CHAR(8) PRIMARY KEY,
  password   VARCHAR(8) NOT NULL,
  name       VARCHAR(16) NOT NULL,
  role_code  CHAR(8) NOT NULL REFERENCES role
);
INSERT INTO uzer (login, password, name, role_code)
VALUES ('root', 'ruth', 'Руфь', 'ADMIN');

DROP TABLE IF EXISTS address CASCADE;
CREATE TABLE address (
  user_login CHAR(8) REFERENCES uzer,
  address    VARCHAR(32) NOT NULL
);
INSERT INTO address VALUES ('root', 'ул. Рутовского, 1');

DROP TABLE IF EXISTS user_music_type;
CREATE TABLE user_music_type (
  user_login      CHAR(8) NOT NULL REFERENCES uzer,
  music_type_code CHAR(8) NOT NULL REFERENCES music_type,
  UNIQUE (user_login, music_type_code)
);