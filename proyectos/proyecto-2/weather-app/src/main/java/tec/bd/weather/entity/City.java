package tec.bd.weather.entity;

public class City {
    private Integer id;
    private String cityName;
    private Integer zipcode;
    private Integer stateID;

    public City(Integer id, String cityName, Integer zipcode, Integer stateID) {
        this.id = id;
        this.cityName = cityName;
        this.zipcode = zipcode;
        this.stateID = stateID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getStateID() {
        return stateID;
    }
    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
