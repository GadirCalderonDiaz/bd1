package tec.bd.weather.cli.forecast;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.ForecastDB;


@CommandLine.Command(name = "forecast-read-by-zipcode", aliases = {"frz"}, description = "Read forecast by zipCode")
public class ReadForecastDBZipcodeCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<City ZipCode>", description = "The city ZipCode")
    private int zipCode;
    @Override
    public void run() {
        System.out.println("Read Forecast by zipCode: " + zipCode);

        var appContext = new ApplicationContext();
        var forecastService = appContext.getForecastService();

        var forecast = forecastService.getAllForecastZipCode(zipCode);


        System.out.println("All Forecast with same zipCode in dataBase");
        System.out.println("=========================");
        for (ForecastDB c : forecast) {
            System.out.println(c.getId() + "\t" + c.getZipcode() + "\t" + c.getCityName()+ "\t" + c.getStateName() + "\t" +c.getCountryName() + "\t" + c.getTemperature()+ "C/" + c.convertirFare(c.getTemperature())+"F" + "\t" +c.getForecastDate());
        }
        System.out.println("=========================");

    }
}
