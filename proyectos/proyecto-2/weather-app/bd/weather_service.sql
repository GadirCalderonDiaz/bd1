
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS state;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS forecast;
DROP TABLE IF EXISTS forecast_log;

-- Crea la base de datos weather_service
CREATE DATABASE IF NOT EXISTS weather_service;

-- Selecciona la base de datos recién creada
USE weather_service;


CREATE USER 'weatherappuser'@'localhost'IDENTIFIED BY 'weatherapppass';
GRANT INSERT, UPDATE, DELETE, EXECUTE ON weather_service.* TO 'weatherappuser'@'localhost';



-- Crea la tabla country
CREATE TABLE country (
    CountryID INT AUTO_INCREMENT PRIMARY KEY,
    CountryName VARCHAR(50) NOT NULL UNIQUE
);

-- Crea la tabla State
CREATE TABLE state(
	StateID INT AUTO_INCREMENT PRIMARY KEY,
    StateName VARCHAR(50) NOT NULL, 
    CountryID INT,
    FOREIGN KEY (CountryID) REFERENCES country(CountryID)

);

-- Crea la tabla City
CREATE TABLE city(
	CityID INT AUTO_INCREMENT PRIMARY KEY,
    CityName VARCHAR(50) NOT NULL,
    ZipCode INT NOT NULL UNIQUE, 
    StateID INT,
    FOREIGN KEY (StateID) REFERENCES state(StateID)

);

CREATE TABLE forecast(
    Forecast_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    CityID INT NOT NULL,
    ZipCode INT NOT NULL,
    Temperature FLOAT NOT NULL,
    Forecast_date VARCHAR(50) NOT NULL,
	INDEX ICode(ZipCode),
    FOREIGN KEY (CityID) REFERENCES city(CityID)
    
    
);

CREATE TABLE forecast_log(
    Log_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    Last_modified_on VARCHAR(50) NOT NULL,
    Forecast_id INT NOT NULL,
    Entry_text VARCHAR(50) NOT NULL
    
    
);

-- CRUDs
-- -------------------------------------
-- #######################################################################
-- ##################### CRUD country#######################################
-- ######################################################################
-- insert Country
DELIMITER $$
CREATE PROCEDURE CreateNewCountry(IN p_countryName VARCHAR(50), OUT p_new_countryID INT)
BEGIN
    ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
    INSERT INTO country(CountryName)values(p_countryName);
    SELECT last_insert_id() INTO p_new_countryID;
COMMIT;
END $$
DELIMITER ;

DROP PROCEDURE CreateNewCountry;
CALL CreateNewCountry('España', @p_new_CountryID);
SELECT * FROM country;
-- -------------------

-- read Countries
DELIMITER $$
CREATE PROCEDURE findAllCountries(IN p_countryID INT)
BEGIN
   IF(p_countryID <= 0) THEN 
    SELECT CountryID, CountryName FROM country order by CountryID asc;
    ELSE 
    SELECT * FROM country WHERE CountryID = p_countryID;
    END IF;
END $$
DELIMITER ;

DROP PROCEDURE findAllCountries;
CALL findAllCountries(0);
SELECT * FROM country;

-- -------------------
-- ################################################
DELIMITER $$
CREATE PROCEDURE findCountryByName(IN p_countryName VARCHAR(50))
BEGIN
    SELECT * FROM country WHERE CountryName = p_countryName;
END $$
DELIMITER ;
Call findCountryByName('Costa Rica');
-- ################################################


-- ----------------------
-- --------------------
-- Delete Countries
DELIMITER $$
CREATE PROCEDURE deleteCountry(IN p_countryID INT)
BEGIN
    ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	DELETE FROM country WHERE CountryID = p_countryID;
COMMIT;  
END $$
DELIMITER ;

CALL deleteCountry(3);

-- -------------------
-- ----------------------
-- --------------------
-- updateCountry
DELIMITER $$
CREATE PROCEDURE updateCountry(IN p_countryID INT, IN p_countryName VARCHAR(50))
BEGIN
   ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	UPDATE country SET CountryName = p_countryName WHERE CountryID = p_countryID;
COMMIT;
END $$
DELIMITER ;


