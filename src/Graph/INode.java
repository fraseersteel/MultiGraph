package Graph;

import java.util.List;
import java.util.Set;

public interface INode<N> {

    int getId();

    Set<IEdge> getConnectedEdges();

    boolean addEdge(IEdge edge);

    boolean removeEdge(IEdge edge);
}
