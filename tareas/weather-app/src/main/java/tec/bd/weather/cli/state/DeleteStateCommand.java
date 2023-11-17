package tec.bd.weather.cli.state;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;


@CommandLine.Command(name = "state-delete", aliases = {"sd"}, description = "Delete state")
public class DeleteStateCommand  implements Runnable {

    @CommandLine.Parameters(paramLabel = "<State ID>", description = "The State ID")
    private int stateID;


    @Override
    public void run() {
        System.out.println("Delete State");

        var appContext = new ApplicationContext();
        var stateService = appContext.getStateService();

        try {
            stateService.removeByStateID(stateID);
            System.out.println("StateID: " + stateID + "\tDeleted successfully" );

        }catch (Exception e){
            System.err.println("StateID: "+stateID +" \tnot deleted by reason" + e.getMessage());
        }
    }
}
