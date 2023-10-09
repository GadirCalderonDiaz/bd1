SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema caso-2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema caso-2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `caso-2` DEFAULT CHARACTER SET utf8 ;
USE `caso-2` ;

-- -----------------------------------------------------
-- Table `caso-2`.`personaFisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`personaFisica` (
  `idpersonaFisica` INT NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  PRIMARY KEY (`idpersonaFisica`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`personaJuridica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`personaJuridica` (
  `idpersonaJuridica` INT NOT NULL,
  `nombreComercial` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersonaJuridica`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`personaXjuridicaXFisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`personaXjuridicaXFisica` (
  `idpersonaXjuridicaXFisica` INT NOT NULL,
  `personaFisica_idpersonaFisica` INT NOT NULL,
  `personaJuridica_idpersonaJuridica` INT NOT NULL,
  PRIMARY KEY (`idpersonaXjuridicaXFisica`, `personaFisica_idpersonaFisica`, `personaJuridica_idpersonaJuridica`),
  INDEX `fk_personaXjuridicaXFisica_personaFisica1_idx` (`personaFisica_idpersonaFisica` ASC) VISIBLE,
  INDEX `fk_personaXjuridicaXFisica_personaJuridica1_idx` (`personaJuridica_idpersonaJuridica` ASC) VISIBLE,
  CONSTRAINT `fk_personaXjuridicaXFisica_personaFisica1`
    FOREIGN KEY (`personaFisica_idpersonaFisica`)
    REFERENCES `caso-2`.`personaFisica` (`idpersonaFisica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personaXjuridicaXFisica_personaJuridica1`
    FOREIGN KEY (`personaJuridica_idpersonaJuridica`)
    REFERENCES `caso-2`.`personaJuridica` (`idpersonaJuridica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`Persona` (
  `cedula` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`cedula`, `tipo`),
  INDEX `fk_Persona_personaXjuridicaXFisica1_idx` (`tipo` ASC) VISIBLE,
  CONSTRAINT `fk_Persona_personaXjuridicaXFisica1`
    FOREIGN KEY (`tipo`)
    REFERENCES `caso-2`.`personaXjuridicaXFisica` (`idpersonaXjuridicaXFisica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`cuentaCobrar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`cuentaCobrar` (
  `numero` INT NOT NULL,
  `monto` DECIMAL NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  `personaCedula` INT NOT NULL,
  PRIMARY KEY (`numero`, `personaCedula`),
  INDEX `fk_cuentaCobrar_Persona_idx` (`personaCedula` ASC) VISIBLE,
  CONSTRAINT `fk_cuentaCobrar_Persona`
    FOREIGN KEY (`personaCedula`)
    REFERENCES `caso-2`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`formaPago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`formaPago` (
  `codigo` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`Abono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`Abono` (
  `idAbono` INT NOT NULL,
  `cuota` DECIMAL NOT NULL,
  `fecha` DATE NOT NULL,
  `monto` DECIMAL NOT NULL,
  `formaPago_codigo` INT NOT NULL,
  `cuentaCobrarNumero` INT NOT NULL,
  PRIMARY KEY (`idAbono`, `cuota`, `formaPago_codigo`, `cuentaCobrarNumero`),
  INDEX `fk_Abono_formaPago1_idx` (`formaPago_codigo` ASC) VISIBLE,
  INDEX `fk_Abono_cuentaCobrar1_idx` (`cuentaCobrarNumero` ASC) VISIBLE,
  CONSTRAINT `fk_Abono_formaPago1`
    FOREIGN KEY (`formaPago_codigo`)
    REFERENCES `caso-2`.`formaPago` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abono_cuentaCobrar1`
    FOREIGN KEY (`cuentaCobrarNumero`)
    REFERENCES `caso-2`.`cuentaCobrar` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-2`.`cobroXabono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-2`.`cobroXabono` (
  `cuentaCobrar_numero` INT NOT NULL,
  `Abono_idAbono` INT NOT NULL,
  PRIMARY KEY (`cuentaCobrar_numero`, `Abono_idAbono`),
  INDEX `fk_cuentaCobrar_has_Abono_Abono1_idx` (`Abono_idAbono` ASC) VISIBLE,
  INDEX `fk_cuentaCobrar_has_Abono_cuentaCobrar1_idx` (`cuentaCobrar_numero` ASC) VISIBLE,
  CONSTRAINT `fk_cuentaCobrar_has_Abono_cuentaCobrar1`
    FOREIGN KEY (`cuentaCobrar_numero`)
    REFERENCES `caso-2`.`cuentaCobrar` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuentaCobrar_has_Abono_Abono1`
    FOREIGN KEY (`Abono_idAbono`)
    REFERENCES `caso-2`.`Abono` (`idAbono`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- ============================================================================
-- ============================================================================
-- Inserts

-- Tabla persona fisica
INSERT INTO personaFisica (idpersonaFisica, fechaNacimiento) VALUES
(0, '2000-01-01'),
(1, '1990-01-15'),
(2, '1985-05-20'),
(3, '1998-09-10'),
(4, '1976-03-02'),
(5, '2000-12-28'),
(6, '1993-07-14'),
(7, '1980-11-07'),
(8, '1995-04-30'),
(9, '1972-08-12'),
(10, '1999-06-25');

-- Tabla persona juridica
INSERT INTO personaJuridica (idpersonaJuridica, nombreComercial) VALUES
(1, 'TEC'),
(2, 'Chocolate adventure'),
(3, 'Pollos cyros'),
(4, 'Taco bell'),
(5, 'Brisas de la jungla'),
(6, 'Subway'),
(7, 'Mus mani'),
(8, 'Super la bendicion'),
(9, 'Maxi pali'),
(10, 'Tienda mia'),
(0, 'NULL');

-- Tabla personaXjuridicaXFisica
-- En esta tabla se unen persona con su tipo segun sea el caso.
-- Los 0 como id representan nulos, osease, es uno u otro.



INSERT INTO personaXjuridicaXFisica (idpersonaXjuridicaXFisica, personaFisica_idpersonaFisica, personaJuridica_idpersonaJuridica) VALUES
(1, 1, 0),
(2, 2, 0),
(3, 3, 0),
(4, 4, 0),
(5, 0, 5),
(6, 0, 6),
(7, 0, 7),
(8, 0, 8),
(9, 0, 9),
(10, 0, 10);

INSERT INTO Persona (cedula, nombre, tipo) VALUES
(123456070, 'Daniel', 1),
(155544400, 'Sandra', 2),
(700100100, 'Bryan', 3),
(703110076, 'Gadyr', 4),
(544872200, 'Juseline', 5),
(699858225, 'Pablo', 6),
(799652245, 'Jareck', 7),
(336581452, 'Fredd', 8),
(744125352, 'Alezka', 9),
(106985212, 'Devony', 10);


INSERT INTO cuentaCobrar (numero, monto, fechaVencimiento, personaCedula) VALUES
(1, 100.50, '2023-01-15', 123456070),
(2, 75.25, '2023-02-20', 155544400),
(3, 150.00, '2023-03-10', 700100100),
(4, 200.75, '2023-04-02', 703110076),
(5, 50.00, '2023-05-28', 544872200),
(6, 300.50, '2023-06-14', 799652245),
(7, 125.75, '2023-07-07', 699858225),
(8, 90.25, '2023-08-30', 336581452),
(9, 180.00, '2023-09-12', 106985212),
(10, 210.75, '2023-10-25', 744125352);

INSERT INTO formaPago (codigo, nombre) VALUES
(1, 'Efectivo'),
(2, 'Tarjeta de crédito'),
(3, 'Transferencia bancaria'),
(4, 'Tasa Cero'),
(5, 'PayPal'),
(6, 'Pagos con interes'),
(7, 'Apple Pay'),
(8, 'Prestamo'),
(9, 'Bizzum'),
(10, 'Sinpe');

INSERT INTO Abono (idAbono, cuota, fecha, monto, formaPago_codigo, cuentaCobrarNumero) VALUES
(1, 1, '2023-01-15', 50.00, 1, 1),
(2, 2, '2023-02-20', 35.00, 2, 1),
(3, 3, '2023-03-10', 70.00, 3, 1),
(4, 4, '2023-04-02', 90.25, 4, 3),
(5, 5, '2023-05-28', 25.00, 5, 2),
(6, 6, '2023-06-14', 150.00, 6, 4),
(7, 7, '2023-07-07', 60.75, 7, 7),
(8, 8, '2023-08-30', 45.50, 8, 8),
(9, 9, '2023-09-12', 90.00, 9, 9),
(10, 10, '2023-10-25', 105.25, 10, 9);



-- Selects para ver la información.

SELECT * FROM personaFisica;

SELECT * FROM personaJuridica;

SELECT * FROM personaXjuridicaXFisica;

SELECT * FROM cuentaCobrar;

SELECT * FROM formaPago;