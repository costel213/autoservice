INSERT IGNORE INTO clients VALUES (1, '110 W. Liberty St.', 'Suceava', 'George', 'Franklin', '6085551023');
INSERT IGNORE INTO clients VALUES (2, '638 Cardinal Ave.', 'Falticeni', 'Betty', 'Davis', '6085551749');
INSERT IGNORE INTO clients VALUES (3, '2693 Commerce St.', 'Hartop', 'Eduardo', 'Rodriquez', '6085558763');
INSERT IGNORE INTO clients VALUES (4, '563 Friendly St.', 'Falticeni', 'Harold', 'Davis', '6085553198');
INSERT IGNORE INTO clients VALUES (5, '2387 S. Fair Way', 'Radauti', 'Peter', 'McTavish', '6085552765');
INSERT IGNORE INTO clients VALUES (6, '105 N. Lake St.', 'Radaseni', 'Jean', 'Coleman', '6085552654');
INSERT IGNORE INTO clients VALUES (7, '1450 Oak Blvd.', 'Suceava', 'Jeff', 'Black', '6085555387');
INSERT IGNORE INTO clients VALUES (8, '345 Maple St.', 'Hartop', 'Maria', 'Escobito', '6085557683');
INSERT IGNORE INTO clients VALUES (9, '2749 Blackhawk Trail', 'Preutesti', 'David', 'Schroeder', '6085559435');
INSERT IGNORE INTO clients VALUES (10, '2335 Independence La.', 'Falticeni', 'Carlos', 'Estaban', '6085555487');

INSERT IGNORE INTO types VALUES (1, 'family car');
INSERT IGNORE INTO types VALUES (2, 'off-road car');
INSERT IGNORE INTO types VALUES (3, 'pick-up truck');
INSERT IGNORE INTO types VALUES (4, 'sport car');
INSERT IGNORE INTO types VALUES (5, 'luxury car');

INSERT IGNORE INTO cars VALUES (1, '2010-09-07', 'Dacia Logan', 1, 1);
INSERT IGNORE INTO cars VALUES (2, '2012-08-06', 'Volkswagen Polo', 1, 2);
INSERT IGNORE INTO cars VALUES (3, '2011-04-17', 'Audi A4', 1, 3);
INSERT IGNORE INTO cars VALUES (4, '2010-03-07', 'Ford F-150', 3, 3);
INSERT IGNORE INTO cars VALUES (5, '2010-11-30', 'Nissan Frontier', 3, 4);
INSERT IGNORE INTO cars VALUES (6, '2020-01-20', 'Aston Martin DB9', 4, 5);
INSERT IGNORE INTO cars VALUES (7, '2019-09-04', 'BMW 340d', 1, 6);
INSERT IGNORE INTO cars VALUES (8, '2021-09-04', 'Alfa Romeo Stelvio', 1, 6);
INSERT IGNORE INTO cars VALUES (9, '2016-08-06', 'Toyota Tundra', 3, 7);
INSERT IGNORE INTO cars VALUES (10, '2021-02-24', 'Land Rover Defender', 2, 8);
INSERT IGNORE INTO cars VALUES (11, '2010-03-09', 'Mercedes-Benz Unimog', 2, 9);
INSERT IGNORE INTO cars VALUES (12, '2010-06-24', 'Ariel Nomad', 2, 10);
INSERT IGNORE INTO cars VALUES (13, '2012-06-08', 'Mercedes-Benz C220', 1, 10);
INSERT IGNORE INTO cars VALUES (14, '2016-04-07', 'Mazda MX-5 Miata', 4, 1);
INSERT IGNORE INTO cars VALUES (15, '2018-12-06', 'Toyota Supra', 4, 2);
INSERT IGNORE INTO cars VALUES (16, '2020-02-18', 'Porsche 718 Cayman', 4, 3);
INSERT IGNORE INTO cars VALUES (17, '2021-01-07', 'Mercedes-Benz E400d', 3, 3);
INSERT IGNORE INTO cars VALUES (18, '2017-12-23', 'BMW Z4', 4, 4);
INSERT IGNORE INTO cars VALUES (19, '2021-10-12', 'Rolls-Royce Ghost', 5, 5);
INSERT IGNORE INTO cars VALUES (20, '2018-04-24', 'Bentley Bentayga', 5, 6);
INSERT IGNORE INTO cars VALUES (21, '2021-05-26', 'Chevrolet Colorado', 3, 6);
INSERT IGNORE INTO cars VALUES (22, '2020-10-01', 'Ford Focus', 1, 7);
INSERT IGNORE INTO cars VALUES (23, '2013-07-27', 'Lada Niva', 2, 8);
INSERT IGNORE INTO cars VALUES (24, '2016-06-20', 'Volkswagen Arteon', 1, 9);
INSERT IGNORE INTO cars VALUES (25, '2018-03-21', 'Dacia Duster', 2, 10);
INSERT IGNORE INTO cars VALUES (26, '2022-01-10', 'Toyota Tacoma', 3, 10);

