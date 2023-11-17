package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.State;


@CommandLine.Command(name = "state-update", aliases = {"su"}, description = "Update state")
public class UpdateStateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<State ID>", description = "The State ID")
    private int stateID;
    @CommandLine.Parameters(paramLabel = "<State Name>", description = "The State Name")
    private String stateName;

    @CommandLine.Parameters(paramLabel = "<Country ID>", description = "The Country ID")
    private int countryID;


    @Override
    public void run() {
        System.out.println("Update State");

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();

        var newState = new State(stateID, stateName, countryID);

        var stateToBeUpdated = stateService.updateState(newState);

        System.out.println("State Updated");
        System.out.println("StateID: " + stateToBeUpdated.getId() + " \nStateName: "+ stateToBeUpdated.getStateName() + "\nCountry Associate " + stateToBeUpdated.getCountryID() );
    }
}