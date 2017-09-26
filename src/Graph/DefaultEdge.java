package Graph;

public abstract class DefaultEdge implements Edge {

    protected int id;

    protected Node node1;
    protected Node node2;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setNode1(Node node) {
        this.node1 = node;
    }

    @Override
    public Node getNode1() {
        return node1;
    }

    @Override
    public void setNode2(Node node) {
        this.node2 = node;
    }

    @Override
    public Node getNode2() {
        return node2;
    }

}
