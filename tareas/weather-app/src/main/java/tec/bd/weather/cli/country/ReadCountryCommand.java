package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Country;


@CommandLine.Command(name = "country-read", aliases = {"cr"}, description = "Read all countries or  by ID")
public class ReadCountryCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<Country ID>", description = "The Country ID", defaultValue = "0")
    private int countryID;
    @Override
    public void run() {
        System.out.println("Read Country. Country ID: " + countryID);

        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        if(countryID == 0){
            var countries = countryService.getAllCountries();


            System.out.println("All Countries in dataBase");
            System.out.println("=========================");
            for(Country c: countries){
                System.out.println(c.getId()  + "\t" + c.getCountryName());
            }
            System.out.println("=========================");
        }else{
            var country = countryService.getCountryByID(countryID);

            if(country.isPresent()){
                System.out.println("Country Selected in dataBase");
                System.out.println("=========================");
                System.out.println(country.get().getId()  + "\t" + country.get().getCountryName());
                System.out.println("=========================");
            }else{
                System.out.println("CountryID: "+ countryID + "Is not found!");
            }
        }
    }
}
