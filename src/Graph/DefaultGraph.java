package Graph;

import java.util.HashMap;

public abstract class DefaultGraph<N extends Node, E extends Edge> implements Multigraph<N, E> {

    private HashMap<Integer, N> nodes = new HashMap<>();
    private HashMap<Integer, E> edges = new HashMap<>();

    @Override
    public boolean addNode(N node) {
        if(nodes.get(node.getId()) == null){
            nodes.put(node.getId(), node);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeNode(N node) {
        if(nodes.get(node.getId()) != null){
            nodes.remove(node.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(E edge) {
        if(edges.get(edge.getId()) == null){
            edges.put(edge.getId(), edge);
            edge.getNode1().addEdge(edge);
            edge.getNode2().addEdge(edge);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(E edge) {
        if(edges.get(edge.getId()) != null){
            edge.getNode1().removeEdge(edge);
            edge.getNode2().removeEdge(edge);
            edges.remove(edge.getId());
            return true;
        }
        return false;
    }

    public N getNode(int id){
        return nodes.get(id);
    }

    public E getEdge(int id){
        return edges.get(id);
    }
}
