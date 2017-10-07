package View;

import Graph.IEdge;
import Graph.INode;

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
     * @requires edges != null, startId must be a valid Id for a node on the starting edge in edges, same for endId but for the last edge in edges
     * @effects Prints the route of edges in a readable way if non-empty otherwise displays error message to user
     */
    public void printRoute(List<? extends IEdge> edges, int startId, int endId){
        if(!edges.isEmpty()){

            System.out.println("--- Your Journey plan ---");
            System.out.println();
            System.out.print(" - Starting from ");
            printNodeDetails(edges.get(0), startId);
            System.out.print(" - In the direction of '" + edges.get(0).getOtherNode(startId).getName() + "' ");

            formatRouteList(edges);

            System.out.print(" - Until you reach your destination ");
            printNodeDetails(edges.get(edges.size() - 1), endId);
        }else{
            System.out.println("No Route Found.");
        }
        System.out.println();
    }

    /**
     * @requires edges != null && edges isn't empty
     * @effects prints the list of Iedges in a formatted, readable way
     */
    private void formatRouteList(List<? extends IEdge> edges){
        IEdge currentEdge = edges.get(0);
        String previousLine = currentEdge.getLabel();

        IEdge previousEdge;
        INode connectingNode;

        int stationCount = 1;
        for(int i = 1; i < edges.size(); i++){

            currentEdge = edges.get(i);

            if(!previousLine.equals(currentEdge.getLabel())){

                previousEdge = edges.get(i-1);
                connectingNode = previousEdge.getNode1();

                if(connectingNode.getId() != currentEdge.getNode1().getId() && connectingNode.getId() != currentEdge.getNode2().getId()){
                    connectingNode = previousEdge.getNode2();
                }

                printNumberOfStops(previousLine, stationCount);
                System.out.println(" - When you reach Station '" + connectingNode.getName() + "' change to the '" + currentEdge.getLabel() + "' line.");
                System.out.print(" - In the direction of '" + currentEdge.getOtherNode(connectingNode.getId()).getName() + "' ");

                previousLine = currentEdge.getLabel();
                stationCount = 0;
            }
            stationCount++;
        }

        printNumberOfStops(previousLine, stationCount);
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
     * @requires edge to be not null and nodeId to be a valid id from a node on param edge
     * @effects prints the details of the node with Id nodeId
     */
    private void printNodeDetails(IEdge edge, int nodeId){
        if(edge.getNode1().getId() == nodeId){
            System.out.println(edge.getNode1().getName());
        }else{
            System.out.println(edge.getNode2().getName());
        }
    }

    /**
     * @effects prints all elements of a collection
     */
    public void printCollection(Collection<?> collection){
        collection.stream().forEach(s -> System.out.println(" - '" + s + "'"));
    }
}
