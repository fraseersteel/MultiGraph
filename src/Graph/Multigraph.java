
package Graph;

import java.util.List;

public interface Multigraph {

    boolean addNode(INode node);

    boolean removeNode(INode node);

    boolean addEdge(IEdge edge);

    boolean removeEdge(IEdge edge);

    List<IEdge> getRoute(INode node1, INode node2);
}
