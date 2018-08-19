DROP TABLE IF EXISTS rule CASCADE;
CREATE TABLE rule (
  id SERIAL CONSTRAINT pk_rule PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE role (
  id       SERIAL CONSTRAINT pk_role PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS role_rule;
CREATE TABLE role_rule (
  role_id INT NOT NULL,
  rule_id INT NOT NULL,
  CONSTRAINT pk_role_rule PRIMARY KEY (role_id, rule_id),
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role,
  CONSTRAINT fk_rule FOREIGN KEY (rule_id) REFERENCES rule
);
DROP TABLE IF EXISTS uzer CASCADE;
CREATE TABLE uzer (
  id      SERIAL CONSTRAINT pk_uzer PRIMARY KEY,
  role_id INT NOT NULL,
  name    VARCHAR(32),
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role
);
DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
  id       SERIAL CONSTRAINT pk_category PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS state CASCADE;
CREATE TABLE state (
  id       SERIAL CONSTRAINT pk_state PRIMARY KEY,
  descript VARCHAR(16)
);
DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item (
  id          SERIAL CONSTRAINT pk_item PRIMARY KEY,
  uzer_id     INT NOT NULL
    UNIQUE, -- в задании почему-то указана связь один-к-одному
  category_id INT NOT NULL,
  state_id    INT NOT NULL,
  name        VARCHAR(16),
  descript    VARCHAR(64),
  CONSTRAINT fk_uzer FOREIGN KEY (uzer_id) REFERENCES uzer,
  CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category,
  CONSTRAINT fk_state FOREIGN KEY (state_id) REFERENCES state
);
DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
  id      SERIAL CONSTRAINT pk_comment PRIMARY KEY,
  item_id INT NOT NULL,
  comment VARCHAR(64),
  CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES item
);
DROP TABLE IF EXISTS attach;
CREATE TABLE attach (
  id       SERIAL CONSTRAINT pk_attach PRIMARY KEY,
  item_id  INT NOT NULL,
  path     VARCHAR(32),
  resource OID,
  comment  VARCHAR(64),
  CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES item
);

INSERT INTO rule (descript) VALUES ('Права ролей 01');
INSERT INTO rule (descript) VALUES ('Права ролей 02');
INSERT INTO rule (descript) VALUES ('Права ролей 03');

INSERT INTO role (descript) VALUES ('Administrator');
INSERT INTO role (descript) VALUES ('Mentor');
INSERT INTO role (descript) VALUES ('Student');
INSERT INTO role (descript) VALUES ('Guest');

INSERT INTO role_rule VALUES (1, 1);
INSERT INTO role_rule VALUES (1, 2);
INSERT INTO role_rule VALUES (1, 3);
INSERT INTO role_rule VALUES (2, 1);
INSERT INTO role_rule VALUES (2, 2);
INSERT INTO role_rule VALUES (3, 1);

INSERT INTO uzer (role_id, name) VALUES (1, 'root');

INSERT INTO category (descript) VALUES ('Задание');
INSERT INTO category (descript) VALUES ('Предложение');

INSERT INTO state (descript) VALUES ('New');
INSERT INTO state (descript) VALUES ('Processing');
INSERT INTO state (descript) VALUES ('Closed');
