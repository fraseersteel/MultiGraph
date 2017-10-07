package Graph;

/**
 * This interface represents a data structure for a Node. Nodes contain a unique ID number and a name (String)
 */
public interface INode {

    /**
     * Effects: Returns the ID (int) of the node that this object represents.
     *
     * @return the ID of this node as an int.
     */
    int getId();


    /**
     * Effects: Returns the name (String) of the node that this object represents.
     *
     * @return the name of this node as a String.
     */
    String getName();


    /**
     * Effects: Sets the name of this node.
     * @param name the name which will be stored for this node. Must not be null
     * @return the name of this node as a String.
     * @throws IllegalArgumentException if the name provided upon method call is null.
     */
    void setName(String name);
}
