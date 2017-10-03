package Metro;

import Graph.IEdge;
import Graph.IMultigraph;

import java.util.HashMap;
import java.util.List;

public class Metro {

    private IMultigraph graph;

    private HashMap<String, Station> stations = new HashMap<>();

    public Metro(IMultigraph graph){
        this.graph = graph;
    }

    public boolean isStation(String name){
        return stations.get(name) != null;
    }

    public List<IEdge> route(String startingStation, String destinationStation){

        Station startStation = stations.get(startingStation);
        Station destStation = stations.get(destinationStation);
        return graph.getRoute(startStation, destStation);
    }

    public Station getStation(String stationName){
        return stations.get(stationName);
    }


}
