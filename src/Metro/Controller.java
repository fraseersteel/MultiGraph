package Metro;

import Graph.IMultigraph;
import View.ConsoleIO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Controller {

    private IMultigraph graph;

    private ConsoleIO consoleIO;

    public Controller(IMultigraph graph){
        this.graph = graph;
        consoleIO = new ConsoleIO();
    }

    /**
     * Effects: prompts the user for station names and validates them, prints out route from one to the other if
     */
    public void run(){
        System.out.println("Type 'exit' at anytime to quit.");
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

            if(startingStation.getId() == destinationStation.getId()){
                System.out.println("Hurray! You've made it to your destination. (Next time try picking two different stations)");
            }else {
                consoleIO.printRoute((List<Line>) graph.getRoute(startingStation, destinationStation), startingStation.getId(), destinationStation.getId());

                System.out.println("Type 'exit' at anytime to quit.");
                System.out.println(" --- Next Route ---");
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("Thank you for using Boston Metro!");
    }

    /**
     * Requires: message != null
     * Effects: keeps prompting user with message until either input is a valid station name (then the station with that name is returned) or 'exit' is entered (and null is returned)
     *
     * @param message the message prompt that the user will respond to
     * @return a Station object that is in the graph or null if user enters exit
     */
    private Station validateInputStation(String message){
        String input = "-1";
        while(true){

            input = consoleIO.prompt(message);
            if(input.trim().equals("")){
                System.out.println("Empty String not allowed, enter a letter and press enter to see suggestions.");
                continue;
            }
            input =  Character.toString(input.charAt(0)).toUpperCase()+input.substring(1);
            input = input.replaceAll("\\s","");

            List<Station> stations = getStationsWithName(input);

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

    /**
     * Requires: input != null
     * Effects: notifies user input is not valid
     *
     * @param input String which has been shown to be invalid Station name
     */
    private void manageStationNotValid(String input){

        System.out.println("That doesn't seem to be a station name we recognise.");
        Set<String> nodes = getStationNamesWithPrefix(input);

        if(nodes.size() == 1){
            System.out.println("You might have meant the following: ");
        }else if(nodes.size() > 0){
            System.out.println("You might have meant one of the following: ");
        }else{
            System.out.println("No Suggestions available.");
        }
        consoleIO.printCollection(nodes);
        System.out.println("Make sure you have spelt the name correclty and try again.");
        if(nodes.size() == 0){
            System.out.println("If in doubt, type the first letter and press enter to see possible suggestions.");
        }
        System.out.println();
    }

    /**
     * Requires: stations.size() > 1
     * Effects: asks user to specify the station they meant, returns int of location in stations or -2 if the user meant none of the options
     *
     * @param stations list of stations
     * @return -1 if exit was entered, -2 if none was entered, an index in the param list
     */
    private int manageDuplicateStations(List<Station> stations){

        List<Line> stationLines;
        System.out.println();
        System.out.println("If the Station you meant is neighbours with:");
        for(int i = 0; i < stations.size(); i++){
            Station currentStation = stations.get(i);
            stationLines = (List<Line>) graph.successors(currentStation);
            System.out.print(" " + (i+1) + " : '" + stationLines.get(0).getOtherNode(currentStation.getId()).getName() + "'");
            for(int j = 1; j < stationLines.size(); j++){
                System.out.print(" and '" + stationLines.get(j).getOtherNode(currentStation.getId()).getName() + "' ");
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

    /**
     * Effects: Returns a java.util.List containing references to the Station(s) which have the same name as specified by the name
     * parameter. Will return an empty list if no matching nodes are found.
     *
     * @param name (String) The name for which will return all Stations with matching names for.
     * @return A List containing references to any Station with names matching the name parameter.
     */
    public List<Station> getStationsWithName(String name) {
        if (name == null) {
            return (new ArrayList<>());
        }
        ArrayList<Station> matchingStations = new ArrayList<>();
        List<Station> list = (List<Station>) graph.getNodes();
        for (Station station : list) {
            if (station.getName().equals(name)) {
                matchingStations.add(station);
            }
        }

        return matchingStations;
    }

    /**
     * Effects: Returns a java.util.Set containing Strings of names prefix matching the prefix parameter
     * parameter. Will return an empty set if no matching nodes are found.
     *
     * @param prefix (String) The name of a Station
     * @return A Set containing the names of any Stations with the name prefix matching the prefix parameter.
     */
    public Set<String> getStationNamesWithPrefix(String prefix) {
        if (prefix == null) {
            return (new HashSet<>());
        }
        Set<String> matchingStations = new HashSet<>();
        List<Station> list = (List<Station>) graph.getNodes();
        for (Station station : list) {
            if (station.getName().startsWith(prefix)) {
                matchingStations.add(station.getName());
            }
        }

        return matchingStations;
    }
}
