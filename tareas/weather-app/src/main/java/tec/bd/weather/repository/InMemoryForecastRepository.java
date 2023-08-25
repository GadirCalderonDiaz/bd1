package tec.bd.weather.repository;

import tec.bd.weather.entity.Forecast;

import java.util.*;

public class InMemoryForecastRepository implements  Repository<Forecast, Integer> {

    private Set<Forecast> inMemoryForecastData;

    public InMemoryForecastRepository(){
        //INI datos
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new Forecast(1, "Costa Rica", "Alajuela", "10101", new Date(), 23.0f));
        this.inMemoryForecastData.add(new Forecast(2, "Costa Rica", "Cartago", "20201", new Date(), 24.0f));
        this.inMemoryForecastData.add(new Forecast(3, "Costa Rica", "San Jose", "30301", new Date(), 25.0f));
        this.inMemoryForecastData.add(new Forecast(4, "Costa Rica", "Limon", "40401", new Date(), 25.0f));
       // this.inMemoryForecastData.add(new Forecast(5, "Limon", "Costa Rica", "33122", new Date(),  23.0f));
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
        System.out.println("1");
        var current = this.findById(source.getId()).get();
        current.setCityName(source.getCityName());
        current.setZipCode(source.getZipCode());
        current.setTemperature(source.getTemperature());

        this.delete(current.getId());
        this.save(current);

        return current;
    }
}