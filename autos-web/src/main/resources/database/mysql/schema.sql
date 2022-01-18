CREATE TABLE IF NOT EXISTS clients (
  id          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address     VARCHAR(80) NOT NULL,
  city        VARCHAR(30) NOT NULL,
  first_name  VARCHAR(50) NOT NULL,
  last_name   VARCHAR(50) NOT NULL,
  telephone   VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS types (
  id    INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS cars (
  id                  INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_of_production  DATE,
  model VARCHAR(30)   NOT NULL,
  type_id             INTEGER UNSIGNED NOT NULL,
  owner_id            INTEGER UNSIGNED NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES clients(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);

CREATE TABLE IF NOT EXISTS mechanics (
  id         INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address     VARCHAR(255) NOT NULL,
  city        VARCHAR(80) NOT NULL,
  first_name  VARCHAR(30) NOT NULL,
  last_name   VARCHAR(30) NOT NULL,
  telephone   VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS experience (
  id             INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  position       VARCHAR(50) NOT NULL,
  time_interval  VARCHAR(30) NOT NULL,
  mechanic_id    INTEGER UNSIGNED NOT NULL,
  FOREIGN KEY (mechanic_id) REFERENCES mechanics(id)
);

CREATE TABLE IF NOT EXISTS specialties (
  id     INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS mechanic_specialties (
  mechanic_id   INTEGER UNSIGNED NOT NULL,
  specialty_id  INTEGER UNSIGNED NOT NULL,
  FOREIGN KEY (mechanic_id) REFERENCES mechanics(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (mechanic_id,specialty_id)
);

CREATE TABLE IF NOT EXISTS roles (
  id    INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  role  VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
  id          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name  VARCHAR(50) NOT NULL,
  last_name   VARCHAR(50) NOT NULL,
  password    VARCHAR(200) NOT NULL,
  user_name   VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_id  INTEGER UNSIGNED NOT NULL,
  role_id  INTEGER UNSIGNED NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  UNIQUE (user_id,role_id)
);

CREATE TABLE IF NOT EXISTS visits (
  id           INTEGER UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  visit_date   DATE,
  description  VARCHAR(50) NOT NULL,
  car_id       INTEGER UNSIGNED NOT NULL,
  mechanic_id  INTEGER UNSIGNED NOT NULL,
  FOREIGN KEY (car_id) REFERENCES cars(id),
  FOREIGN KEY (mechanic_id) REFERENCES mechanics(id)
);