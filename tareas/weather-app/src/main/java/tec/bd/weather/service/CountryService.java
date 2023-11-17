package tec.bd.weather.service;

import tec.bd.weather.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Country newCountry(String countryName);

    List<Country> getAllCountries();

    Optional<Country> getCountryByID(int countryID);

    void removeByCountryID(int countryID);

    Country updateCountry(Country country);
}
