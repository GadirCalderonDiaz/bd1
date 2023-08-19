package tec.bd.weather.service;

import tec.bd.weather.entity.Weather;
import tec.bd.weather.repository.Repository;


import static java.util.Optional.*;

public class WeatherServiceImpl implements WeatherService {


    private Repository<Weather, Integer> weatherRepository;

    public WeatherServiceImpl(Repository<Weather, Integer>weatherRepository) {
            this.weatherRepository = weatherRepository;
    }

    @Override
    public float getCityTemperature(String city) {
        var weather =this.weatherRepository
                .findAll()
                .stream()
                .filter(e ->e.getCityName().equals(city))
                .findFirst()
                .orElseThrow(()->new RuntimeException(city + "is not supoorted"));
        return weather.getTemperature();
    }

    @Override
    public float getZipCodeTemperature(String zipCode) {
            var weather = this.weatherRepository
                    .findAll()
                    .stream()
                    .filter(e-> e.getZipCode().equals(zipCode))
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException(zipCode + "is not supported"));
        return weather.getTemperature();
    }

    @Override
    public void newForeCast(Weather weather) {
        Weather.validate(weather);

        var current = this.weatherRepository.findById(weather.getId());
        if(current.isPresent()){
            throw new RuntimeException("Weather forecast ID already exists in database");
        }
        this.weatherRepository.save(weather);

    }

    public Weather updateForecast(Weather weather){
        Weather.validate(weather);
        var current = this.weatherRepository.findById(weather.getId());
        if(current.isEmpty()){
            throw  new RuntimeException("Weather forecast id doesn't exists in database");
        }
        return  this.weatherRepository.update(weather);
    }

    public void removeForecast(int forecastId){
        var forecastToDelete = this.weatherRepository.findById(forecastId);
        if(forecastToDelete.isEmpty()){
            throw  new RuntimeException("forecastID doesn't exists");
        }
        this.weatherRepository.delete(forecastId);

    }
}
