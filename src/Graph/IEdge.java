package Graph;

public interface IEdge {


    void setNode1(INode node);

    INode getNode1();

    void setNode2(INode node);

    INode getNode2();

    void setLabel(String element);

    String getLabel();

}
