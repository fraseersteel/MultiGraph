package View;

import Control.Controller;
import Graph.IEdge;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private String INVALID = "Invalid";

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

            IEdge edge = edges.get(0);
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
