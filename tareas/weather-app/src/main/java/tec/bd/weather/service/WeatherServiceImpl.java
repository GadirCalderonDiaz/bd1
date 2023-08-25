package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

public class WeatherServiceImpl implements WeatherService {


    private Repository<Forecast, Integer> weatherRepository;

    public WeatherServiceImpl(Repository<Forecast, Integer> weatherRepository) {
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
    public void newForecast(Forecast forecast) {
        Forecast.validate(forecast);

        var current = this.weatherRepository.findById(forecast.getId());
        if(current.isPresent()){
            throw new RuntimeException("Weather forecast ID already exists in database");
        }
        this.weatherRepository.save(forecast);

    }

    public Forecast updateForecast(Forecast forecast){
        Forecast.validate(forecast);
        var current = this.weatherRepository.findById(forecast.getId());
        if(current.isEmpty()){
            throw  new RuntimeException("Weather forecast id doesn't exists in database");
        }
        return  this.weatherRepository.update(forecast);
    }

    public void removeForecast(int forecastId){
        var forecastToDelete = this.weatherRepository.findById(forecastId);
        if(forecastToDelete.isEmpty()){
            throw  new RuntimeException("forecastID doesn't exists");
        }
        this.weatherRepository.delete(forecastId);

    }
}
