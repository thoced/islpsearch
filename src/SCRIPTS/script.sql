SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `db_islp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `db_islp` ;

-- -----------------------------------------------------
-- Table `db_islp`.`t_islp_info`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_islp`.`t_islp_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `contact` VARCHAR(32) NULL ,
  `type` CHAR(8) NULL ,
  `annee` CHAR(4) NULL ,
  `numero` CHAR(12) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM;

CREATE INDEX `contact_index` ON `db_islp`.`t_islp_info` (`contact` ASC) ;

CREATE UNIQUE INDEX `unique` ON `db_islp`.`t_islp_info` (`contact` ASC, `annee` ASC, `numero` ASC, `type` ASC) ;


-- -----------------------------------------------------
-- Table `db_islp`.`t_islp_search`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_islp`.`t_islp_search` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `contact` VARCHAR(32) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM;

CREATE INDEX `index_contact` ON `db_islp`.`t_islp_search` (`contact` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
