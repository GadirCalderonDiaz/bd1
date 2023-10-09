-- MySQL Script generated by MySQL Workbench
-- Sun Oct  8 20:05:59 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema caso-1
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `caso-1` ;
-- -----------------------------------------------------
-- Schema caso-1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `caso-1` DEFAULT CHARACTER SET utf8 ;
USE `caso-1` ;

-- -----------------------------------------------------
-- Table `caso-1`.`GERENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`GERENTE` (
  `CodigoGerente` INT AUTO_INCREMENT NOT NULL,
  `CedulaGerente` INT NOT NULL,
  `NombreGerente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CodigoGerente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-1`.`PRESIDENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`PRESIDENTE` (
  `CodigoPresi` INT AUTO_INCREMENT NOT NULL,
  `CedulaPresi` INT NOT NULL,
  `NombrePresi` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CodigoPresi`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-1`.`COMPAÑÍA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`COMPAÑÍA` (
  `IdCompañia` INT AUTO_INCREMENT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Dirección` VARCHAR(200) NOT NULL,
  `NumeroPatronal` INT NOT NULL,
  `CodigoGerente` INT NOT NULL,
  `CodigoPresi` INT NOT NULL,
  PRIMARY KEY (`IdCompañia`),
  CONSTRAINT `CodigoGerente`
    FOREIGN KEY (`CodigoGerente`)
    REFERENCES `caso-1`.`GERENTE` (`CodigoGerente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CodigoPresi`
    FOREIGN KEY (`CodigoPresi`)
    REFERENCES `caso-1`.`PRESIDENTE` (`CodigoPresi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `CodigoGerente_idx` ON `caso-1`.`COMPAÑÍA` (`CodigoGerente` ASC) VISIBLE;

CREATE INDEX `CodigoPresi_idx` ON `caso-1`.`COMPAÑÍA` (`CodigoPresi` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `caso-1`.`SUCURSAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`SUCURSAL` (
  `ID_Sucursal` INT AUTO_INCREMENT NOT NULL,
  `IdCompañia` INT NOT NULL,
  `NombreSucursal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Sucursal`),
  CONSTRAINT `IdCompañia`
    FOREIGN KEY (`IdCompañia`)
    REFERENCES `caso-1`.`COMPAÑÍA` (`IdCompañia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `IdCompañia_idx` ON `caso-1`.`SUCURSAL` (`IdCompañia` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `caso-1`.`SEGMENTO_CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`SEGMENTO_CLIENTE` (
  `ID_Segmento` INT AUTO_INCREMENT NOT NULL,
  `Descripción` VARCHAR(200) NOT NULL,
  `NombreGerente` VARCHAR(45) NOT NULL,
  `UltimaVisitaDoctor` DATE NOT NULL,
  PRIMARY KEY (`ID_Segmento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-1`.`CONTACTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`CONTACTO` (
  `CodigoContacto` INT AUTO_INCREMENT NOT NULL,
  `NombreConta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CodigoContacto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-1`.`PROVEEDOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`PROVEEDOR` (
  `CodigoProve` INT AUTO_INCREMENT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `CedulaProve` INT NOT NULL,
  `TipoProve` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  `DireccionProve` VARCHAR(205) NOT NULL,
  `CodigoConta` INT NOT NULL,
  PRIMARY KEY (`CodigoProve`),
  CONSTRAINT `CodigoConta`
    FOREIGN KEY (`CodigoConta`)
    REFERENCES `caso-1`.`CONTACTO` (`CodigoContacto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `CodigoConta_idx` ON `caso-1`.`PROVEEDOR` (`CodigoConta` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `caso-1`.`ARTICULO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`ARTICULO` (
  `CodigoArti` INT AUTO_INCREMENT NOT NULL,
  `DescripcionArti` VARCHAR(200) NOT NULL,
  `TipoArticulo` VARCHAR(45) NOT NULL,
  `PrecioUnitario` INT NOT NULL,
  `PrecioVenta` INT NOT NULL,
  `Stock` VARCHAR(45) NOT NULL,
  `FechaUltimaCompra` DATE NOT NULL,
  `FechaUltimoInventario` DATE NOT NULL,
  PRIMARY KEY (`CodigoArti`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caso-1`.`ORDEN_PEDIDO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`ORDEN_PEDIDO` (
  `Num_Orden` INT AUTO_INCREMENT NOT NULL,
  `FechaOrden` DATE NOT NULL,
  `UsuarioCompra` VARCHAR(45) NOT NULL,
  `UsuarioAproba` VARCHAR(45) NOT NULL,
  `MontoTotal` INT NOT NULL,
  `CodigoPro` INT NOT NULL,
  PRIMARY KEY (`Num_Orden`),
  CONSTRAINT `CodigoPro`
    FOREIGN KEY (`CodigoPro`)
    REFERENCES `caso-1`.`PROVEEDOR` (`CodigoProve`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `CodigoPro_idx` ON `caso-1`.`ORDEN_PEDIDO` (`CodigoPro` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `caso-1`.`LINEA_PEDIDO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caso-1`.`LINEA_PEDIDO` (
  `NumeLinea` INT AUTO_INCREMENT NOT NULL,
  `NumOr` INT NOT NULL,
  `CodiArti` INT NOT NULL,
  `CantidadOrdenada` INT NOT NULL,
  `PrecioArticuNego` INT NOT NULL,
  `TotalLinea` INT NOT NULL,
  PRIMARY KEY (`NumeLinea`),
  CONSTRAINT `NumOrden`
    FOREIGN KEY (`NumOr`)
    REFERENCES `caso-1`.`ORDEN_PEDIDO` (`Num_Orden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CodigoArti`
    FOREIGN KEY (`CodiArti`)
    REFERENCES `caso-1`.`ARTICULO` (`CodigoArti`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `NumOrden_idx` ON `caso-1`.`LINEA_PEDIDO` (`NumOr` ASC) VISIBLE;

CREATE INDEX `CodigoArti_idx` ON `caso-1`.`LINEA_PEDIDO` (`CodiArti` ASC) VISIBLE;



-- -----------------------------------------------------
-- TABLA GERENTE--
-- -----------------------------------------------------
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(703080762, 'Gadyr Calderon');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(701230321, 'Juan Calderon');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(703210123, 'Salomon Calderon');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(103080762, 'Andres Calderon');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(203080762, 'Lionel Messi');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(303080762, 'Ronaldo Nazario');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(403080762, 'Fredd Badilla');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(503080762, 'Jareck Levell');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(603080762, 'Pepe Rojas');
INSERT INTO GERENTE (cedulaGerente, nombreGerente) VALUES(705410123, 'Danny Smith');

-- -----------------------------------------------------
-- TABLA PRESIDENTE--
-- -----------------------------------------------------
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(705410121, 'Danny Yash');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(701230322, 'Juan Diaz');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(703210124, 'Salomon Diaz');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(103080765, 'Andres Diaz');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(203080766, 'Lionel Cuccittini');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(303080767, 'Ronaldo Dos Santos');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(403080768, 'Fredd Viquez');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(503080760, 'Jareck Porras');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(603080761, 'Pepe Jimenez');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(705410122, 'Danny Balotelli');
INSERT INTO PRESIDENTE (cedulaPresi, nombrePresi) VALUES(703080762, 'Gadyr Calderon');

-- -----------------------------------------------------
-- TABLA COMPAÑÍA--
-- -----------------------------------------------------
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Brisas De La Jungla', 'Rio Blanco, Limon, 3 kilometros carretera hacia blanco, entrada a mano derecha',3, 1, 11);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Super Leo', 'Rio Blanco',4, 2, 1);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Bar Trochita','Blanco',5, 3, 2);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Bar Pegadero','Blanco',6, 4, 3);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Restaurante Bambu','Blanco',7, 5, 4);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Sintetica Iguana','Blanco',8, 6, 5);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Pollos hermanos','Blanco',9, 7, 6);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Super sol','Blanco',10, 8, 7);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Super Container','Blanco',11, 9, 8);
INSERT INTO COMPAÑÍA (Nombre, Dirección, NumeroPatronal, CodigoGerente, CodigoPresi) VALUES('Super Ericka','Blanco',12, 10, 9);

-- -----------------------------------------------------
-- TABLA SUCURSAL--
-- -----------------------------------------------------
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(1, 'Familia Calderon');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(2, 'Los Pepes');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(3, 'Cartel Cali');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(4, 'Cartel L2000');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(5, 'Cartel JNG');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(6, 'Los extraditables');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(7, 'JGL');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(8, 'Los chupitos');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(9, 'Cartel Sinaloa');
INSERT INTO SUCURSAL (IdCompañia, NombreSucursal) VALUES(10, 'los de la 7');

-- -----------------------------------------------------
-- TABLA SEGMENTO_CLIENTE--
-- -----------------------------------------------------
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente fijo', 'Gadyr', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente viejo', 'Danny Smith', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente nuevo', 'Fredd Viquez', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Departamento Brisas', 'Ronaldo Dos Santos', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Departamento EO', 'Jareck Porras', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Departamento CEO', 'Pepe Jimenez', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Departamento TROCHITA', 'Danny Balotelli', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente DEP', 'Danny Smith', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Departamento JULIAN', 'Lionel Cuccittini', '2023-2-12');
INSERT INTO SEGMENTO_CLIENTE (Descripción, NombreGerente, UltimaVisitaDoctor) VALUES('Cliente Dep NW', 'Gadyr Calderon', '2023-2-12');

-- -----------------------------------------------------
-- TABLA CONTACTO--
-- -----------------------------------------------------
INSERT INTO CONTACTO (NombreConta) VALUES('Gadyr Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Andres Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Andre Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Fredd Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Jareck Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Lionel Calderon');
INSERT INTO CONTACTO (NombreConta) VALUES('Dawen Watson');
INSERT INTO CONTACTO (NombreConta) VALUES('Dilan Zuñiga');
INSERT INTO CONTACTO (NombreConta) VALUES('John Montero');
INSERT INTO CONTACTO (NombreConta) VALUES('Justin Rojas');
-- -----------------------------------------------------
-- TABLA PROVEEDOR--
-- -----------------------------------------------------
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Justin Rojas', 203080721, 'Granos', '8598-40721', 'Rio blanco', 10);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Jareck Calderon', 203080721, 'licores', '8598-40721', 'Rio blanco', 9);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('John Montero', 203080721, 'lacteos', '8598-40721', 'Rio blanco', 8);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Dilan Zuñiga', 203080721, 'equipo oficina', '8598-40721', 'Rio blanco', 7);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Dawen Watson', 203080721, 'lacteos', '8598-40721', 'Rio blanco', 6);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Lionel Calderon', 203080721, 'licores', '8598-40721', 'Rio blanco', 5);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Fredd Calderon', 203080721, 'Granos', '8598-40721', 'Rio blanco', 4);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Andre Calderon', 203080721, 'licores', '8598-40721', 'Rio blanco', 3);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Andres Calderon', 203080721, 'licores', '8598-40721', 'Rio blanco', 2);
INSERT INTO PROVEEDOR (Nombre, CedulaProve, TipoProve, Telefono, DireccionProve, CodigoConta) VALUES('Gadyr Calderon', 203080721, 'licores', '8598-40721', 'Rio blanco', 1);

-- -----------------------------------------------------
-- TABLA ARTICULO--
-- -----------------------------------------------------
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de arroz','granos',100,1000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de Arroz negro','granos',1000,10000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de Arroz integral','granos',1000,10000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de Arroz rojo','granos',1000,10000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de Mijo','granos',1000,10000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de Quinua','granos',1000,11000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de silver','licores',1200,24000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de pilsen','licores',1000,20000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de cocacola','licores',1000,20000,'lleno','2023-9-12', '2023-9-9');
INSERT INTO ARTICULO (DescripcionArti, TipoArticulo, PrecioUnitario, PrecioVenta, Stock, FechaUltimaCompra, FechaUltimoInventario) VALUES('paquete de fanta','refrescos',1000,20000,'lleno','2023-9-12', '2023-9-9');


-- -----------------------------------------------------
-- TABLA ORDEN_PEDIDO
-- -----------------------------------------------------
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Justin Diaz', 'Gadyr Calderon', '30000', 1);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dawen Watson', 'Gadyr Calderon', '29000', 2);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dilan Zuñiga', 'Gadyr Calderon', '28000', 3);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Andre Calderon', 'Gadyr Calderon', '27000', 4);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dawen Watson', 'Gadyr Calderon', '26000', 5);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dilan Zuñiga', 'Gadyr Calderon', '25000', 6);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dawen Watson', 'Gadyr Calderon', '24000', 7);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Andre Diaz', 'Gadyr Calderon', '23000', 8);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dilan Zuñiga', 'Gadyr Calderon', '22000', 9);
INSERT INTO ORDEN_PEDIDO (FechaOrden, UsuarioCompra, UsuarioAproba, MontoTotal, CodigoPro) VALUES('2023-10-8', 'Dilan Zuñiga', 'Gadyr Calderon', '21000', 10);

-- -----------------------------------------------------
-- TABLA LINEA_PEDIDO
-- -----------------------------------------------------
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(1,1, 20, 1000, 20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(2,2,11,10000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(3,3,12,10000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(4,4,13,10000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(5,5,14,10000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(6,6,15,11000, 22000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(7,7,16,24000,24000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(8,8,17,20000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(9,9,18,20000,20000);
INSERT INTO LINEA_PEDIDO (NumOr,CodiArti,CantidadOrdenada,PrecioArticuNego,TotalLinea) VALUES(10,10,19,20000,20000);



-- -----------------------------------------------------
-- Selects de tablas
-- -----------------------------------------------------
SELECT * FROM GERENTE;
SELECT * FROM PRESIDENTE;
SELECT * FROM COMPAÑÍA;
SELECT * FROM SUCURSAL;
SELECT * FROM SEGMENTO_CLIENTE;
SELECT * FROM CONTACTO;
SELECT * FROM PROVEEDOR;
SELECT * FROM ARTICULO;
SELECT * FROM ORDEN_PEDIDO;
SELECT * FROM LINEA_PEDIDO;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
