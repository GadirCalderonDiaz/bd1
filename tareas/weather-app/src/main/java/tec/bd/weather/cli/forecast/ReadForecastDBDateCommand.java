package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.ForecastDB;
import tec.bd.weather.entity.State;

@CommandLine.Command(name = "forecast-read-by-date", aliases = {"frd"}, description = "Read forecast by date")
public class ReadForecastDBDateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<forecast Date>", description = "The forecast Date")
    private String forecastDate;
    @Override
    public void run() {
        System.out.println("Read Forecast by Date: " + forecastDate);

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();

        var forecast = forecastService.getAllForecastDate(forecastDate);


        System.out.println("All Forecast with same date in dataBase");
        System.out.println("=========================");
        for (ForecastDB c : forecast) {
            System.out.println(c.getId() + "\t" + c.getZipcode() + "\t" + c.getCityName()+ "\t" + c.getStateName() + "\t" +c.getCountryName() + "\t" + c.getTemperature()+ "C/" + c.convertirFare(c.getTemperature())+"F"  + "\t" +c.getForecastDate());
        }
        System.out.println("=========================");

    }
}
