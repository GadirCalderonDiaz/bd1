package tec.bd.weather;

import java.util.HashMap;
import java.util.Map;
import static java.util.Optional.*;

public class WeatherServiceImpl implements WeatherService {

    private Map<String, Float> cityTemperatureData;
    private Map<String, Float> zipCodeTemperatureData;

    public WeatherServiceImpl() {
        this.cityTemperatureData = new HashMap<>(){
            { put("Alajuela", 23.0f ); }
            { put("San Jose", 24.0f ); }
            { put("Heredia", 25.0f ); }
            { put("Cartago", 26.0f ); }
            { put("Limon", 27.0f ); }
            { put("Puntarenas", 28.0f ); }
            { put("Guanacaste", 29.0f ); }
        };

        this.zipCodeTemperatureData = new HashMap<>(){
            { put("0200", 23.0f ); }
            { put("0100", 24.0f ); }
            { put("0400", 25.0f ); }
             { put("0300", 26.0f ); }
            { put("0700", 27.0f ); }
            { put("0600", 28.0f ); }
            { put("0500", 29.0f ); }
        };
    }

    @Override
    public float getCityTemperature(String city) {
        var temperature = ofNullable(this.cityTemperatureData.get(city));
        return temperature.orElseThrow();
    }

    @Override
    public float getZipCodeTemperature(String zipCode) {
         var temperature = ofNullable(this.zipCodeTemperatureData.get(zipCode));
        return temperature.orElseThrow();
    }
}
