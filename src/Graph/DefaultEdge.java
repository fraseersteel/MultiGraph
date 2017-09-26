package Graph;

public abstract class DefaultEdge implements Edge {

    private int id;

    private Node node1;
    private Node node2;

    public DefaultEdge(int id){
        this.id = id;
    }

    public DefaultEdge(int id, Node node1, Node node2){
        this.id = id;
        this.node1 = node1;
        this.node2 = node2;
    }

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
