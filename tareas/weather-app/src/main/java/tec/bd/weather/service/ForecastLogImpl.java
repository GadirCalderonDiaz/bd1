package tec.bd.weather.service;

import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.Logs;
import tec.bd.weather.repository.Repository;

import java.util.List;

public class ForecastLogImpl implements  ForecastLog {


    private Repository<Logs, Integer> forecastLogRepository;


    public ForecastLogImpl(Repository<Logs, Integer> forecastLogRepository){
        this.forecastLogRepository = forecastLogRepository;
    }

    @Override
    public List<Logs> getLogs(Integer n) {
        return this.forecastLogRepository.findLogs(n);
    }
}
