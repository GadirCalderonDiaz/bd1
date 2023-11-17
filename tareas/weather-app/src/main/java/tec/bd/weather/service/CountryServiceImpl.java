package tec.bd.weather.service;

import tec.bd.weather.entity.Country;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    private Repository<Country, Integer> countryRepository;


    public CountryServiceImpl(Repository<Country, Integer> countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public Country newCountry(String countryName) {
        if(countryName.equals(" ")){
            throw new RuntimeException("The CountryName its NULL");
        }
        var countryToBeSaved = new Country(null, countryName);
        return this.countryRepository.save(countryToBeSaved);
    }

    @Override
    public List<Country> getAllCountries() {
        return this.countryRepository.findAll();

    }

    @Override
    public Optional<Country> getCountryByID(int CountryID) {

        if(CountryID > 0 ){
            return this.countryRepository.findById(CountryID);
        }else{
            throw new RuntimeException("The CountryID has to be greater than 0");

        }
    }

    @Override
    public void removeByCountryID(int countryID) {

        var countryFromDBOpt = this.getCountryByID(countryID);

        if(countryFromDBOpt.isEmpty()){
            throw new RuntimeException("The CountryID:"+ countryID + "is not found");
        }
        this.countryRepository.delete(countryID);


    }

    @Override
    public Country updateCountry(Country country) {

        //validar si el countryID existe en la base de datos
        //si el nombre existe en la db

        var countryIDFromDBOpt = this.getCountryByID(country.getId());

        if(countryIDFromDBOpt.isEmpty()){
            throw new RuntimeException("The CountryID:"+ country.getId() + "is not found");
        }

        var countryUpdated = this.countryRepository.update(country);

        return countryUpdated;
    }

}
