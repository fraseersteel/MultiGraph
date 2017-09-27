package Metro;

import Graph.IEdge;
import Graph.INode;

import java.util.Set;

public class Station implements INode {


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Set<IEdge> getConnectedEdges() {
        return null;
    }

    @Override
    public boolean addEdge(IEdge edge) {
        return false;
    }

    @Override
    public boolean removeEdge(IEdge edge) {
        return false;
    }
}
