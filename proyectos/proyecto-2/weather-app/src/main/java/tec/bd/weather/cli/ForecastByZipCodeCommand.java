package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "by-zip", aliases = { "bz" }, description = "Get weather for a Zip code")
public class ForecastByZipCodeCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The Zip code")
    private String zipCode;

    @Override
    public void run() {

        System.out.println("By Zip Code: " + zipCode);

        try {
            var AppContext = new ApplicationContext();
            var weatherService = AppContext.getWeatherService();


            System.out.println(weatherService.getZipCodeTemperature(zipCode));
        } catch (Exception e) {
            System.err.println(zipCode + " is not supported");
        }
    }
}