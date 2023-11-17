package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "city-delete", aliases = {"cid"}, description = "Delete a city")
public class DeleteCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city ID>", description = "The city ID")
    private int cityID;

    @Override
    public void run() {
        System.out.println("Delete City");

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        try {
            cityService.removeByCityID(cityID);
            System.out.println("CityID: " + cityID + "\tDeleted successfully" );

        }catch (Exception e){
            System.err.println("CityID: "+cityID +" \tnot deleted by reason" + e.getMessage());
        }

    }

}
