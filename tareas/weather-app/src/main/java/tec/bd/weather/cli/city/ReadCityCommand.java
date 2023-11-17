package tec.bd.weather.cli.city;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.City;
import tec.bd.weather.entity.Country;


@CommandLine.Command(name = "city-read", aliases = {"cir"}, description = "Read all cities or  by ID")
public class ReadCityCommand  implements Runnable {

    @CommandLine.Parameters(paramLabel = "<City ID>", description = "The City ID", defaultValue = "0")
    private int cityID;
    @Override
    public void run() {
        System.out.println("Read City. City ID: " + cityID);

        var appContext = new ApplicationContext();
        var cityService = appContext.getCityService();

        if(cityID == 0){
            var cities = cityService.getAllCities();


            System.out.println("All Cities in dataBase");
            System.out.println("=========================");
            for(City c: cities){
                System.out.println(c.getId()  + "\t" + c.getCityName()+ "\t" + c.getZipcode() + "\t" + c.getStateID());
            }
            System.out.println("=========================");
        }else{
            var city = cityService.getCityByID(cityID);

            if(city.isPresent()){
                System.out.println("City Selected in dataBase");
                System.out.println("=========================");
                System.out.println(city.get().getId()  + "\t" + city.get().getCityName()+ "\t" + city.get().getZipcode() + "\t" + city.get().getStateID());
                System.out.println("=========================");
            }else{
                System.out.println("CityID: "+ cityID + "Is not found!");
            }
        }
    }
}