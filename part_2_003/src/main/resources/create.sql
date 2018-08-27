CREATE TABLE position (
  id       SERIAL CONSTRAINT pk_position PRIMARY KEY,
  descript TEXT,
  dt       TIMESTAMP
--  CONSTRAINT ck_unique UNIQUE (descript, dt)
);
CREATE UNIQUE INDEX idx_position ON position (dt, descript);