package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;

public interface WeatherService {

    float getCityTemperature(String city);
    float getZipCodeTemperature(String zipCode);

    void newForecast(Forecast forecast);

    Forecast updateForecast(Forecast forecast);

    void removeForecast(int forecastId);
}
