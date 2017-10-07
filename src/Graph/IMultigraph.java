

package Graph;

import java.util.List;

/**
 * This interface represents a multigraph. The multigraph consists of a set of INodes (each with a unique ID),
 * and IEdges between the contained INodes.
 */
public interface IMultigraph {

    /**
     * Attempts to add the node parameter to the multigraph's collection of INodes.
     * The node will be added only if there is no existing INode with the same ID.
     * <br>
     * modifies:this
     *
     * @param node (INode) The INode which the multigraph will attempt to add. Must not be null.
     * @return true if node was successfully added; otherwise false.
     * @throws IllegalArgumentException If node paramter is null.
     */
    boolean addNode(INode node);


    /**
     * Attempts to add the edge parameter to the multigraph's collection of IEdges.
     * The edge will be added only if there is no existing IEdge between the two specifeid nodes with the same label.
     * <br>
     * modifies:this
     *
     * @param edge (IEdge) The IEdge which the multigraph will attempt to add. Must not be null.
     * @return true if edge was successfully added; otherwise false.
     * @throws IllegalArgumentException If edge paramter is null.
     */
    boolean addEdge(IEdge edge);


    /**
     * Returns a java.util.List containing references to the INode(s) stored within the multigraph.
     *
     * @return A List containing references to the INode(s) stored within the multigraph.
     */
    List<? extends INode> getNodes();


    /**
     * Returns a java.util.List containing references to the INode(s) which have the same name as specified by the name
     * parameter. Will return an empty list if no matching nodes are found.
     *
     * @param name (String) The name for which the multigraph will return all matching INodes for.
     * @return A List containing references to any INodes with names matching the name parameter.
     */
    List<? extends INode> getNodesWithName(String name);


    /**
     * Returns the INode which has the same ID as specified by the ID parameter.
     *
     * @param ID (int) The ID for which the multigraph will return all matching INodes for.
     * @return A List containing references to the INodes with names matching the name parameter.
     */
    INode getNode(int ID);


    /**
     * Returns a java.util.List containing references to the IEdge(s) which contain the specified INode.
     * Will return an empty list if no corresponding IEdges are found.
     *
     * @param node (INode) The INode for which the graph will return all edges that contain said INode.
     * @return A List containing references to any IEdges which contain the node specified by the node parameter.
     * @throws IllegalArgumentException If node paramter is null.
     */
    List<? extends IEdge> successors(INode node);


    /**
     * Attempts to find and return a path of IEdges from the first provided INode (start parameter) to the second INode
     * (target parameter). If no path exists, or the start is the same as the target, an empty list will be returned.
     * The path found will contain the least number of IEdges possible. The List returned will have the IEdge from start
     * being at index 0, and IEdge going into target being at path size -1.
     *
     * @param start  (INode) The INode which the multigraph will attempt to find a route from. Must not be null.
     * @param target (INode) The INode which the multigraph will attempt to find a route to. Must not be null.
     * @return A List containing references to the IEdges in sequence of the path found from start to target.
     * @throws IllegalArgumentException If either start or target paramters are null.
     */
    List<? extends IEdge> getRoute(INode start, INode target);
}