DROP PROCEDURE updateCountry;
CALL updateCountry(2, 'China');


-- #######################################################################
-- ######################## CRUD State######################################
-- ######################################################################
DELIMITER $$
CREATE PROCEDURE CreateNewState(IN p_stateName VARCHAR(50), IN p_countryID INT ,OUT p_new_stateID INT)
BEGIN
	 ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
    INSERT INTO state(stateName, CountryID)values(p_stateName, p_countryID );
    SELECT last_insert_id() INTO p_new_stateID;
    
COMMIT;
END $$
DELIMITER ;

DROP PROCEDURE CreateNewState;
CALL CreateNewState('Tegucigalpa',1, @p_new_stateID);
SELECT * FROM state;
-- -------------------


-- read States
DELIMITER $$
CREATE PROCEDURE findAllStates(IN p_stateID INT)
BEGIN
   IF(p_stateID <= 0) THEN 
    SELECT StateID, StateName, CountryID FROM state order by StateID asc;
    ELSE 
    SELECT * FROM state WHERE StateID = p_stateID;
    END IF;
END $$
DELIMITER ;

DROP PROCEDURE findAllStates;
CALL findAllStates(0);
SELECT * FROM state;
-- -------------------
-- ----------------------
-- --------------------
-- Delete State
DELIMITER $$
CREATE PROCEDURE deleteState(IN p_stateID INT)
BEGIN
     ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	DELETE FROM state WHERE StateID = p_stateID;

COMMIT;
END $$
DELIMITER ;

CALL deleteState(3);

-- -------------------
-- ----------------------
-- --------------------
-- updateState
DELIMITER $$
CREATE PROCEDURE updateState(IN p_stateID INT, IN p_stateName VARCHAR(50), IN p_countryID INT)
BEGIN
    ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	UPDATE state SET StateName = p_stateName , CountryID = p_countryID WHERE StateID = p_stateID;
 
COMMIT;
END $$
DELIMITER ;


DROP PROCEDURE updateState;
CALL updateState();


-- #######################################################################
-- ######################## CRUD City######################################
-- ######################################################################
DELIMITER $$
CREATE PROCEDURE CreateNewCity(IN p_cityName VARCHAR(50), IN p_zipcode INT , IN p_stateID INT ,OUT p_new_cityID INT)
BEGIN
    ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
    INSERT INTO city(CityName, ZipCode, StateID)values(p_cityName, p_zipcode, p_stateID );
    SELECT last_insert_id() INTO p_new_cityID;
    
COMMIT;
END $$
DELIMITER ;

DROP PROCEDURE CreateNewCity;
CALL CreateNewCity('San Jose',5555,2, @p_new_cityID);
SELECT * FROM city;
-- -------------------


-- read cities
DELIMITER $$
CREATE PROCEDURE findAllCities(IN p_cityID INT)
BEGIN
   IF(p_cityID <= 0) THEN 
    SELECT CityID, CityName, ZipCode, StateID FROM city order by CityID asc;
    ELSE 
    SELECT * FROM city WHERE CityID = p_cityID;
    END IF;
END $$
DELIMITER ;

DROP PROCEDURE findAllCities;
CALL findAllCities(1);
SELECT * FROM city;
-- -------------------
-- ----------------------
-- --------------------
-- Delete City
DELIMITER $$
CREATE PROCEDURE deleteCity(IN p_cityID INT)
BEGIN
    ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	DELETE FROM city WHERE CityID = p_cityID;

COMMIT;
END $$
DELIMITER ;

CALL deleteCity(1);

-- -------------------
-- ----------------------
-- --------------------
-- updateCity
DELIMITER $$
CREATE PROCEDURE updateCity(IN p_cityID INT, IN p_cityName VARCHAR(50),IN p_zipcode INT, IN p_stateID INT)
BEGIN
	 ROLLBACK;
	-- Inicia la transacción
	START TRANSACTION;
	UPDATE city SET CityName = p_cityName, ZipCode =p_zipcode,  StateID = p_stateID WHERE CityID = p_cityID;

COMMIT;
END $$
DELIMITER ;


DROP PROCEDURE updateCity;
CALL updateCity(2, 'Murcia', 7777, 2);
SELECT * FROM city;

