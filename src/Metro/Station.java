package Metro;

import Graph.INode;

/**
 * This class is a concrete implementation of an INode, representing stations (such as that in a metro system).
 */
public class Station implements INode {

    private String name;
    private int id;

    /**
     * Effects: Initialises the ID and name of the Station to that of the supplied parameters (of "id" and "name")
     * respectively. If the name is specified as null; the Station's name will be set as an empty String.
     *
     * @param id the int which this Station will have as the ID number
     * @param name the String which this Station will have as the name
     */
    public Station(int id, String name) {
        this.id = id;
        this.name = name;

        if (name == null) {
            this.name = "";
        }
    }

    /**
     * Effects: Initialises the ID to that of the supplied parameter "id"
     * name is initialised as an empty string.
     * respectively
     *
     * @param id the int which this Station will have as the ID number
     */
    public Station(int id) {
        this.id = id;
        name = "";
    }

    /**
     * Effects: Returns the ID (int) of the node that this object represents.
     *
     * @return the ID of this node as an int.
     */
    @Override
    public int getId() {
        return id;
    }


    /**
     * Effects: Returns the name (String) of the node that this object represents.
     *
     * @return the name of this node as a String.
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Effects: Sets the name of this node.
     * @param name the name which will be stored for this node. Must not be null
     * @throws IllegalArgumentException if the name provided upon method call is null.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
