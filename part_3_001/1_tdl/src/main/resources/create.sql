DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item (
  id        SERIAL PRIMARY KEY,
  descript  VARCHAR(32) NOT NULL,
  create_dt TIMESTAMP NOT NULL,
  done      BOOLEAN NOT NULL
);