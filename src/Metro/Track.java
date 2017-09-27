package Metro;

import Graph.IEdge;
import Graph.INode;

public class Track implements IEdge {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setNode1(INode node) {

    }

    @Override
    public INode getNode1() {
        return null;
    }

    @Override
    public void setNode2(INode node) {

    }

    @Override
    public INode getNode2() {
        return null;
    }

    @Override
    public void setLabel(String element) {

    }

    @Override
    public String getLabel() {
        return null;
    }
}