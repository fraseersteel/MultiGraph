package Metro;

import Graph.IEdge;
import Graph.INode;

public class Line implements IEdge {

    private String label;
    private Station node1;
    private Station node2;


    public Line(String label, Station node1, Station node2){
        this.label = label;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public Station getNode1() {
        return node1;
    }

    @Override
    public Station getNode2() {
        return node2;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Station getOtherNode(int ID) {
        if(node1.getId() == ID){
            return node2;
        }
        else{
            return node1;
        }
    }
}