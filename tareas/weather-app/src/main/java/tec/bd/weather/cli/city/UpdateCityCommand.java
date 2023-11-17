package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.City;
import tec.bd.weather.entity.State;

@CommandLine.Command(name = "city-update", aliases = {"ciu"}, description = "update city")
public class UpdateCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city ID>", description = "The city ID")
    private int cityID;
    @CommandLine.Parameters(paramLabel = "<city Name>", description = "The city Name")
    private String cityName;

    @CommandLine.Parameters(paramLabel = "<zipcode>", description = "The zipcode ")
    private int zipcode;
    @CommandLine.Parameters(paramLabel = "<State ID>", description = "The StateID associate ")
    private int stateID;


    @Override
    public void run() {
        System.out.println("Update City");

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        var newCity = new City(cityID, cityName,zipcode, stateID);

        var cityToBeUpdated = cityService.updateCity(newCity);

        System.out.println("City Updated");
        System.out.println("CityID: " + cityToBeUpdated.getId() + " \nCityName: "+ cityToBeUpdated.getCityName()+ "\nZipCode " + cityToBeUpdated.getZipcode()  + "\nState Associate " + cityToBeUpdated.getStateID() );
    }
}