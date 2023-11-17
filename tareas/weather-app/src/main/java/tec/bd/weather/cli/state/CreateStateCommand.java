package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;


@CommandLine.Command(name = "state-create", aliases = {"sc"}, description = "Create new state")
public class CreateStateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<state name>", description = "The state name")
    private String stateName;

    @CommandLine.Parameters(paramLabel = "<country id>", description = "The country id")
    private int countryID;


    @Override
    public void run() {
        System.out.println("Create State");

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();
        var newState = stateService.newState(stateName, countryID);

        System.out.println("New State:\t" + newState.getId()+ ",\t" + newState.getStateName()  + "\nCountry Associated\t"+ newState.getCountryID());
    }
}
