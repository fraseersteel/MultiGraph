package View;

import Control.Controller;
import Graph.IEdge;
import Graph.INode;
import Metro.Line;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    public ConsoleIO(){
        System.out.println("Hello, Welcome to the Boston Metro!");
        System.out.println("Type 'exit' at anytime to quit.");
        System.out.println();
    }

    public String prompt(String message){

        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public void printRoute(List<IEdge> edges, int startId, int endId){

        if(!edges.isEmpty()){

            String startStation = "";
            IEdge edge = edges.get(0);

            //to find start node
            if(edge.getNode1().getId() == startId){
                System.out.println(edge.getNode1());
            }else{
                System.out.println(edge.getNode2());
            }

            //loop though edges
            String previousLine = edge.getLabel();
//            System.out.print("Travel on the " + previousLine + " for ");
//            int stationCount = 0;

            for(int i = 0; i < edges.size(); i++){

//                stationCount++;
                edge = edges.get(i);

                //If line has changed
                if(!previousLine.equals(edge.getLabel())){

//                    System.out.println(stationCount + " stops");
                    IEdge tempEdge = edges.get(i-1);
                    INode tempNode = tempEdge.getNode1();

                    if(!tempNode.equals(edge.getNode1()) && !tempNode.equals(edge.getNode2())){
                         tempNode = tempEdge.getNode2();
                    }

                    System.out.println("Change Line from " + previousLine + " To " + edge.getLabel() + " at Station: " + tempNode);
                    previousLine = edge.getLabel();
//                    System.out.print("Travel on the " + previousLine + " for ");
//                    stationCount = 1;
                }
            }
//            System.out.println(stationCount + " stops");

            //to find end node
            edge = edges.get(edges.size() - 1);

            if(edge.getNode1().getId() == endId){
                System.out.println(edge.getNode1());
            }else{
                System.out.println(edge.getNode2());
            }
            System.out.println();

        }else{

            System.out.println("No Route Found.");
            System.out.println();
        }



    }
}
