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
     * @requires message != null
     * @effects prompts the user with the given message and reads in and returns the reply
     */
    public String prompt(String message){

        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    /**
     * @requires route != null, startId must be a valid Id for a station on the starting line in route, same for endId but for the last line in route
     * @effects Prints the stages of route in a readable way if non-empty otherwise displays error message to user
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
     * @requires route != null && route isn't empty
     * @effects prints the list of Lines in a formatted, readable way
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
     * @requires previousLine to not be null, count to be positive
     * @effects prints out the line and number of stops
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
     * @requires line to be not null and stationId to be a valid id from a station on param line
     * @effects prints the details of the station with Id stationId
     */
    private void printStationDetails(Line line, int stationId){
        if(line.getNode1().getId() == stationId){
            System.out.println(line.getNode1().getName());
        }else{
            System.out.println(line.getNode2().getName());
        }
    }

    /**
     * @effects prints all elements of a collection
     */
    public void printCollection(Collection<?> collection){
        collection.stream().forEach(s -> System.out.println(" - '" + s + "'"));
    }
}
