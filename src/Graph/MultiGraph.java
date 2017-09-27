package Graph;

import java.util.HashMap;
import java.util.List;

public class MultiGraph implements IMultigraph {

    @Override
    public boolean addNode(INode node) {
        return false;
    }

    @Override
    public boolean removeNode(INode node) {
        return false;
    }

    @Override
    public boolean addEdge(IEdge edge) {
        return false;
    }

    @Override
    public boolean removeEdge(IEdge edge) {
        return false;
    }

    @Override
    public List<IEdge> getRoute(INode node1, INode node2) {
        return null;
    }
}