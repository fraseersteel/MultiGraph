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


    @Override
    public boolean addNode(INode node) {
        for (INode i : nodeSet) {
            if (i.getId() == node.getId()) {
                return false;
            }
        }

        return (nodeSet.add(node));
    }

    @Override
    public boolean addEdge(IEdge edge) {
        if (checkEdgeExists(edge)) {
            return false;
        }


        edgeList.add(edge);
        return true;
    }


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
                        System.out.println(connectingNode.getName() + " ADDED: " + curNodeToCheck.getName());

                        System.out.println("\n\n\n\n");
                        System.out.println(parents.containsKey(getNodesWithName("NorthStation")));

                    } else {
                        int successorDepth = nodeDepths.get(connectingNode);

                        if (nodeDepths.get(curNodeToCheck) == successorDepth -1) {


///TODO
                            ParentNodeRecord successorParent = new ParentNodeRecord(curNodeToCheck, i);
                            System.out.println("Second route found  " + connectingNode.getName() + "   " +  successorParent.getParent().getName());
                            parents.get(connectingNode).add(successorParent);
                        }
                    }
                }

            }
        }

        List<IEdge> edgeSequence = new ArrayList<>();
        System.out.println("\n\n\n\n");
        System.out.println(parents.containsKey(node2));

        INode destination = node2;

        String optimalEdgeLabel = parents.get(destination).get(0).getEdge().getLabel();



        for (ParentNodeRecord i : parents.get(destination)) {
            if (parents.containsKey(i.getParent())) {
                for (ParentNodeRecord j : parents.get(i.getParent())) {
                    if (j.getEdge().getLabel().equals(i.getEdge().getLabel())) {
                        optimalEdgeLabel = j.getEdge().getLabel();
                    }
                }
            }
        }

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
                System.out.println("NO OPTIMUS");
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
            if (parents.containsKey(destination)) {
                System.out.println(destination.getName()    + parents.get(destination).size());
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