-- #######################################################################
-- ######################## CRUD Forecast######################################
-- ######################################################################
DELIMITER $$
CREATE PROCEDURE findZipcode(IN p_cityID INT,OUT p_new_zipcodeID INT )
BEGIN
    SELECT ZipCode INTO p_new_zipcodeID FROM city WHERE CityID = p_cityID;
   

END $$
DELIMITER ;

DROP PROCEDURE findZipcode;
CALL findZipcode(3, @p_new_zipcodeID);
SELECT @p_new_zipcodeID;
-- ###############################
-- encontrar el nombre de la cuidad y el codigo del estado
-- ##############################
DELIMITER $$
CREATE PROCEDURE findCityName(IN p_cityID INT, OUT p_cityName VARCHAR(50))
BEGIN
    SELECT CityName INTO p_cityName FROM city WHERE CityID = p_cityID;
END $$
DELIMITER ;

DROP PROCEDURE findCityName;
CALL findCityName(3, @p_cityName);
SELECT @p_cityName;
-- ###############################
-- #########################
DELIMITER $$
CREATE PROCEDURE findStateID(IN p_cityID INT, OUT p_stateID INT)
BEGIN
    SELECT StateID INTO p_stateID FROM city  WHERE CityID = p_cityID;
END $$
DELIMITER ;

DROP PROCEDURE findStateID;
CALL findStateID(3, @p_stateID);
SELECT @p_stateID;

-- ###############################
-- encontrar el nombre del estado y el codigo del pais
-- ##############################
DELIMITER $$
CREATE PROCEDURE findStateName(IN p_stateID INT,OUT p_stateName VARCHAR(50))
BEGIN
    SELECT StateName INTO p_stateName FROM state WHERE StateID = p_stateID;
   
END $$
DELIMITER ;

DROP PROCEDURE findStateName;
CALL findStateName(1, @p_stateName);
SELECT @p_stateName;
-- ###################################
-- ###################################
DELIMITER $$
CREATE PROCEDURE findCountryID(IN p_stateID INT, OUT p_countryID INT)
BEGIN
    SELECT CountryID INTO p_countryID FROM state  WHERE StateID = p_stateID;
   
END $$
DELIMITER ;
DROP PROCEDURE findCountryID;
CALL findCountryID(1,@p_countryID);
SELECT @p_countryID;
-- ###############################
-- encontrar el nombre del pais
-- ##############################
DELIMITER $$
CREATE PROCEDURE findCountryName(IN p_countryID INT,OUT p_countryName VARCHAR(50))
BEGIN
    SELECT CountryName INTO p_countryName FROM country WHERE CountryID = p_countryID;
   
END $$
DELIMITER ;

DROP PROCEDURE findCountryName;
CALL findCountryName(2, @p_countryName);
SELECT @p_countryName;


-- ##########################################
-- createForecast
-- ########################################
DELIMITER $$
CREATE PROCEDURE CreateNewForecast(IN p_cityID INT , IN p_zipcode INT , IN p_temperature FLOAT , IN p_fecha DATE ,OUT p_new_forecastID INT)
BEGIN
ROLLBACK;
-- Inicia la transacción
START TRANSACTION;
INSERT INTO forecast (CityID, ZipCode, Temperature, Forecast_date)VALUES (p_cityID, p_zipcode, p_temperature, p_fecha);
SELECT last_insert_id() INTO p_new_forecastID;


-- Confirma la transacción
COMMIT;
END $$
DELIMITER ;

DROP PROCEDURE CreateNewForecast;
CALL CreateNewForecast(4,5555,10.0,current_date(),@p_new_cityID);
SELECT @p_cityName;
SELECT * FROM forecast_log;
-- -------------------


-- read forecast
DELIMITER $$
CREATE PROCEDURE findAllForecast(IN p_forecastDate VARCHAR(50))
BEGIN
    SELECT Forecast_id, CityID, ZipCode, Temperature, Forecast_date FROM forecast WHERE Forecast_date = p_forecastDate  order by Forecast_id asc;
END $$
DELIMITER ;

