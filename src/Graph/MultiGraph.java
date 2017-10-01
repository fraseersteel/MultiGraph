package Graph;

import java.util.ArrayList;
import java.util.List;

public class MultiGraph implements IMultigraph {

    private List<INode> nodeList;
    private List<IEdge> edgeList;

    public MultiGraph() {
        nodeList = new ArrayList<INode>();
        edgeList = new ArrayList<IEdge>();
    }



    @Override
    public boolean addNode(INode node) {


            for (int i = 0; i < nodeList.size(); i++) {
                if (node.getId() == nodeList.get(i).getId()) {
                    return false;
                }
            }
            nodeList.add(node);
            return true;

    }

    @Override
    public boolean addEdge(IEdge edge) {
        edgeList.add(edge);
        return true;
    }

    @Override
    public List<IEdge> getRoute(INode node1, INode node2) {

        return null;
    }




    public List<INode> getSeries(INode node1, INode node2) {
        List<INode> visited = new ArrayList<INode>();
        List<INode> listofstations = method(node1, node2, visited);


        return listofstations;
    }

    private List<INode> method(INode node1, INode node2, List<INode> visited) {
        ArrayList<INode> nodeChain= new ArrayList<INode>();
        nodeChain.add(node1);
        visited.add(node1);
        if (node1.getId() == node2.getId()) {

            return (nodeChain);
        } else if (successors(node1).size() == 0) {
            nodeChain = new ArrayList<INode>();
            return(nodeChain);
        }
        else if (successors(node1).size() >= 1) {
            for (int i = 0; i < successors(node1).size(); i++) {
                INode successiveNode =  successors(node1).get(i);

                List<INode> temporary = new ArrayList<INode>();
                if (!visited.contains(successiveNode)) {
                    temporary = method(successiveNode, node2, visited);
                }
                if (temporary.size() > 0) {
                    nodeChain.addAll(temporary);
                    return(nodeChain);
                }
            }
        }
        nodeChain = new ArrayList<INode>();
        return(nodeChain);
    }


    private List<INode> successors(INode node) {
            ArrayList<INode> successorList = new ArrayList<INode>();
            int sourceID = node.getId();



            for (int i = 0; i < edgeList.size(); i++) {
                 if (edgeList.get(i).getNode1().getId() == sourceID) {

                     successorList.add(edgeList.get(i).getNode2());
                 } else if (edgeList.get(i).getNode2().getId() == sourceID) {
                     successorList.add(edgeList.get(i).getNode1());
                 }
            }


            return successorList;
    }

}