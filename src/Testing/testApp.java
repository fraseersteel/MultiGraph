package Testing;

import Graph.IEdge;
import Graph.IMultigraph;
import Graph.INode;
import Graph.MultiGraph;
import Metro.Line;
import Metro.Station;

import java.util.List;

public class testApp {

    public testApp() {

        System.out.println("Test Start");
        IMultigraph MG = new MultiGraph();


        Station first = new Station(1, "AAA");
        MG.addNode(first);
        System.out.println("confirm" + first.getId());

        Station second = new Station(2, "B");
        System.out.println(first.getId());
        System.out.println( MG.addNode(second));

        
        Station third = new Station(3, "C");
        MG.addNode(third);
        Station fourth = new Station(4, "D");
        MG.addNode(fourth);
        Station fifth = new Station(5, "E");
        MG.addNode(fifth);
        Station sixth = new Station(6, "F");
        MG.addNode(sixth);
        Station seventh = new Station(7, "G");
        MG.addNode(seventh);
        Station eighth = new Station(8, "H");
        MG.addNode(eighth);
        Station ninth = new Station(9, "I");
        MG.addNode(ninth);


        List<INode> stationlist = MG.getStations();
        System.out.println("asdasasdasdasdasdd" + stationlist.size());

        MG.addEdge(new Line("alp", first, third));
        MG.addEdge(new Line("ban", second, third));
        MG.addEdge(new Line("can", second, fourth));
        MG.addEdge(new Line("dan", second, fifth));
        MG.addEdge(new Line("eaa", fifth, seventh));
        MG.addEdge(new Line("ffa", third, sixth));
        MG.addEdge(new Line("gga", sixth, eighth));
        MG.addEdge(new Line("has", sixth, ninth));

        List<IEdge> edgelist = MG.getLines();
        System.out.println(edgelist.size());


        System.out.println(MG.getRoute(third, ninth));

        for (int i = 0; i < edgelist.size(); i++) {
            System.out.println(edgelist.get(i).getLabel());
        }

        System.out.println("PART 2");

        List<INode> temporariom = MG.successors(third);
        for (int i = 0; i < temporariom.size(); i++) {
            System.out.println(temporariom.get(i).getId());
        }

        System.out.println("PART 3");
        List<INode> tempas = MG.getSeries(fourth, eighth);
        for (int i = 0; i < tempas.size(); i++) {
            System.out.println(tempas.get(i).getId());
        }

    }
}