DROP PROCEDURE findAllForecast;
CALL findAllForecast('2023-10-10');
SELECT * FROM forecast;
-- -------------------
-- ##########################
DELIMITER $$
CREATE PROCEDURE findAllForecastZipCode(IN p_zipCode  INT)
BEGIN
    SELECT Forecast_id, CityID, ZipCode, Temperature, Forecast_date FROM forecast WHERE ZipCode = p_zipCode  order by Forecast_id asc;
END $$
DELIMITER ;
DROP PROCEDURE findAllForecastZipCode;
CALL findAllForecastZipCode(7777);
SELECT * FROM forecast;
-- ###########################################
-- ###########################################
DELIMITER $$
CREATE PROCEDURE findAllForecastCityCode(IN p_cityCode  INT)
BEGIN
    SELECT Forecast_id, CityID, ZipCode, Temperature, Forecast_date FROM forecast WHERE CityID = p_cityCode  order by Forecast_id asc;
END $$
DELIMITER ;
DROP PROCEDURE findAllForecastCityCode;
CALL findAllForecastCityCode(3);
SELECT * FROM forecast;



-- ----------------------
-- --------------------
-- Delete forecast
DELIMITER $$
CREATE PROCEDURE deleteForecast(IN p_forecastID INT)
BEGIN
    ROLLBACK;
-- Inicia la transacción
	START TRANSACTION;
	DELETE FROM forecast WHERE Forecast_id = p_forecastID;
    
   COMMIT;
END $$
DELIMITER ;

CALL deleteForecast(1);

-- -------------------


DELIMITER $$
CREATE PROCEDURE findForecastBYID(IN p_forecastID INT)
BEGIN
    SELECT * FROM forecast WHERE Forecast_id = p_forecastID;
END $$
DELIMITER ;

DROP PROCEDURE findForecastBYID;
CALL findForecastBYID(2);
SELECT * FROM forecast;


-- ----------------------
-- --------------------
-- updateCity
DELIMITER $$
CREATE PROCEDURE updateForecast(IN p_forecastID INT,IN p_cityID INT,IN p_zipcode INT, IN p_temperature FLOAT, IN p_date VARCHAR(50))
BEGIN
	ROLLBACK;
   	-- Inicia la transacción
	START TRANSACTION;
	UPDATE forecast SET CityID = p_cityID, ZipCode =p_zipcode,  Temperature = p_temperature, Forecast_date = p_date WHERE Forecast_id = p_forecastID;

COMMIT;
END $$
DELIMITER ;

DROP PROCEDURE updateForecast;
CALL updateForecast(1, 4, 2222, 20, '2023-03-12');
SELECT * FROM forecast;
-- ################################################
-- ################################################
-- ###############################################
DELIMITER $$
CREATE PROCEDURE GetLastForecastLogs(IN N INT)
BEGIN
    SELECT *
    FROM forecast_log
    ORDER BY Last_modified_on DESC
    LIMIT N;
END $$

DELIMITER ;
CALL GetLastForecastLogs(1);
-- ############################################
-- ###############TRIGGERS###############
-- #####################################
-- Trigger AFTER INSERT
CALL CreateNewForecast(2,7237,12.0,current_date(),@p_cityName);

CREATE TRIGGER after_forecast_insert
AFTER INSERT ON forecast
FOR EACH ROW
INSERT INTO forecast_log (Last_modified_on, forecast_id, Entry_text)
VALUES ( NEW.Forecast_date ,NEW.forecast_id, 'New Forecast');

DROP TRIGGER after_forecast_insert;


SELECT * FROM forecast_log;
SELECT * FROM forecast;
SELECT * FROM city;




-- Trigger AFTER UPDATE
CREATE TRIGGER after_forecast_update
AFTER UPDATE ON forecast
FOR EACH ROW
INSERT INTO forecast_log (Last_modified_on, forecast_id, Entry_text)
VALUES ( NEW.Forecast_date ,NEW.forecast_id, 'Update');

-- Trigger AFTER DELETE
CREATE TRIGGER after_forecast_delete
AFTER DELETE ON forecast
FOR EACH ROW
INSERT INTO forecast_log (Last_modified_on, forecast_id, Entry_text)
VALUES ( OLD.Forecast_date ,OLD.forecast_id, 'Forecast Delete');


SET autocommit = OFF;













