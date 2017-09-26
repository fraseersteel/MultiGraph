package Graph;

public interface Multigraph<N extends Node, E extends Edge> {

    boolean addNode(N node);

    boolean removeNode(N node);

    boolean addEdge(E edge);

    boolean removeEdge(E edge);

    N getRoute(N startNode, N endNode);
}
