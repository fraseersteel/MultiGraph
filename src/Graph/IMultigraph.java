
package Graph;

import java.util.List;
import java.util.Set;

public interface IMultigraph {

    /** Attempts to add an implementation of INode to the collection of INodes that implementations of this
     * interface would keep. The INode is added to the collection if there is no other instance of the Node
     * currently stored, and if no currently stored Node shares the same ID.
     *
     * Returns true if the node was added, otherwise false.*/
    boolean addNode(INode node);

    /** Attempts to add an implementation of IEdge to the collection of IEdges that implementations of this
     * interface would keep.
     *
     * Returns true if the node was added, otherwise false.*/
    boolean addEdge(IEdge edge);

    /** Finds a path from the first provided node, to the second provided node, within the multigraph.
     *
     * Returns an implementation of java.util.List which contains implementations of IEdges. The list, starting from
     * index 0, will contain the first line, and consecutively move along the path. */
    List<IEdge> getRoute(INode node1, INode node2);

    List<INode> getNodeList();
    Set<INode> getNodeSet();
    INode getNode(int ID);

    Boolean checkEdgeExists(int id1, int id2);
    List<IEdge> successors(INode node);
}
