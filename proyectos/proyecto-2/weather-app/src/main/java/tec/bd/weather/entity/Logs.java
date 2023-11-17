package tec.bd.weather.entity;

public class Logs {

    private Integer id;
    private String last_modified_on;
    private Integer forecast_id;

    private String text;


    public Logs(Integer id, String last_modified_on, Integer forecast_id, String text) {
        this.id = id;
        this.last_modified_on = last_modified_on;
        this.forecast_id = forecast_id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_modified_on() {
        return last_modified_on;
    }

    public void setLast_modified_on(String last_modified_on) {
        this.last_modified_on = last_modified_on;
    }

    public Integer getForecast_id() {
        return forecast_id;
    }

    public void setForecast_id(Integer forecast_id) {
        this.forecast_id = forecast_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

