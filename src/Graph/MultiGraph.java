package Graph;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class MultiGraph implements IMultigraph {

    private List<IEdge> edgeList;
    private Set<INode> nodeSet;

    public MultiGraph() {
        nodeSet = new HashSet<>();
        edgeList = new ArrayList<>();
    }

//adds an available node
    @Override
    public boolean addNode(INode node) {
        for (INode i : nodeSet) {
            if (i.getId() == node.getId()) {
                return false;
            }
        }

        return (nodeSet.add(node));
    }

    //adds an edge to a node
    @Override
    public boolean addEdge(IEdge edge) {
        if (checkEdgeExists(edge)) {
            return false;
        }


        edgeList.add(edge);
        return true;
    }

//returns the nodes with the given string name
    @Override
    public List<INode> getNodesWithName(String name) {
        ArrayList<INode> matchingNodes = new ArrayList<>();

        for (INode i : nodeSet) {
            if (i.getName().equals(name)) {
                matchingNodes.add(i);
            }
        }
 
        return matchingNodes;
    }

    /* TODO consider removal
    *
     */

    //returns a list of all nodes
    @Override
    public List<INode> getNodes() {
        ArrayList<INode> nodeList = new ArrayList<>();
        nodeList.addAll(nodeSet);

        return nodeList;
    }


    public List<IEdge> getRoute(INode node1, INode node2) {


        LinkedList<INode> queue = new LinkedList<>();

        HashMap<INode, ArrayList<ParentNodeRecord>> parents = new HashMap<>();
        queue.add(node1);

        HashMap<INode, Integer> nodeDepths = new HashMap<>();
        nodeDepths.put(node1, 0);

        while (!queue.isEmpty()) {
            INode curNodeToCheck = queue.poll();

            List<IEdge> successors = successors(curNodeToCheck);


            for (IEdge i : successors) {

                INode connectingNode = i.getOtherNode(curNodeToCheck.getId());

                if (connectingNode == node1) {
                    //Do nothing
                } else {
                    if (!parents.containsKey(connectingNode)) {
                        queue.add(connectingNode);
                        nodeDepths.put(connectingNode, nodeDepths.get(curNodeToCheck) + 1);
                        ParentNodeRecord successorParent = new ParentNodeRecord(curNodeToCheck, i);
                        parents.put(connectingNode, new ArrayList<>());
                        parents.get(connectingNode).add(successorParent);


                    } else {
                        int successorDepth = nodeDepths.get(connectingNode);

                        if (nodeDepths.get(curNodeToCheck) == successorDepth -1) {


///TODO
                            ParentNodeRecord successorParent = new ParentNodeRecord(curNodeToCheck, i);

                            parents.get(connectingNode).add(successorParent);
                        }
                    }
                }

            }
        }

        List<IEdge> edgeSequence = new ArrayList<>();



        INode destination = node2;

        String optimalEdgeLabel = parents.get(destination).get(0).getEdge().getLabel();

for (INode i : parents.keySet()) {
    System.out.println(i.getName() + "   " + parents.get(i).size());
}
System.out.println();
        HashMap<String, Integer> destinationEdgeLabelLengths = new HashMap<>() ;
        for (ParentNodeRecord i : parents.get(destination)) {
            INode workingDestination = i.getParent();
            int currentEdgeLength = 0;
            optimalEdgeLabel = i.getEdge().getLabel();

            boolean ended = false;
            while (parents.containsKey(workingDestination)&& !ended) {
                ended = true;
                for (ParentNodeRecord j : parents.get(workingDestination)) {
                    if (j.getEdge().getLabel().equals(optimalEdgeLabel)) {
                        currentEdgeLength ++;
                        workingDestination = j.getParent();
                        ended = false;
                    }


                }


                destinationEdgeLabelLengths.put(i.getEdge().getLabel(), currentEdgeLength);

            }

            destinationEdgeLabelLengths.put(optimalEdgeLabel, currentEdgeLength);
        }







        int optimalEdgeLength = 0;
        for (String i : destinationEdgeLabelLengths.keySet()) {
            System.out.println(i + destinationEdgeLabelLengths.get(i));
            if (optimalEdgeLength < destinationEdgeLabelLengths.get(i)) {
                optimalEdgeLabel = i;
            }
        }



        System.out.println(optimalEdgeLabel);

        destination = node2;
        while (node1.getId() != destination.getId()) {
            boolean optimalFound = false;
            for (ParentNodeRecord i : parents.get(destination)) {
                if (i.getEdge().getLabel().equals(optimalEdgeLabel)) {
                    edgeSequence.add(0, i.getEdge());
                    destination = i.getParent();
                    optimalFound = true;
                    break;
                }
            }
            if (!optimalFound) {

                if (parents.containsKey(destination)) {


                    optimalEdgeLabel = parents.get(destination).get(0).getEdge().getLabel();


                    for (ParentNodeRecord i : parents.get(destination)) {
                        if (parents.containsKey(i.getParent())) {
                            for (ParentNodeRecord j : parents.get(i.getParent())) {
                                if (j.getEdge().getLabel().equals(i.getEdge().getLabel())) {
                                    optimalEdgeLabel = j.getEdge().getLabel();
                                }
                            }
                        }
                    }
                }
            }



        }

        return edgeSequence;
    }

//    @Override
//    public Set<INode> getNodeSet() {
//        HashSet<INode> copySet = new HashSet<>();
//        copySet.addAll(nodeSet);
//        return copySet;
//    }

    @Override
    /* Returns the node object which contains the same ID as the ID specified.
    * If no matching ID, returns null
     */
    public INode getNode(int ID) {

        for (INode i : nodeSet) {
            if (i.getId() == ID) {

                return i;
            }
        }

        return null;
    }


    public List<IEdge> successors(INode node) {
        ArrayList<IEdge> successorList = new ArrayList<>();
        int sourceID = node.getId();


        for (IEdge i : edgeList) {
            if (i.getNode1().getId() == sourceID) {

                successorList.add(i);
            } else if (i.getNode2().getId() == sourceID) {
                successorList.add(i);
            }
        }

        return successorList;
    }

    //checks if a edge exists between two nodes
    private Boolean checkEdgeExists(IEdge edge) {
        for (IEdge i : edgeList) {
            if (i.getLabel().equals(edge.getLabel())
                    && i.getNode1().getId() == edge.getNode1().getId()
                    && i.getNode2().getId() == edge.getNode2().getId()) {
                return false;
            }
        }
        return false;
    }

    class ParentNodeRecord {
        private INode parent;
        private IEdge edge;


        ParentNodeRecord(INode parent, IEdge edge) {
            this.parent = parent;
            this.edge = edge;
        }

        INode getParent() {
            return parent;
        }

        IEdge getEdge() {
            return edge;
        }
    }


}
