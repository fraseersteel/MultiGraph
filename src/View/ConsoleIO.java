package View;

import Metro.Line;
import Metro.Station;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    public ConsoleIO(){
        System.out.println("Welcome to the Boston Metro!");
    }

    /**
     * Requires: message != null
     * Effects: prompts the user with the given message and reads in and returns the reply
     *
     * @param message a string displayed to the user
     * @return the response from the user
     */
    public String prompt(String message){

        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    /**
     * Requires: route != null, startId must be a valid Id for a station on the starting line in route, same for endId but for the last line in route
     * Effects: Prints the stages of route in a readable way if non-empty otherwise displays error message to user
     *
     * @param route list of lines to be printed
     * @param startId id of the start station
     * @param endId id of the last station
     */
    public void printRoute(List<Line> route, int startId, int endId){
        if(!route.isEmpty()){

            System.out.println("--- Your Journey plan ---");
            System.out.println();
            System.out.print(" - Starting from ");
            printStationDetails(route.get(0), startId);
            System.out.print(" - In the direction of '" + route.get(0).getOtherNode(startId).getName() + "' ");

            formatRouteList(route);

            System.out.print(" - Until you reach your destination ");
            printStationDetails(route.get(route.size() - 1), endId);
        }else{
            System.out.println("No Route Found.");
        }
        System.out.println();
    }

    /**
     * Requires: route != null && route isn't empty
     * Effects: prints the list of Lines in a formatted, readable way
     *
     *@param route list of lines to be formatted
     */
    private void formatRouteList(List<Line> route){
        Line currentLine = route.get(0);
        String previousLineName = currentLine.getLabel();

        Line previousLine;
        Station connectingStation;

        int stationCount = 1;
        for(int i = 1; i < route.size(); i++){

            currentLine = route.get(i);

            if(!previousLineName.equals(currentLine.getLabel())){

                previousLine = route.get(i-1);
                connectingStation = previousLine.getNode1();

                if(connectingStation.getId() != currentLine.getNode1().getId() && connectingStation.getId() != currentLine.getNode2().getId()){
                    connectingStation = previousLine.getNode2();
                }

                printNumberOfStops(previousLineName, stationCount);
                System.out.println(" - When you reach Station '" + connectingStation.getName() + "' change to the '" + currentLine.getLabel() + "' line.");
                System.out.print(" - In the direction of '" + currentLine.getOtherNode(connectingStation.getId()).getName() + "' ");

                previousLineName = currentLine.getLabel();
                stationCount = 0;
            }
            stationCount++;
        }

        printNumberOfStops(previousLineName, stationCount);
    }

    /**
     * Requires: previousLine to not be null, count to be positive
     * Effects: prints out the line and number of stops
     *
     * @param previousLine the Line name
     * @param count the number of stops
     */
    private void printNumberOfStops(String previousLine, int count){
        System.out.print(", Travel on the '" + previousLine + "' line for " + count);
        if(count<2){
            System.out.println(" stop ");
        }else {
            System.out.println(" stops ");
        }
    }

    /**
     * Requires: line to be not null and stationId to be a valid id from a station on param line
     * Effects: prints the details of the station with Id stationId
     *
     * @param line the line on which the station is on
     * @param stationId the id of the station
     */
    private void printStationDetails(Line line, int stationId){
        if(line.getNode1().getId() == stationId){
            System.out.println(line.getNode1().getName());
        }else{
            System.out.println(line.getNode2().getName());
        }
    }

    /**
     * Effects: prints all elements of a collection
     *
     * @param collection a collection of elements
     */
    public void printCollection(Collection<?> collection){
        collection.stream().forEach(s -> System.out.println(" - '" + s + "'"));
    }
}
