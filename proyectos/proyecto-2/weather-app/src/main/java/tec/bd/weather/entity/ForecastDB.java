package tec.bd.weather.entity;

import java.util.Date;

public class ForecastDB {

    private Integer id;

    private Integer cityID;

    private Integer zipcode;

    private String cityName;

    private String stateName;

    private String countryName;

    private float temperature;

    private String forecastDate;


    public ForecastDB(Integer id,Integer cityID, Integer zipCode, String forecastDate, float temperature) {
        this.id = id;
        this.cityID = cityID;
        this.zipcode = zipCode;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
    }

    public ForecastDB(Integer id, Integer zipCode, String cityName, String stateName, String countryName, String forecastDate, float temperature) {
        this.id = id;
        this.zipcode = zipCode;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
    }

    public ForecastDB(Integer id,Integer cityID, String forecastDate, float temperature) {
        this.id = id;
        this.cityID = cityID;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Double convertirFare(Float n){

        Double faren = (n*1.8) +32;

        return faren;
    }
}
