package tec.bd.weather.service;

import tec.bd.weather.entity.Weather;

public interface WeatherService {

    float getCityTemperature(String city);
    float getZipCodeTemperature(String zipCode);

    void newForeCast(Weather weather);

    Weather updateForecast(Weather weather);

    void removeForecast(int forecastId);
}
