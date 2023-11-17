package tec.bd.weather.entity;



import java.util.Date;

public class State {
    private Integer id;
    private String stateName;
    private Integer countryID;


    public State(Integer id, String stateName, Integer countryID) {
        this.id = id;
        this.stateName = stateName;
        this.countryID = countryID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}