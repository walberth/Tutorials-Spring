CREATE DATABASE xxxxx;
USE xxxxx;

-- -----------------------------------------------------
-- Table `xxxxx`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xxxxx`.`person` ;

CREATE TABLE IF NOT EXISTS `xxxxx`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `father_name` VARCHAR(45) NULL,
  `mother_name` VARCHAR(45) NULL,
  `sex` TINYINT NOT NULL DEFAULT 1,
  `birth_date` DATETIME NULL,
  `document` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `mobile` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  `user_register` VARCHAR(20) NOT NULL,
  `time_stamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `xxxxx`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xxxxx`.`role` ;

CREATE TABLE IF NOT EXISTS `xxxxx`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `xxxxx`.`permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xxxxx`.`permission` ;

CREATE TABLE IF NOT EXISTS `xxxxx`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `name` VARCHAR(500) NOT NULL,
  INDEX `role_fk1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_fk0`
    FOREIGN KEY (`role_id`)
    REFERENCES `xxxxx`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `xxxxx`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xxxxx`.`user` ;

CREATE TABLE IF NOT EXISTS `xxxxx`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL,
  `password` TEXT NULL,
  `person_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `user_register` VARCHAR(20) NOT NULL,
  `time_stamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `user_fk1_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `user_fk0`
    FOREIGN KEY (`person_id`)
    REFERENCES `xxxxx`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `user_fk2_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_fk1`
    FOREIGN KEY (`role_id`)
    REFERENCES `xxxxx`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `xxxxx`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xxxxx`.`session` ;

CREATE TABLE IF NOT EXISTS `xxxxx`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `token` TEXT NOT NULL,
  `time_release` BIGINT NOT NULL,
  `expiration_time` DATETIME NOT NULL,
  INDEX `session_fk1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `session_fk0`
    FOREIGN KEY (`user_id`)
    REFERENCES `xxxxx`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;