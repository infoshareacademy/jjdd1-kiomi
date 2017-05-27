CREATE DATABASE IF NOT EXISTS statistics;
USE statistics;
CREATE TABLE IF NOT EXISTS statistics.members
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  email VARCHAR(50),
  date DATE NOT NULL,
  firstname VARCHAR(50),
  lastname VARCHAR(50),
  role INT(1)
);
CREATE UNIQUE INDEX UK_9d30a9u1qpg8eou0otgkwrp5d ON statistics.members (email);

CREATE TABLE IF NOT EXISTS statistics.promoted_brand
(
  id BIGINT(20) PRIMARY KEY NOT NULL,
  brand VARCHAR(20),
  date_time DATETIME
);

CREATE TABLE IF NOT EXISTS statistics.search_history
(
  id BIGINT(20) PRIMARY KEY NOT NULL,
  car_brand VARCHAR(50),
  car_model VARCHAR(50),
  car_type VARCHAR(50),
  date DATE,
  part_brand VARCHAR(50),
  part_category VARCHAR(50),
  part_name VARCHAR(50),
  part_serial VARCHAR(50),
  promoted BIT(1)
);

INSERT INTO statistics.members VALUES (NULL,'azielazny@gmail.com', '2017-05-22', 'Arkadiusz', 'Zielazny', 1);
INSERT INTO statistics.members VALUES (NULL,'kiomi.info@gmail.com', '2017-05-22', 'Kiomi', 'Kiomi', 1);