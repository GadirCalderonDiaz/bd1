package tec.bd.weather.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Weather App",
        subcommands = {
                ForecastByCityCommand.class,
                ForecastByZipCodeCommand.class,
                CommandLine.HelpCommand.class
        },
        description = "Weather App Service by City and Zip Code")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}