package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.City;
import tec.bd.weather.entity.ForecastDB;

@CommandLine.Command(name = "forecast-update", aliases = {"fu"}, description = "update forecast")
public class UpdateForecastDBCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<forecastID>", description = "The forecast ID to update")
    private int forecastID;

    @CommandLine.Parameters(paramLabel = "<city ID>", description = "The city ID")
    private int cityID;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "The temperature ")
    private float temperature;
    @CommandLine.Parameters(paramLabel = "<forecast date>", description = "The forecast date ")
    private String date;


    @Override
    public void run() {
        System.out.println("Update Forecast");

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();

        var newForecast = new ForecastDB(forecastID,cityID,date,temperature);

        var forecastToBeUpdated = forecastService.updateForecast(newForecast);

        System.out.println("Forecast Updated");
        System.out.println("Forecast: " + forecastToBeUpdated.getId() + " \nForecast CityID: "+ forecastToBeUpdated.getCityID()+ "\nForecastZipCode " + forecastToBeUpdated.getZipcode()  + "\nForecast Temperature " + forecastToBeUpdated.getTemperature()+ "\nForecast Date " + forecastToBeUpdated.getForecastDate() );
    }
}