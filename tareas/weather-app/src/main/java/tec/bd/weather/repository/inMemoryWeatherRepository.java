package tec.bd.weather.repository;

import tec.bd.weather.entity.Weather;

import java.util.*;

public class inMemoryWeatherRepository implements  Repository<Weather , Integer> {

    private Set<Weather>inMemoryWeatherData;

    public inMemoryWeatherRepository(){
        //INI datos
        this.inMemoryWeatherData = new HashSet<>();
        this.inMemoryWeatherData.add(new Weather(1,"Limon","Costa Rica","0700",27.0f));
        this.inMemoryWeatherData.add(new Weather(2,"Alajuela","Costa Rica","0200",23.0f));
        this.inMemoryWeatherData.add(new Weather(3,"Heredia","Panamá","0400",25.0f));
        this.inMemoryWeatherData.add(new Weather(4,"Guanacaste","Colombia","0500",29.0f));

    }

    @Override
    public Optional <Weather> findById(Integer id) {
        return this.inMemoryWeatherData
                .stream()
                .filter(e -> Objects.equals(e.getId(),id))
                .findFirst();
    }

    @Override
    public List<Weather> findAll() {
        return new ArrayList<>(this.inMemoryWeatherData);
    }

    @Override
    public void save(Weather weather) {
            this.inMemoryWeatherData.add(weather);
    }

    @Override
    public void delete(Integer id) {
        var weatherToDelete = this.findById(id);
        this.inMemoryWeatherData.remove(weatherToDelete.get());
    }

    @Override
    public Weather update(Weather source) {
        var current = this.findById(source.getId()).get();
        current.setCityName(source.getCityName());
        current.setZipCode(source.getZipCode());
        current.setTemperature(source.getTemperature());

        this.delete(current.getId());
        this.save(current);

        return current;
    }
}
