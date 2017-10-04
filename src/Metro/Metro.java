package Metro;

import Graph.IEdge;
import Graph.IMultigraph;
import Graph.INode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Metro {

    private IMultigraph graph;

    public Metro(IMultigraph graph){
        this.graph = graph;
    }

    public boolean isStation(String name){
        return !getStationsWithName(name).isEmpty();
    }

    public List<Line> route(Station startingStation, Station destinationStation){
        System.out.println("got 2 (in Metro)");
        List<Line> route = new ArrayList<>();
        List<IEdge> edges = graph.getRoute(startingStation, destinationStation);
        System.out.println("got 4 (in Metro)");
                edges.stream()
                .forEach(line -> route.add((Line)line));
        System.out.println("got 5 (in Metro)");
        return route; 
    }

    public List<Station> getStationsWithName(String name){
        List<Station> stationsWithName = new ArrayList<>();
        graph.getNodeList()
                .stream()
                .filter(station -> ((Station)station).getName().equals(name))
                .forEach(station -> stationsWithName.add((Station)station));
        return  stationsWithName;
    }

    public List<Line> getLinesConnectedToStation(Station station){
        List<Line> edges = new ArrayList<>();
        graph.successors(station)
                .stream()
                .forEach(line -> edges.add((Line)line));
        return edges;
    }


//    public List<Line> getAllStations(Station station){
//
//    }
}
