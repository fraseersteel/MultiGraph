package Metro;

import Graph.IEdge;
import Graph.IMultigraph;
import Graph.INode;
import View.ConsoleIO;

import javax.sound.midi.SysexMessage;
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

            if(startingStation.getId() == destinationStation.getId()){
                System.out.println("You are already at this location");
            }else {
                consoleIO.printRoute(graph.getRoute(startingStation, destinationStation), startingStation.getId(), destinationStation.getId());
                printUserCommands();
                System.out.println(" --- Next Route ---");
                System.out.println();
            }
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
                manageStationNotValid(input);
            }else if(stations.size() > 1){
                System.out.println("We have found multiple stations with the name '" + input + "'.");
                int i = manageDuplicateStations(stations);
                if(i == -1){
                    return null;
                }else if(i >= 0){
                    return stations.get(i);
                }
            }else{

                return stations.get(0);
            }

        }
    }

    private void manageStationNotValid(String input){

        System.out.println("That doesn't seem to be a station name we recognise.");
        List<INode> nodes = new ArrayList<>();
        graph.getNodes().stream().filter(n -> n.getName().startsWith(input)).forEach(n -> nodes.add(n));

        if(nodes.size() == 1){
            System.out.println("You might have meant the following: ");
        }else if(nodes.size() > 0){
            System.out.println("You might have meant one of the following: ");
        }
        consoleIO.printList(nodes);
        System.out.println("Make sure you have spelt the name correclty and try again.");
        System.out.println();
    }

    private int manageDuplicateStations(List<INode> stations){

        List<IEdge> stationLines;
        System.out.println();
        System.out.println("If the Station you meant is neighbours with:");
        for(int i = 0; i < stations.size(); i++){
            INode currentStation = stations.get(i);
            stationLines =  graph.successors(currentStation);
            System.out.print("OPTION " + (i+1) + ": '" + stationLines.get(0).getOtherNode(currentStation.getId()) + "'");
            for(int j = 1; j < stationLines.size(); j++){
                System.out.print(" and '" + stationLines.get(j).getOtherNode(currentStation.getId()) + "' ");
            }
            System.out.println();
        }
        String input = consoleIO.prompt("Else, if you didn't mean any of the above options, enter 'none'.");
        while (true) {
            if (input.toLowerCase().equals("exit")) {
                return -1;
            }
            if (input.toLowerCase().equals("none")) {
                return -2;
            } else {
                try {
                    int i = Integer.parseInt(input) - 1;
                    if(i > stations.size()-1 || i < 0){
                        input = consoleIO.prompt("Sorry, That number was not an option, choose one of the options above.");
                    }else{
                        stations.get(i);
                        return i;
                    }
                } catch (Exception e) {
                    input = consoleIO.prompt("Sorry, That is not a number, please either type 'none' or one of the options above.");
                }
            }
        }
    }
}