INSERT IGNORE INTO mechanics VALUES (1, '23 Main St.', 'Falticeni', 'James', 'Carter', '34675667867');
INSERT IGNORE INTO mechanics VALUES (2, '65 Random St.', 'Faticeni', 'Helen', 'Leary', '67u8942345');
INSERT IGNORE INTO mechanics VALUES (3, '01 Sunrise St.', 'Hartop', 'Linda', 'Douglas', '78989078967');
INSERT IGNORE INTO mechanics VALUES (4, '02 Roses St.', 'Suceava', 'Rafael', 'Ortega', '26753467125');
INSERT IGNORE INTO mechanics VALUES (5, '98 University St.', 'Suceava', 'Stevens', 'Peterson', '5674893235');
INSERT IGNORE INTO mechanics VALUES (6, '22 Edge St.', 'Hartop', 'Sharon', 'Jenkins', '04972389345');

INSERT IGNORE INTO specialties VALUES (1, 'Engine Repair');
INSERT IGNORE INTO specialties VALUES (2, 'Engine Performance');
INSERT IGNORE INTO specialties VALUES (3, 'Automatic Transmission/Transaxle');
INSERT IGNORE INTO specialties VALUES (4, 'Brakes');
INSERT IGNORE INTO specialties VALUES (5, 'Electrical/Electronic Systems');
INSERT IGNORE INTO specialties VALUES (6, 'Heating and Air Conditioning');
INSERT IGNORE INTO specialties VALUES (7, 'Manual Drive Train and Axles');
INSERT IGNORE INTO specialties VALUES (8, 'Suspension and Steering');

INSERT IGNORE INTO mechanic_specialties VALUES (1, 1);
INSERT IGNORE INTO mechanic_specialties VALUES (1, 2);
INSERT IGNORE INTO mechanic_specialties VALUES (2, 3);
INSERT IGNORE INTO mechanic_specialties VALUES (2, 7);
INSERT IGNORE INTO mechanic_specialties VALUES (3, 4);
INSERT IGNORE INTO mechanic_specialties VALUES (3, 8);
INSERT IGNORE INTO mechanic_specialties VALUES (4, 5);
INSERT IGNORE INTO mechanic_specialties VALUES (4, 6);
INSERT IGNORE INTO mechanic_specialties VALUES (5, 7);
INSERT IGNORE INTO mechanic_specialties VALUES (6, 4);

INSERT IGNORE INTO users VALUES(1, 'Default', 'User', '$2a$12$SWzid6MFMVt4RXyneWcJzezy0rLGWVU58Owsew8Cdtc7hq0APH8ni', 'user');
INSERT IGNORE INTO users VALUES(2, 'Default', 'Admin', '$2a$12$YTovQFfneGyyJ5dLVa7kCOswj65pHwd8h0hkYcgBlpHjd9MSWii5.', 'admin');

INSERT IGNORE INTO roles VALUES(1, 'ROLE_USER');
INSERT IGNORE INTO roles VALUES(2, 'ROLE_ADMIN');

INSERT IGNORE INTO user_roles VALUES(1, 1);
INSERT IGNORE INTO user_roles VALUES(2, 1);
INSERT IGNORE INTO user_roles VALUES(2, 2);
