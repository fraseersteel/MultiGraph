package Metro;

import Graph.DefaultEdge;
import Graph.Node;

public class Track extends DefaultEdge {

    private String label;

    public Track(int id){
        this.id = id;
        this.label = "";
    }

    public Track(int id, String line){
        this.id = id;
        this.label = line;
    }

    public Track(int id, String line, Node node1, Node node2){
        this.id = id;
        this.label = line;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public void setLabel(String element) {
        label = element;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
