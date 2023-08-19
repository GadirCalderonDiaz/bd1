package tec.bd.weather;

import tec.bd.weather.entity.Weather;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.inMemoryWeatherRepository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

public class ApplicationContext {
        private Repository<Weather, Integer>weatherRepository;

        private WeatherService weatherService;

        public  ApplicationContext(){
            initWeatherRepository(this.weatherRepository);
            initWeatherRepository(this.weatherRepository);
        }

        private void  initWeatherRepository(Repository<Weather, Integer> weatherRepository){
            this.weatherRepository= new inMemoryWeatherRepository();
        }
        private void initWeatherService(){
            this.weatherService = new WeatherServiceImpl(this.weatherRepository);
        }

        public  Repository<Weather, Integer> getWeatherRepository(){
            return  this.weatherRepository;

        }
        public WeatherService getWeatherService(){
            return this.weatherService;
        }
}
