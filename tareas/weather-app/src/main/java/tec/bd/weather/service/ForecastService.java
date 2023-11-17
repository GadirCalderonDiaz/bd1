package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.entity.ForecastDB;
import tec.bd.weather.entity.Logs;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ForecastService {

    ForecastDB newFore(Integer cityID, Float temperature, String date);

    List<ForecastDB> getAllForecast();

    List<ForecastDB> getAllForecastDate(String forecastDate);

    List<ForecastDB> getAllForecastZipCode(Integer zipCode);

    List<ForecastDB> getAllForecastCityID(Integer cityID);


    Optional<ForecastDB> getForecastByID(int forecastID);

    void removeByForecastID(int forecastID);

    ForecastDB updateForecast(ForecastDB forecast);


}
