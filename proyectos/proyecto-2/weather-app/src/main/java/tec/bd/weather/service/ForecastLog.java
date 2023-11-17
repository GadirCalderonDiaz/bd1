package tec.bd.weather.service;



import tec.bd.weather.entity.Logs;

import java.util.List;

public interface ForecastLog {

    List<Logs> getLogs(Integer n);
}
