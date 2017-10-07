package Metro;

import Graph.IEdge;

/**
 * This class is a concrete implementation of an IEdge, representing lines (such as that in a metro system that
 * connects between stations).
 */
public class Line implements IEdge {

    private String label;
    private Station node1;
    private Station node2;

    /**
     * Effects: Initialises and stores the label, and both INodes to which this line is connected to. If the supplied
     * label is null, then the label will be treated as an empty String. If either of the two nodes stored are null,
     * then this method will throw an IllegalArgumentException.
     * <br><br>
     * modifies:this
     *
     * @param label the String which this Line will be called.
     * @param node1 one of the INodes that this line will be connected to.
     * @param node2 the second INode that this line will be connected to.
     * @throws IllegalArgumentException if either of the supplied nodes are null.
     */
    public Line(String label, Station node1, Station node2){
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("IEdges must connect between two nodes.");
        }
        this.label = label;
        this.node1 = node1;
        this.node2 = node2;

        if (label == null) {
            this.label = "";
        }
    }

    /**
     * Effects: Returns the reference to the INode "node1" stored by this instance.
     *
     * @return Returns the first INode stored by this Edge.
     */
    @Override
    public Station getNode1() {
        return node1;
    }

    /**
     * Effects: Returns the reference to the INode "node2" stored by this instance.
     *
     * @return Returns the second INode stored by this Edge.
     */
    @Override
    public Station getNode2() {
        return node2;
    }

    /**
     * Effects: Returns the label of this edge.
     *
     * @return Returns the label of this edge as a String.
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Effects: Given an ID (int), if the ID matches either of the INodes stored by this edge, the method will return
     * the connecting INode's reference.
     *
     * @param ID the int of which to search for a corresponding INode to.
     * @return Returns the INode to which the INode with matching ID is paired to.
     */
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