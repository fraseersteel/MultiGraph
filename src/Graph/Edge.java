package Graph;

public interface Edge {

    int getId();

    void setNode1(Node node);

    Node getNode1();

    void setNode2(Node node);

    Node getNode2();

    void setLabel(String element);

    String getLabel();

}
