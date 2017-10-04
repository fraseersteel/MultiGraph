package Metro;

import Graph.IEdge;
import Graph.INode;

import java.util.Set;

public class Station implements INode {

    private String name;
    private int id;

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(int id) {
        this.id = id;
        name = "";
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
