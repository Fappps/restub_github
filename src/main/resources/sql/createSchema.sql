-- create table horse if not exists
CREATE TABLE IF NOT EXISTS horse (
  -- use auto incrementing IDs as primary key
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  breed     TEXT,
  min_speed DOUBLE       NOT NULL,
  max_speed DOUBLE       NOT NULL,
  created   DATETIME     NOT NULL,
  updated   DATETIME     NOT NULL
);

-- create table jockey if not exists
CREATE TABLE IF NOT EXISTS jockey (
  -- use auto incrementing IDs as primary key
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  skills    DOUBLE,
  created   DATETIME     NOT NULL,
  updated   DATETIME     NOT NULL
);

-- create table simulation if not exists
CREATE TABLE IF NOT EXISTS simulation (
  -- use auto incrementing IDs as primary key
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  created   DATETIME     NOT NULL
);

-- create table simulation_jockey_horse if not exists
CREATE TABLE IF NOT EXISTS simulation_jockey_horse (
  -- use auto incrementing IDs as primary key
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  horseID BIGINT  NOT NULL REFERENCES horse(id),
  jockeyID BIGINT NOT NULL REFERENCES jockey(id) ,
  simulationID BIGINT NOT NULL REFERENCES simulation(id) ,
  rank BIGINT NOT NULL,
  luckfactor DOUBLE NOT NULL,
  avgSpeed DOUBLE NOT NULL,
  horseSpeed DOUBLE NOT NULL
);