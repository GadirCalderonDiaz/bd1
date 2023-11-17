package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Country;

@CommandLine.Command(name = "country-update", aliases = {"cu"}, description = "Update country")
public class UpdateCountryCommand implements Runnable {


    @CommandLine.Parameters(paramLabel = "<Country ID>", description = "The Country ID")
    private int countryID;
    @CommandLine.Parameters(paramLabel = "<Country Name>", description = "The Country Name")
    private String countryName;

    @Override
    public void run() {

        System.out.println("Update Country");

        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        var newCountry = new Country(countryID, countryName);

        var countryToBeUpdated = countryService.updateCountry(newCountry);

        System.out.println("Country Updated");
        System.out.println("CountryID: " + countryToBeUpdated.getId() + " \nCountryName: "+ countryToBeUpdated.getCountryName() );


    }
}
