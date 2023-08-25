package tec.bd.weather.entity;

import java.util.Date;

public class Forecast {

    private Integer id;

    private Date forecastDate;

    private float temperature;

    private String cityName;

    private  String zipCode;

    private String countryName;


    public Forecast(){


    }

    public Forecast(Integer id, String cityName, String countryName , String zipCode, Date date,float temperature){
        this.forecastDate = date;
        this.countryName = countryName;
        this.id = id;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.temperature=  temperature;

    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTemperature(){
        return temperature;
    }
    public void setTemperature(float temperature){

        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", forecastDate=" + forecastDate +
                ", temperature=" + temperature +
                ", cityName='" + cityName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public static void validate(Forecast forecast){
        if(forecast == null){
            throw new RuntimeException("No weather forecast was provided");
        }
        if(forecast.getId() == null){
            throw new RuntimeException("No weather forecast ID was provided");
        }
        //System.out.println(forecast.getId());
        if(forecast.getId() < 1){
            throw new RuntimeException("Weather forecast ID invalid");
        }
        if(forecast.getZipCode() == null){
            throw new RuntimeException("No weather forecast Zip Code was provided ");
        }
        if(forecast.getCityName() == null){
            throw new RuntimeException("No weather forecast city name was provided ");
        }
        if(forecast.getCountryName() == null){
            throw new RuntimeException("No weather forecast country name was provided ");
        }

    }
}

