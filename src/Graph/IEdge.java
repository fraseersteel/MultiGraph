package Graph;

public interface IEdge {

    INode getNode1();

    INode getNode2();

    String getLabel();

    INode getOtherNode(int ID);

}
