-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema courier_tracking_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema courier_tracking_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `courier_tracking_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `courier_tracking_system` ;

-- -----------------------------------------------------
-- Table `courier_tracking_system`.`consignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`consignment` (
  `co_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `co_from_warehouse_id` BIGINT(10) NOT NULL,
  `co_to_warehouse_id` BIGINT(10) NOT NULL,
  `co_consignment_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`co_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`user` (
  `us_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `us_first_name` VARCHAR(50) NOT NULL,
  `us_last_name` VARCHAR(50) NULL DEFAULT NULL,
  `us_gender` VARCHAR(7) NOT NULL,
  `us_email` VARCHAR(50) NULL DEFAULT NULL,
  `us_contact_number` DECIMAL(10,0) NOT NULL,
  `us_user_name` VARCHAR(15) NOT NULL,
  `us_password` VARCHAR(15) NOT NULL,
  `us_active` CHAR(1) NOT NULL,
  PRIMARY KEY (`us_id`),
  UNIQUE INDEX `us_user_name_UNIQUE` (`us_user_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`package`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`package` (
  `pa_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `pa_user_name` VARCHAR(45) NOT NULL,
  `pa_parcel_type` VARCHAR(45) NOT NULL,
  `pa_book_date` DATE NOT NULL,
  `pa_weight` FLOAT NOT NULL,
  `pa_distance` INT(6) NOT NULL,
  `pa_sender_address` VARCHAR(245) NOT NULL,
  `pa_receiver_address` VARCHAR(245) NOT NULL,
  `pa_cost` FLOAT NOT NULL,
  `pa_warehouse_location` VARCHAR(45) NOT NULL,
  `pa_status` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`pa_id`),
  INDEX `pa_user_name_idx` (`pa_user_name` ASC),
  CONSTRAINT `pa_user_name`
    FOREIGN KEY (`pa_user_name`)
    REFERENCES `courier_tracking_system`.`user` (`us_user_name`))
ENGINE = InnoDB
AUTO_INCREMENT = 5017
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`consignment_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`consignment_list` (
  `co_li_id` BIGINT(16) NOT NULL AUTO_INCREMENT,
  `co_li_package_status` TINYINT(1) NOT NULL,
  `co_li_pa_id` BIGINT(10) NOT NULL,
  `co_li_co_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`co_li_id`),
  INDEX `fk_consignment_list_package1_idx` (`co_li_pa_id` ASC),
  INDEX `fk_consignment_list_consignment1_idx` (`co_li_co_id` ASC),
  CONSTRAINT `fk_consignment_list_consignment1`
    FOREIGN KEY (`co_li_co_id`)
    REFERENCES `courier_tracking_system`.`consignment` (`co_id`),
  CONSTRAINT `fk_consignment_list_package1`
    FOREIGN KEY (`co_li_pa_id`)
    REFERENCES `courier_tracking_system`.`package` (`pa_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`employee` (
  `em_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `em_user_name` VARCHAR(15) NOT NULL,
  `em_first_name` VARCHAR(50) NOT NULL,
  `em_last_name` VARCHAR(50) NOT NULL,
  `em_gender` VARCHAR(7) NOT NULL,
  `em_email` VARCHAR(50) NULL DEFAULT NULL,
  `em_contact_number` DECIMAL(10,0) NOT NULL,
  `em_password` VARCHAR(15) NOT NULL,
  `em_salary` DECIMAL(10,0) NOT NULL,
  `em_designation` VARCHAR(50) NOT NULL,
  `em_permanent_address` VARCHAR(100) NULL DEFAULT NULL,
  `em_correspondence_address` VARCHAR(100) NOT NULL,
  `em_login_type` CHAR(1) NOT NULL,
  `em_active` CHAR(1) NOT NULL,
  PRIMARY KEY (`em_id`),
  UNIQUE INDEX `em_user_name` (`em_user_name` ASC),
  UNIQUE INDEX `em_id_UNIQUE` (`em_id` ASC),
  INDEX `em_user_name_UNIQUE` (`em_user_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 100019
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`parcel_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`parcel_type` (
  `pa_ty_id` BIGINT(5) NOT NULL AUTO_INCREMENT,
  `pa_ty_name` VARCHAR(30) NOT NULL,
  `pa_ty_price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`pa_ty_id`),
  UNIQUE INDEX `pa_ty_name` (`pa_ty_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`policy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`policy` (
  `po_id` BIGINT(5) NOT NULL AUTO_INCREMENT,
  `po_name` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`po_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`quotation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`quotation` (
  `qu_id` BIGINT(5) NOT NULL AUTO_INCREMENT,
  `qu_distance` INT(5) NOT NULL,
  `qu_price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`qu_id`),
  UNIQUE INDEX `qu_distance` (`qu_distance` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `courier_tracking_system`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`warehouse` (
  `wa_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `wa_name` VARCHAR(45) NOT NULL,
  `wa_capacity` DECIMAL(10,0) NOT NULL,
  `wa_location` VARCHAR(50) NOT NULL,
  `wa_em_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`wa_id`),
  INDEX `pk_manager_id_idx` (`wa_em_id` ASC),
  CONSTRAINT `pk_manager_id`
    FOREIGN KEY (`wa_em_id`)
    REFERENCES `courier_tracking_system`.`employee` (`em_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
