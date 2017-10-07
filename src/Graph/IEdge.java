package Graph;

/**
 * This interface represents a data structure for an Edge. Edges contain two INodes (referred to as node1, and node2)
 * and a label. The edge represents a connection between both nodes.
 */
public interface IEdge {

    /**
     * Effects: Returns the reference to the INode "node1" stored by this instance.
     *
     * @return Returns the first INode stored by this Edge.
     */
    INode getNode1();


    /**
     * Effects: Returns the reference to the INode "node2" stored by this instance.
     *
     * @return Returns the second INode stored by this Edge.
     */
    INode getNode2();


    /**
     * Effects: Returns the label of this edge.
     *
     * @return Returns the label of this edge as a String.
     */
    String getLabel();

    /**
     * Effects: Given an ID (int), if the ID matches either of the INodes stored by this edge, the method will return
     * the connecting INode's reference.
     *
     * @param ID the int of which to search for a corresponding INode to.
     * @return Returns the INode to which the INode with matching ID is connected to.
     */
    INode getOtherNode(int ID);

}
