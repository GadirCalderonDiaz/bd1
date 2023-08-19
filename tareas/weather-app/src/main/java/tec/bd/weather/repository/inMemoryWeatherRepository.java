package tec.bd.weather.repository;

import tec.bd.weather.entity.Forecast;

import java.util.*;

public class inMemoryWeatherRepository implements  Repository<Forecast, Integer> {

    private Set<Forecast> inMemoryForecastData;

    public inMemoryWeatherRepository(){
        //INI datos
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new Forecast(1,"Limon","Costa Rica","0700",27.0f));
        this.inMemoryForecastData.add(new Forecast(2,"Alajuela","Costa Rica","0200",23.0f));
        this.inMemoryForecastData.add(new Forecast(3,"Heredia","Panamá","0400",25.0f));
        this.inMemoryForecastData.add(new Forecast(4,"Guanacaste","Colombia","0500",29.0f));

    }

    @Override
    public Optional <Forecast> findById(Integer id) {
        return this.inMemoryForecastData
                .stream()
                .filter(e -> Objects.equals(e.getId(),id))
                .findFirst();
    }

    @Override
    public List<Forecast> findAll() {
        return new ArrayList<>(this.inMemoryForecastData);
    }

    @Override
    public void save(Forecast forecast) {
            this.inMemoryForecastData.add(forecast);
    }

    @Override
    public void delete(Integer id) {
        var weatherToDelete = this.findById(id);
        this.inMemoryForecastData.remove(weatherToDelete.get());
    }

    @Override
    public Forecast update(Forecast source) {
        var current = this.findById(source.getId()).get();
        current.setCityName(source.getCityName());
        current.setZipCode(source.getZipCode());
        current.setTemperature(source.getTemperature());

        this.delete(current.getId());
        this.save(current);

        return current;
    }
}
