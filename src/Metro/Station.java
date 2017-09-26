package Metro;

import Graph.Edge;
import Graph.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Station implements Node {

    private int id;

    private String name;

    private Set<Edge> connectedEdges = new HashSet<>();


    public Station(int id){
        this.id = id;
    }

    public Station(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Set<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    @Override
    public boolean addEdge(Edge edge) {
        if(connectedEdges.contains(edge)){
            return false;
        }
        connectedEdges.add(edge);
        return true;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if(!connectedEdges.contains(edge)){
            return false;
        }
        connectedEdges.remove(edge);
        return true;
    }

    public String getStationName(){
        return name;
    }

    public void setStationName(String name){
        this.name = name;
    }

}
