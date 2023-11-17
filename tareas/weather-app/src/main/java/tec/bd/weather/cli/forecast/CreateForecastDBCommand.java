package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

import java.util.Date;

@CommandLine.Command(name = "forecast-create", aliases = {"cf"}, description = "Create new forecast")
public class CreateForecastDBCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city ID>", description = "The city ID")
    private int cityID;

    @CommandLine.Parameters(paramLabel = "<Temperature>", description = "The Temperature")
    private float temperature;
    @CommandLine.Parameters(paramLabel = "<Date>", description = "The forecast Date")
    private String date;


    @Override
    public void run() {
        System.out.println("Create Forecast");

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();
        var newForecast = forecastService.newFore(cityID, temperature, date);

        System.out.println("New Forecast: " + newForecast.getId()+ ", " + newForecast.getCityID()+ ", " +newForecast.getZipcode() + ", " + newForecast.getTemperature()+ ", " + newForecast.getForecastDate());
    }
}