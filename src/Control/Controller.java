package Control;

import Exceptions.BadFileException;
import Metro.Metro;
import Metro.Line;
import Metro.Station;
import View.ConsoleIO;

import java.io.IOException;
import java.util.List;

public class Controller {

    private Metro metro;

    private ConsoleIO consoleIO;

    private MetroMapParser metroMapParser;

    public Controller(){
        consoleIO = new ConsoleIO();

        try {

            metroMapParser = new MetroMapParser("resources/bostonmetro.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        initialiseMetro();
    }

    public void initialiseMetro(){
        try {
            metro = new Metro(metroMapParser.generateGraphFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadFileException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        printUserCommands();
        Station startingStation;
        Station destinationStation;

        while(true){

            startingStation = validateInputStation("Please enter the starting Station name: ");

            if(startingStation == null){
                break;
            }

            destinationStation = validateInputStation("Please enter the destination name: ");

            if(destinationStation == null){
                break;
            }

            consoleIO.printRoute(metro.route(startingStation, destinationStation), startingStation.getId(), destinationStation.getId());
            printUserCommands();
            System.out.println(" --- Next Route ---");
            System.out.println();
        }

        System.out.println();
        System.out.println("Thank you for using Boston Metro!");
    }

    private void printUserCommands(){
        System.out.println();
        System.out.println("Type 'exit' at anytime to quit.");
        System.out.println("Type 'stations' at anytime to get a list of available stations.");
        System.out.println();
    }

    private Station validateInputStation(String message){
        String input = "-1";
        while(true){

            input = consoleIO.prompt(message);

            List<Station> stations = metro.getStationsWithName(input);

            if(input.toLowerCase().equals("exit")){
                return null;
            }

            if(input.toLowerCase().equals("stations")){

//                consoleIO.printList(metro.);
            }else if(stations.isEmpty()){

                System.out.println("Sorry, station not recognised. Make sure you have spelt the name correclty and try again.");
            }else if(stations.size() > 1){

                System.out.println("We have found multiple stations with the name '" + input + "'");
                System.out.println("Please pick from the options below to specify which line your station is on: ");
                //print out choice of lines
                System.out.println();
            }else{

                return stations.get(0);
            }

        }
    }
}
