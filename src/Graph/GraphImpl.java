package Graph;

import java.util.HashMap;

public abstract class GraphImpl<N extends Node> implements Multigraph<N, EdgeImpl> {

    private HashMap<Integer, N> nodes = new HashMap<>();
    private HashMap<Integer, EdgeImpl> edges = new HashMap<>();

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
    public boolean addEdge(EdgeImpl edge) {
        if(edges.get(edge.getId()) == null){
            edges.put(edge.getId(), edge);
            edge.getNode1().addEdge(edge);
            edge.getNode2().addEdge(edge);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(EdgeImpl edge) {
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

    public EdgeImpl getEdge(int id){
        return edges.get(id);
    }
}
