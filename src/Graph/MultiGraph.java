package Graph;

import java.util.*;

public class MultiGraph implements IMultigraph {

    private List<IEdge> edgeList;
    private List<INode> nodes;

    public MultiGraph() {
        nodes = new ArrayList<>();
        edgeList = new ArrayList<>();
    }


    @Override
    public boolean addNode(INode node) {
        for (INode i : nodes) {
            if (i.getId() == node.getId()) {
                return false;
            }
        }

        return (nodes.add(node));
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
        List<INode> matchingNodes = new ArrayList<>();

        for (INode i : nodes) {
            if (i.getName().equals(name)) {
                matchingNodes.add(i);
            }
        }

        return matchingNodes;
    }

    @Override
    public List<INode> getNodes() {
        return Collections.unmodifiableList(this.nodes);
    }

    public List<IEdge> getRoute(INode node1, INode node2) {
        INode destination = node2;

        List<INode> visited = new ArrayList<>();
        LinkedList<INode> queue = new LinkedList<>();

        HashMap<INode, ParentNodeRecord> parents = new HashMap<>();

        visited.add(node1);
        queue.add(node1);
        while (!queue.isEmpty()) {
            INode curNodeToCheck = queue.poll();

            if (curNodeToCheck.getId() == node2.getId()) {
                break;
            }

            List<IEdge> successors = successors(curNodeToCheck);


            for (IEdge i : successors) {

                INode connectingNode = i.getOtherNode(curNodeToCheck.getId());
                if (!visited.contains(connectingNode)) {
                    queue.add(connectingNode);
                    visited.add(connectingNode);


                    if (!parents.containsKey(connectingNode)) {

                        parents.put(connectingNode, new ParentNodeRecord(curNodeToCheck, i));
                    }

                }
            }


        }


        List<IEdge> edgeSequence = new ArrayList<>();


        while (node1.getId() != destination.getId()) {
            ParentNodeRecord curDestinationRecord = parents.get(destination);
            edgeSequence.add(0, curDestinationRecord.getEdge());
            destination = curDestinationRecord.getParent();
        }


        return edgeSequence;
    }

//    @Override
//    public Set<INode> getNodeSet() {
//        HashSet<INode> copySet = new HashSet<>();
//        copySet.addAll(nodes);
//        return copySet;
//    }

    @Override
    /* Returns the node object which contains the same ID as the ID specified.
    * If no matching ID, returns null
     */
    public INode getNode(int ID) {

        for (INode i : nodes) {
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
                    && (i.getNode1().getId() == edge.getNode1().getId() || i.getNode1().getId() == edge.getNode2().getId())
                    && (i.getNode2().getId() == edge.getNode1().getId() || i.getNode1().getId() == edge.getNode2().getId())) {
                return true;
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