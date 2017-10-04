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
        List<Line> route = new ArrayList<>();
        graph.getRoute(startingStation, destinationStation)
                .stream()
                .forEach(line -> route.add((Line)line));
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
}
