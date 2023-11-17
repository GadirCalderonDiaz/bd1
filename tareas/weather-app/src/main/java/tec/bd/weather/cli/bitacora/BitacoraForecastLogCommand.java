package tec.bd.weather.cli.bitacora;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Logs;
import tec.bd.weather.entity.State;


@CommandLine.Command(name = "log", aliases = {"lg"}, description = "Forecast log")
public class BitacoraForecastLogCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<nLogs>", description = "The number logs")
    private int nlog;
    @Override
    public void run() {
        System.out.println("Read Forecast Logs. Number of logs: " + nlog);

        var appContext = new ApplicationContext();
        var forecastLogService = appContext.getForecastLogService();


        var logs = forecastLogService.getLogs(nlog);


        System.out.println("Number: "+ nlog+ "\tLogs in dataBase");
        System.out.println("=========================");
        for (Logs c : logs) {

            System.out.println(c.getId() + "\t" + c.getLast_modified_on() + "\t" + c.getForecast_id() +"\t"+c.getText());
        }
        System.out.println("=========================");

    }
}
