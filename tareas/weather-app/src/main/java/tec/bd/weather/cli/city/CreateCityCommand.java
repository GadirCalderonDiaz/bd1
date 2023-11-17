package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;



@CommandLine.Command(name = "city-create", aliases = {"cic"}, description = "Create new city")
public class CreateCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @CommandLine.Parameters(paramLabel = "<zipcode>", description = "The zipcode")
    private int zipcode;
    @CommandLine.Parameters(paramLabel = "<state ID>", description = "The state associate")
    private int stateID;


    @Override
    public void run() {
        System.out.println("Create City");

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();
        var newCity = cityService.newCity(cityName, zipcode, stateID);

        System.out.println("New City: " + newCity.getId()+ ", " + newCity.getCityName()+ ", " +newCity.getZipcode() + ", " + newCity.getStateID());
    }
}