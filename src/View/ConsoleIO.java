package View;

import Control.Controller;
import Graph.IEdge;
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

    public void printMessage(String message){

        System.out.println(message);
    }

    public void printRoute(List<IEdge> edges){

        if(!edges.isEmpty()){

            String startStation = "";
            IEdge edge = edges.get(0);
            IEdge edge2 = edges.get(1);

            //assumes that stations aren't next to each other
            if(edge2.getNode1().getId() == edge.getNode1().getId() || edge2.getNode2().getId() == edge.getNode1().getId()){
                startStation = edge.getNode2().toString();
            }else{
                startStation = edge.getNode1().toString();
            }

            System.out.print(edge.getNode1() + " - ");

            for(int i = 0; i < edges.size() - 1; i++){
                edge = edges.get(i);
                System.out.print(edge.getLabel() + " - " + edge.getNode2() + " - ");
            }

            edge = edges.get(edges.size() - 1);
            System.out.print(edge.getLabel() + " - " + edge.getNode2());
            System.out.println();
        }else{

            System.out.println("No Route Found.");
            System.out.println();
        }



    }
}
