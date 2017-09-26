package Metro;

import Graph.DefaultNode;
import Graph.Edge;
import Graph.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Station extends DefaultNode {

    private String name;

    public Station(int id){
        super(id);
    }

    public Station(int id, String name){
        super(id);
        this.name = name;
    }

    public String getStationName(){
        return name;
    }

    public void setStationName(String name){
        this.name = name;
    }

}
