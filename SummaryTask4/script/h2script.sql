DROP VIEW IF EXISTS BOOKED_TOUR;
DROP VIEW IF EXISTS FREE_TOUR;
DROP VIEW IF EXISTS REGISTERED_TOUR;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS verification_code;
DROP TABLE IF EXISTS verification_code;
DROP TABLE IF EXISTS type_of_holiday;
DROP TABLE IF EXISTS hotel_type;
DROP TABLE IF EXISTS tour_status;
DROP TABLE IF EXISTS tour;

CREATE TABLE IF NOT EXISTS user_role (
  role_id BIGINT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(45) NOT NULL,
  office_coefficient TINYINT NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE INDEX role_name_UNIQUE (role_name ASC),
  UNIQUE INDEX office_coefficient_UNIQUE (office_coefficient ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL,
  user_email VARCHAR(45) NOT NULL,
  user_password VARCHAR(65) NOT NULL,
  user_salt VARCHAR(45) NOT NULL,
  user_role BIGINT NOT NULL,
  is_confirmed TINYINT(1) NOT NULL,
  purchased_tours BIGINT NOT NULL,
  balance BIGINT NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE INDEX user_email_UNIQUE (user_email ASC),
  CONSTRAINT fk_user_1
    FOREIGN KEY (user_role)
    REFERENCES user_role (role_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS verification_code (
  code_id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  user_code VARCHAR(45) NOT NULL,
  PRIMARY KEY (code_id),
  CONSTRAINT fk_VerificationCode_1
    FOREIGN KEY (user_id)
    REFERENCES user (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS type_of_holiday (
  type_id BIGINT NOT NULL AUTO_INCREMENT,
  holiday_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (type_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS hotel_type (
  hotel_type_id BIGINT NOT NULL AUTO_INCREMENT,
  type_name VARCHAR(6) NOT NULL,
  PRIMARY KEY (hotel_type_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS tour_status (
  tour_status_id INT NOT NULL AUTO_INCREMENT,
  status_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (tour_status_id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS tour (
  tour_id BIGINT NOT NULL AUTO_INCREMENT,
  tour_name VARCHAR(45) NOT NULL,
  price BIGINT NOT NULL,
  holiday_type_id BIGINT NOT NULL,
  number_of_people INT NOT NULL,
  hotel_type BIGINT NOT NULL,
  status_id INT NOT NULL,
  reserved_by_id BIGINT NULL,
  is_hot TINYINT(1) NOT NULL,
  max_discount TINYINT NOT NULL,
  step_discount TINYINT NOT NULL,
  increment_discount TINYINT NOT NULL,
  PRIMARY KEY (tour_id),
  UNIQUE INDEX tour_name_UNIQUE (tour_name ASC),
  CONSTRAINT fk_Tour_1
    FOREIGN KEY (holiday_type_id)
    REFERENCES type_of_holiday (type_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Tour_2
    FOREIGN KEY (hotel_type)
    REFERENCES hotel_type (hotel_type_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Tour_3
    FOREIGN KEY (status_id)
    REFERENCES tour_status (tour_status_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Tour_4
    FOREIGN KEY (reserved_by_id)
    REFERENCES user (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  OR REPLACE VIEW free_tour AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status WHERE tour.reserved_by_id IS NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id ORDER BY is_hot DESC;

CREATE  OR REPLACE VIEW booked_tour AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status,user,user_role WHERE tour.reserved_by_id IS NOT NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND user.user_id=tour.reserved_by_id AND user_role.role_id=user.user_role ORDER BY is_hot DESC;

CREATE  OR REPLACE VIEW registered_tour AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status,user,user_role WHERE tour.reserved_by_id IS NOT NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND user.user_id=tour.reserved_by_id AND user_role.role_id=user.user_role AND tour_status.status_name='registered' ORDER BY is_hot DESC;


INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (1, 'GUEST', 1);
INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (2, 'BANNED', 10);
INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (3, 'UNCONFIRMED', 25);
INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (4, 'CLIENT', 50);
INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (5, 'MANAGER', 90);
INSERT INTO user_role (role_id, role_name, office_coefficient) VALUES (6, 'ADMIN', 100);


INSERT INTO user (user_id, user_name, user_email, user_password, user_salt, user_role, is_confirmed, purchased_tours, balance) VALUES (1, 'Никита', 'itachi61054@mail.ru', '0a0ba3da91ffbcbe5a1dad9c5beb2ad1430efae83e0f652347f08ca485b30520', 'de14c6a86a2e4d664a97d3b5', 6, 1, 1, 5300);


INSERT INTO type_of_holiday (type_id, holiday_name) VALUES (1, 'recreation');
INSERT INTO type_of_holiday (type_id, holiday_name) VALUES (2, 'excursion');
INSERT INTO type_of_holiday (type_id, holiday_name) VALUES (3, 'shopping');


INSERT INTO hotel_type (hotel_type_id, type_name) VALUES (1, '*');
INSERT INTO hotel_type (hotel_type_id, type_name) VALUES (2, '**');
INSERT INTO hotel_type (hotel_type_id, type_name) VALUES (3, '***');
INSERT INTO hotel_type (hotel_type_id, type_name) VALUES (4, '****');
INSERT INTO hotel_type (hotel_type_id, type_name) VALUES (5, '*****');


INSERT INTO tour_status (tour_status_id, status_name) VALUES (1, 'registered');
INSERT INTO tour_status (tour_status_id, status_name) VALUES (2, 'paid');
INSERT INTO tour_status (tour_status_id, status_name) VALUES (3, 'canceled');
INSERT INTO tour_status (tour_status_id, status_name) VALUES  (4, 'active');
 
