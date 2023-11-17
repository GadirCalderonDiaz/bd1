package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.State;


@CommandLine.Command(name = "state-read", aliases = {"sr"}, description = "Read state")
public class ReadStateCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<State ID>", description = "The State ID", defaultValue = "0")
    private int stateID;
    @Override
    public void run() {
        System.out.println("Read State. State ID: " + stateID);

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();

        if (stateID == 0) {
            var states = stateService.getAllStates();


            System.out.println("All States in dataBase");
            System.out.println("=========================");
            for (State c : states) {
                System.out.println(c.getId() + "\t" + c.getStateName() + "\t" + c.getCountryID());
            }
            System.out.println("=========================");
        } else {
            var state = stateService.getStateByID(stateID);

            if (state.isPresent()) {
                System.out.println("State Selected in dataBase");
                System.out.println("=========================");
                System.out.println(state.get().getId() + "\t" + state.get().getStateName() + "\t" + state.get().getCountryID());
                System.out.println("=========================");
            } else {
                System.out.println("StateID: " + stateID + "Is not found!");
            }

        }
    }
}
