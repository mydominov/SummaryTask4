SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Tour` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Tour` ;

-- -----------------------------------------------------
-- Table `Tour`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`user_role` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  `office_coefficient` TINYINT NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC),
  UNIQUE INDEX `office_coefficient_UNIQUE` (`office_coefficient` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(65) NOT NULL,
  `user_salt` VARCHAR(45) NOT NULL,
  `user_role` BIGINT NOT NULL,
  `is_confirmed` TINYINT(1) NOT NULL,
  `purchased_tours` BIGINT NOT NULL,
  `balance` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC),
  INDEX `fk_user_1_idx` (`user_role` ASC),
  CONSTRAINT `fk_user_1`
    FOREIGN KEY (`user_role`)
    REFERENCES `Tour`.`user_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`verification_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`verification_code` (
  `code_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `user_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code_id`),
  INDEX `fk_VerificationCode_1_idx` (`user_id` ASC),
  CONSTRAINT `fk_VerificationCode_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Tour`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`type_of_holiday`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`type_of_holiday` (
  `type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `holiday_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`hotel_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`hotel_type` (
  `hotel_type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`hotel_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`tour_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`tour_status` (
  `tour_status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tour_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tour`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`tour` (
  `tour_id` BIGINT NOT NULL AUTO_INCREMENT,
  `tour_name` VARCHAR(45) NOT NULL,
  `price` BIGINT NOT NULL,
  `holiday_type_id` BIGINT NOT NULL,
  `number_of_people` INT NOT NULL,
  `hotel_type` BIGINT NOT NULL,
  `status_id` INT NOT NULL,
  `reserved_by_id` BIGINT NULL,
  `is_hot` TINYINT(1) NOT NULL,
  `max_discount` TINYINT NOT NULL,
  `step_discount` TINYINT NOT NULL,
  `increment_discount` TINYINT NOT NULL,
  PRIMARY KEY (`tour_id`),
  INDEX `fk_Tour_1_idx` (`holiday_type_id` ASC),
  INDEX `fk_Tour_2_idx` (`hotel_type` ASC),
  INDEX `fk_Tour_3_idx` (`status_id` ASC),
  INDEX `fk_Tour_4_idx` (`reserved_by_id` ASC),
  UNIQUE INDEX `tour_name_UNIQUE` (`tour_name` ASC),
  CONSTRAINT `fk_Tour_1`
    FOREIGN KEY (`holiday_type_id`)
    REFERENCES `Tour`.`type_of_holiday` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tour_2`
    FOREIGN KEY (`hotel_type`)
    REFERENCES `Tour`.`hotel_type` (`hotel_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tour_3`
    FOREIGN KEY (`status_id`)
    REFERENCES `Tour`.`tour_status` (`tour_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tour_4`
    FOREIGN KEY (`reserved_by_id`)
    REFERENCES `Tour`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `Tour` ;

-- -----------------------------------------------------
-- Placeholder table for view `Tour`.`free_tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`free_tour` (`tour_id` INT, `tour_name` INT, `price` INT, `holiday_type_id` INT, `number_of_people` INT, `hotel_type` INT, `status_id` INT, `reserved_by_id` INT, `is_hot` INT, `max_discount` INT, `step_discount` INT, `increment_discount` INT, `type_id` INT, `holiday_name` INT, `hotel_type_id` INT, `type_name` INT, `tour_status_id` INT, `status_name` INT);

-- -----------------------------------------------------
-- Placeholder table for view `Tour`.`booked_tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`booked_tour` (`tour_id` INT, `tour_name` INT, `price` INT, `holiday_type_id` INT, `number_of_people` INT, `hotel_type` INT, `status_id` INT, `reserved_by_id` INT, `is_hot` INT, `max_discount` INT, `step_discount` INT, `increment_discount` INT, `type_id` INT, `holiday_name` INT, `hotel_type_id` INT, `type_name` INT, `tour_status_id` INT, `status_name` INT, `user_id` INT, `user_name` INT, `user_email` INT, `user_password` INT, `user_salt` INT, `user_role` INT, `is_confirmed` INT, `purchased_tours` INT, `balance` INT, `role_id` INT, `role_name` INT, `office_coefficient` INT);

-- -----------------------------------------------------
-- Placeholder table for view `Tour`.`registered_tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tour`.`registered_tour` (`tour_id` INT, `tour_name` INT, `price` INT, `holiday_type_id` INT, `number_of_people` INT, `hotel_type` INT, `status_id` INT, `reserved_by_id` INT, `is_hot` INT, `max_discount` INT, `step_discount` INT, `increment_discount` INT, `type_id` INT, `holiday_name` INT, `hotel_type_id` INT, `type_name` INT, `tour_status_id` INT, `status_name` INT, `user_id` INT, `user_name` INT, `user_email` INT, `user_password` INT, `user_salt` INT, `user_role` INT, `is_confirmed` INT, `purchased_tours` INT, `balance` INT, `role_id` INT, `role_name` INT, `office_coefficient` INT);

-- -----------------------------------------------------
-- View `Tour`.`free_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tour`.`free_tour`;
USE `Tour`;
CREATE  OR REPLACE VIEW `free_tour` AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status WHERE tour.reserved_by_id IS NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id ORDER BY is_hot DESC;

-- -----------------------------------------------------
-- View `Tour`.`booked_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tour`.`booked_tour`;
USE `Tour`;
CREATE  OR REPLACE VIEW `booked_tour` AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status,user,user_role WHERE tour.reserved_by_id IS NOT NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND user.user_id=tour.reserved_by_id AND user_role.role_id=user.user_role ORDER BY is_hot DESC;

-- -----------------------------------------------------
-- View `Tour`.`registered_tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tour`.`registered_tour`;
USE `Tour`;
CREATE  OR REPLACE VIEW `registered_tour` AS
SELECT * FROM tour,type_of_holiday,hotel_type,tour_status,user,user_role WHERE tour.reserved_by_id IS NOT NULL AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND user.user_id=tour.reserved_by_id AND user_role.role_id=user.user_role AND tour_status.status_name='registered' ORDER BY is_hot DESC;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Tour`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `Tour`;
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (1, 'GUEST', 1);
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (2, 'BANNED', 10);
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (3, 'UNCONFIRMED', 25);
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (4, 'CLIENT', 50);
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (5, 'MANAGER', 90);
INSERT INTO `Tour`.`user_role` (`role_id`, `role_name`, `office_coefficient`) VALUES (6, 'ADMIN', 100);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Tour`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `Tour`;
INSERT INTO `Tour`.`user` (`user_id`, `user_name`, `user_email`, `user_password`, `user_salt`, `user_role`, `is_confirmed`, `purchased_tours`, `balance`) VALUES (1, 'Никита', 'itachi61054@mail.ru', '0a0ba3da91ffbcbe5a1dad9c5beb2ad1430efae83e0f652347f08ca485b30520', 'de14c6a86a2e4d664a97d3b5', 6, 1, 1, 5300);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Tour`.`type_of_holiday`
-- -----------------------------------------------------
START TRANSACTION;
USE `Tour`;
INSERT INTO `Tour`.`type_of_holiday` (`type_id`, `holiday_name`) VALUES (1, 'recreation');
INSERT INTO `Tour`.`type_of_holiday` (`type_id`, `holiday_name`) VALUES (2, 'excursion');
INSERT INTO `Tour`.`type_of_holiday` (`type_id`, `holiday_name`) VALUES (3, 'shopping');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Tour`.`hotel_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `Tour`;
INSERT INTO `Tour`.`hotel_type` (`hotel_type_id`, `type_name`) VALUES (1, '*');
INSERT INTO `Tour`.`hotel_type` (`hotel_type_id`, `type_name`) VALUES (2, '**');
INSERT INTO `Tour`.`hotel_type` (`hotel_type_id`, `type_name`) VALUES (3, '***');
INSERT INTO `Tour`.`hotel_type` (`hotel_type_id`, `type_name`) VALUES (4, '****');
INSERT INTO `Tour`.`hotel_type` (`hotel_type_id`, `type_name`) VALUES (5, '*****');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Tour`.`tour_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `Tour`;
INSERT INTO `Tour`.`tour_status` (`tour_status_id`, `status_name`) VALUES (1, 'registered');
INSERT INTO `Tour`.`tour_status` (`tour_status_id`, `status_name`) VALUES (2, 'paid');
INSERT INTO `Tour`.`tour_status` (`tour_status_id`, `status_name`) VALUES (3, 'canceled');
INSERT INTO `Tour`.`tour_status` (`tour_status_id`, `status_name`) VALUES (4, 'active');

COMMIT;

