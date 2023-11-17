package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "country-delete", aliases = {"cd"}, description = "Delete a country")
public class DeleteCountryCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<Country ID>", description = "The Country ID")
    private int countryID;

    @Override
    public void run() {
        System.out.println("Delete Country");

        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        try {
            countryService.removeByCountryID(countryID);
            System.out.println("CountryID: " + countryID + "\tDeleted successfully" );
            
        }catch (Exception e){
            System.err.println("CountryID: "+countryID +" \tnot deleted by reason" + e.getMessage());
        }

    }

}
