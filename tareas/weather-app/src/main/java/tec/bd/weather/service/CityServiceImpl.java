package tec.bd.weather.service;

import tec.bd.weather.entity.City;
import tec.bd.weather.entity.State;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService{


    private Repository<City, Integer> cityRepository;


    public CityServiceImpl(Repository<City, Integer> cityRepository){
        this.cityRepository = cityRepository;
    }

    @Override
    public City newCity(String cityName, Integer zipcode, Integer stateID) {
        if(cityName.equals(" ")){
            throw new RuntimeException("The cityName it's NULL");
        }
        var cityToBeSaved = new City(null, cityName, zipcode,stateID);
        return this.cityRepository.save(cityToBeSaved);
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public Optional<City> getCityByID(int cityID) {
        if(cityID > 0 ){
            return this.cityRepository.findById(cityID);
        }else{
            throw new RuntimeException("The CityID has to be greater than 0");

        }
    }

    @Override
    public void removeByCityID(int cityID) {
        var cityFROMDB = this.getCityByID(cityID);

        if(cityFROMDB.isEmpty()){
            throw new RuntimeException("The CityID:"+ cityID + "is not found");
        }
        this.cityRepository.delete(cityID);

    }

    @Override
    public City updateCity(City city) {
        //validar si el cityID existe en la base de datos


        var cityIDFromDB = this.getCityByID(city.getId());

        if(cityIDFromDB.isEmpty()){
            throw new RuntimeException("The CityID:"+ city.getId() + "is not found");
        }

        var cityUpdated = this.cityRepository.update(city);

        return cityUpdated;
    }
}
