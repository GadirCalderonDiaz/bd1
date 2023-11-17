package tec.bd.weather.service;


import tec.bd.weather.entity.ForecastDB;
import tec.bd.weather.entity.Logs;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.sql.Queries;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ForecastServiceImpl implements ForecastService {




    private Repository<ForecastDB, Integer> forecastRepository;


    public ForecastServiceImpl(Repository<ForecastDB, Integer> forecastRepository){
        this.forecastRepository = forecastRepository;
    }



    @Override
    public ForecastDB newFore(Integer cityID,Float temperature, String date) {

        if(cityID == null){
            throw new RuntimeException("The cityID its NULL");
        }
        var ForecastToBeSaved = new ForecastDB(null, cityID,0,date, temperature);
        return this.forecastRepository.save(ForecastToBeSaved);
    }


    @Override
    public List<ForecastDB> getAllForecast() {
        return this.forecastRepository.findAll();
    }


    @Override
    public List<ForecastDB> getAllForecastDate(String forecastDate) {
        return this.forecastRepository.findAllDate(forecastDate);
    }

    @Override
    public List<ForecastDB> getAllForecastZipCode(Integer zipCode) {
        return this.forecastRepository.findAllZipCode(zipCode);
    }

    @Override
    public List<ForecastDB> getAllForecastCityID(Integer cityID) {
        return this.forecastRepository.findAllCityID(cityID);
    }

    @Override
    public Optional<ForecastDB> getForecastByID(int forecastID) {

        if(forecastID > 0 ){
            return this.forecastRepository.findById(forecastID);
        }else{
            throw new RuntimeException("The ForecastID has to be greater than 0");

        }
    }

    @Override
    public void removeByForecastID(int forecastID) {

        var forecastFromDBOpt = this.getForecastByID(forecastID);

        if(forecastFromDBOpt.isEmpty()){
            throw new RuntimeException("The ForecastID:"+ forecastID + "\tis not found");
        }
        this.forecastRepository.delete(forecastID);

    }

    @Override
    public ForecastDB updateForecast(ForecastDB forecast) {
        //validar si el cityID existe en la base de datos


        var forecastIDFromDB = this.getForecastByID(forecast.getId());

        if(forecastIDFromDB.isEmpty()){
            throw new RuntimeException("The ForecastID:"+ forecast.getId() + "is not found");
        }

        var forecastUpdated = this.forecastRepository.update(forecast);

        return forecastUpdated;
    }

}
