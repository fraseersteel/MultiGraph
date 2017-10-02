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


    public List<IEdge> getRoute(INode node1, INode node2) {
        List<INode> visited = new ArrayList<INode>();
        List<IEdge> currentEdgeSequence = new ArrayList<IEdge>();
        List<IEdge> listofstations = method2(node1, node2, visited, currentEdgeSequence);


        return listofstations;
    }

    @Override
    public List<INode> getNodeList() {
        ArrayList<INode> copyList = new ArrayList<>();
        copyList.addAll(nodeList);
        return copyList;
    }

    @Override
    /* Returns the node object which contains the same ID as the ID specified.
    * If no matching ID, returns null
     */
    public INode getNode(int ID) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getId() == ID) {

                return nodeList.get(i);
            }
        }

        return null;
    }

    private List<IEdge> method2(INode node1, INode node2, List<INode> visited, List<IEdge> currentEdgeChain) {
        ArrayList<IEdge> edgeChain = new ArrayList<IEdge>();
        visited.add(node1);
        if (node1.getId() == node2.getId()) {

            return (currentEdgeChain);
        } else if (successors2(node1).size() == 0) {
            edgeChain = new ArrayList<IEdge>();
            return(edgeChain);
        }
        else if (successors2(node1).size() >= 1) {
            for (int i = 0; i < successors2(node1).size(); i++) {
                INode successiveNode;



                if (successors2(node1).get(i).getNode1().getId() == node1.getId()) {
                    successiveNode = successors2(node1).get(i).getNode2();
                } else {
                    successiveNode = successors2(node1).get(i).getNode1();
                }

                List<IEdge> temporary = new ArrayList<IEdge>();
                temporary.add(successors2(node1).get(i));
                if (!visited.contains(successiveNode)) {
                    temporary = method2(successiveNode, node2, visited,temporary);

                    if (temporary.size() > 0) {
                        currentEdgeChain.addAll(temporary);
                        return(currentEdgeChain);
                    }
                }

            }
        }
        edgeChain = new ArrayList<IEdge>();
        return(edgeChain);
    }


    private List<IEdge> successors2(INode node) {
        ArrayList<IEdge> successorList = new ArrayList<IEdge>();
        int sourceID = node.getId();



        for (int i = 0; i < edgeList.size(); i++) {
            if (edgeList.get(i).getNode1().getId() == sourceID) {

                successorList.add(edgeList.get(i));
            } else if (edgeList.get(i).getNode2().getId() == sourceID) {
                successorList.add(edgeList.get(i));
            }
        }


        return successorList;
    }
    
}