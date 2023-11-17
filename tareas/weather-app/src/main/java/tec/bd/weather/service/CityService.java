package tec.bd.weather.service;

import tec.bd.weather.entity.City;
import tec.bd.weather.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City newCity(String cityName, Integer zipcode, Integer stateID);

    List<City> getAllCities();

    Optional<City> getCityByID(int cityID);

    void removeByCityID(int cityID);

    City updateCity(City city);

}
