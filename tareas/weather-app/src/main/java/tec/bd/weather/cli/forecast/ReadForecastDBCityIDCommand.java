package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.ForecastDB;

@CommandLine.Command(name = "forecast-read-by-cityID", aliases = {"frc"}, description = "Read forecast by cityID")
public class ReadForecastDBCityIDCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<City ID>", description = "The city ID")
    private int cityID;
    @Override
    public void run() {
        System.out.println("Read Forecast by cityID: " + cityID);

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();

        var forecast = forecastService.getAllForecastCityID(cityID);


        System.out.println("All Forecast with same cityID in dataBase");
        System.out.println("=========================");
        for (ForecastDB c : forecast) {
            System.out.println(c.getId() + "\t" + c.getZipcode() + "\t" + c.getCityName()+ "\t" + c.getStateName() + "\t" +c.getCountryName() + "\t" + c.getTemperature()+ "C/" + c.convertirFare(c.getTemperature())+"F" + "\t" +c.getForecastDate());
        }
        System.out.println("=========================");

    }
}
