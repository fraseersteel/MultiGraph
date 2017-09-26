package Graph;

import java.util.List;
import java.util.Set;

public interface Node<N> {

    int getId();

    Set<Edge> getConnectedEdges();

    boolean addEdge(Edge edge);

    boolean removeEdge(Edge edge);
}
