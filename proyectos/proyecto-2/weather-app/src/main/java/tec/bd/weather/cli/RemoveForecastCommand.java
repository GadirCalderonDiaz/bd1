package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "remove-forecast", aliases = { "rf" }, description = "Delete existing forecast data")
public class RemoveForecastCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int forecastIdToDelete;


    @Override
    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            weatherService.removeForecast(forecastIdToDelete);
            System.out.println("forecast removed");
        } catch (Exception e) {
            System.err.println("Can't removed forecast. " +  e.getMessage());
        }
    }

}