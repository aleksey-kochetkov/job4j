CREATE TABLE item (
  id       SERIAL CONSTRAINT pk_item PRIMARY KEY,
  name     VARCHAR(16),
  descript VARCHAR(32)
);