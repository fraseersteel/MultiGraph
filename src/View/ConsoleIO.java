package View;

import Control.Controller;
import Graph.IEdge;
import Graph.INode;
import Metro.Line;
import Metro.Station;

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

        int stationCount = 1;
        for(int i = 1; i < edges.size(); i++){
            currentEdge = edges.get(i);

            if(!previousLine.equals(currentEdge.getLabel())){

                printNumberOfStops(previousLine, stationCount);

                IEdge previousEdge = edges.get(i-1);
                INode connectingNode = previousEdge.getNode1();

                if(!connectingNode.equals(currentEdge.getNode1()) && !connectingNode.equals(currentEdge.getNode2())){
                    connectingNode = previousEdge.getNode2();
                }

                System.out.println("When you reach Station '" + connectingNode + "' change line to '" + currentEdge.getLabel() + "' from '" + previousLine + "'");
                previousLine = currentEdge.getLabel();
                stationCount = 0;
            }
            stationCount++;
        }

        printNumberOfStops(previousLine, stationCount);
    }

    private void printNumberOfStops(String previousLine, int count){
        System.out.print("Travel on the '" + previousLine + "' for " + count);
        if(count<2){
            System.out.println(" stop");
        }else {
            System.out.println(" stops");
        }
    }

    private void printStationDetails(IEdge edge, int nodeId){
        INode otherNode = edge.getOtherNode(nodeId);
        System.out.println(otherNode);

    }



    public void printList(List<?> list){
        System.out.println();
        list.stream().forEach(System.out::println);
        System.out.println();
    }
}
