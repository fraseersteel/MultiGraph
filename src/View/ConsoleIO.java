package View;

import Graph.IEdge;
import Graph.INode;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    public ConsoleIO(){
        System.out.println("Welcome to the Boston Metro!");
    }

    public String prompt(String message){

        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public void printRoute(List<? extends IEdge> edges, int startId, int endId){
        if(!edges.isEmpty()){

            System.out.println("--- Your Journey plan ---");
            System.out.println();
            System.out.print("Starting from ");
            printStationDetails(edges.get(0), startId);
            printDirectionOfTravel(startId, edges.get(0));

            formatRouteList(edges);

            System.out.print("Until you reach your destination ");
            printStationDetails(edges.get(edges.size() - 1), endId);
        }else{
            System.out.println("No Route Found.");
        }
        System.out.println();
    }

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
                printLineChange(connectingNode, currentEdge);
                printDirectionOfTravel(connectingNode.getId(), currentEdge);

                previousLine = currentEdge.getLabel();
                stationCount = 0;
            }
            stationCount++;
        }

        printNumberOfStops(previousLine, stationCount);
    }

    private void printNumberOfStops(String previousLine, int count){
        System.out.print(", Travel on the '" + previousLine + "' line for " + count);
        if(count<2){
            System.out.println(" stop ");
        }else {
            System.out.println(" stops ");
        }
    }

    private void printDirectionOfTravel(int startId, IEdge edge){
        System.out.print("In the direction of '" + edge.getOtherNode(startId) + "' ");
    }

    private void printLineChange(INode node, IEdge edge){
        System.out.println("When you reach Station '" + node + "' change to the '" + edge.getLabel() + "' line.");
    }

    private void printStationDetails(IEdge edge, int nodeId){
        if(edge.getNode1().getId() == nodeId){
            System.out.println(edge.getNode1());
        }else{
            System.out.println(edge.getNode2());
        }
    }

    public void printList(List<?> list){
        list.stream().forEach(s -> System.out.println(" - '" + s + "'"));
    }
}
