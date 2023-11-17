package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "forecast-delete", aliases = {"fd"}, description = "Delete Forecast")
public class DeleteForecastDBCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<Forecast ID>", description = "The Forecast ID")
    private int forecastID;


    @Override
    public void run() {
        System.out.println("Delete Forecast");

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();

        try {
            forecastService.removeByForecastID(forecastID);
            System.out.println("ForecastID: " + forecastID + "\tDeleted successfully" );

        }catch (Exception e){
            System.err.println("Forecast: "+forecastID +" not deleted by reason\t" + e.getMessage());
        }
    }
}
