package Graph;

import java.util.HashSet;
import java.util.Set;

public class DefaultNode implements Node {

    protected int id;

    private Set<Edge> connectedEdges = new HashSet<>();

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Set<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    @Override
    public boolean addEdge(Edge edge) {
        if(connectedEdges.contains(edge)){
            return false;
        }
        connectedEdges.add(edge);
        return true;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if(!connectedEdges.contains(edge)){
            return false;
        }
        connectedEdges.remove(edge);
        return true;
    }
}
