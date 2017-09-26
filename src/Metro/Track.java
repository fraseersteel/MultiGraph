package Metro;

import Graph.DefaultEdge;
import Graph.Node;

public class Track extends DefaultEdge {

    private String label;

    public Track(int id){
        super(id);
        this.label = "";
    }

    public Track(int id, String line){
        super(id);
        this.label = line;
    }

    public Track(int id, String line, Node node1, Node node2){
        super(id, node1, node2);
        this.label = line;
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
