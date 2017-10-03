package Control;

import Exceptions.BadFileException;
import Metro.Metro;
import Metro.Line;
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

        String startingStation;
        String destinationStation;

        while(true){

            startingStation = valididateInputStation("Please enter the starting Station name: ");

            if(startingStation.toLowerCase().equals("exit")){
                break;
            }

            destinationStation = valididateInputStation("Please enter the destination name: ");

            if(destinationStation.toLowerCase().equals("exit")){
                break;
            }

            consoleIO.printRoute(metro.route(startingStation, destinationStation), metro.getStation(startingStation).getId(), metro.getStation(destinationStation).getId());
            System.out.println();
            System.out.println("Type 'exit' at anytime to quit.");
            System.out.println("Next Route:");
            System.out.println();
        }

        System.out.println();
        System.out.println("Thank you for using Boston Metro!");
    }

    private String valididateInputStation(String message){
        String station = "-1";
        while(!metro.isStation(station)){

            station = consoleIO.prompt(message);

            if(station.toLowerCase().equals("exit")){
                break;
            }
        }

        return station;
    }
}
