package Metro;

import Graph.IEdge;
import Graph.IMultigraph;
import Graph.INode;
import View.ConsoleIO;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    private IMultigraph graph;

    private ConsoleIO consoleIO;

    public Controller(IMultigraph graph){
        this.graph = graph;
        consoleIO = new ConsoleIO();
    }

    public void run(){
        printUserCommands();
        INode startingStation;
        INode destinationStation;

        while(true){

            startingStation = validateInputStation("Please enter the starting Station name: ");

            if(startingStation == null){
                break;
            }

            destinationStation = validateInputStation("Please enter the destination name: ");

            if(destinationStation == null){
                break;
            }

            consoleIO.printRoute(graph.getRoute(startingStation, destinationStation), startingStation.getId(), destinationStation.getId());

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
    }

    private INode validateInputStation(String message){
        String input = "-1";
        while(true){

            input = consoleIO.prompt(message);
            input =  Character.toString(input.charAt(0)).toUpperCase()+input.substring(1);
            input = input.replaceAll("\\s","");


            List<INode> stations = graph.getNodesWithName(input);

            if(input.toLowerCase().equals("exit")){
                return null;
            }

            if(stations.isEmpty()){
                System.out.println("Sorry, station not recognised. Make sure you have spelt the name correclty and try again.");
            }else if(stations.size() > 1){

                System.out.println("We have found multiple stations with the name '" + input + "'");
                System.out.println("Please pick from the options below to specify which line your station is on: ");
                List<IEdge> stationOneLines =  graph.successors(stations.get(0));
                List<IEdge> stationTwoLines = graph.successors(stations.get(1));
                List<IEdge> tempDupe = new ArrayList<>();
                tempDupe.addAll(stationOneLines);
                stationOneLines.removeAll(stationTwoLines);
                stationTwoLines.removeAll(tempDupe);

                System.out.println("If your Node on line: '" + stationOneLines.get(0) + "'" );
                System.out.println();
            }else{

                return stations.get(0);
            }

        }
    }
}
