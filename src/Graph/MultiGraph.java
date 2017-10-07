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
        if (node == null) {
            throw new IllegalArgumentException("null values are not considered to be Nodes.");
        }
        for (INode i : nodeSet) {
            if (i.getId() == node.getId()) {
                return false;
            }
        }

        return (nodeSet.add(node));
    }

    @Override
    public boolean addEdge(IEdge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("null values are not considered to be Edges.");
        }

        if (checkEdgeExists(edge)) {
            return false;
        }


        edgeList.add(edge);
        return true;
    }


    @Override
    public List<? extends INode> getNodesWithName(String name) {
        if (name == null) {
            return (new ArrayList<>());
        }
        ArrayList<INode> matchingNodes = new ArrayList<>();

        for (INode i : nodeSet) {
            if (i.getName().equals(name)) {
                matchingNodes.add(i);
            }
        }

        return matchingNodes;
    }

    @Override
    public List<? extends INode> getNodes() {
        ArrayList<INode> nodeList = new ArrayList<>();
        nodeList.addAll(nodeSet);

        return nodeList;
    }

    @Override
    public List<? extends IEdge> getRoute(INode start, INode target) {
        if (start == null || target == null) {
            throw new IllegalArgumentException("null values are not considered to be Nodes.");
        }


        if (!nodeSet.contains(start) || !nodeSet.contains(target)) {
            return new ArrayList<>();
        }

        INode destination = target;

        List<INode> visited = new ArrayList<>();
        LinkedList<INode> queue = new LinkedList<>();

        HashMap<Integer, ParentNodeRecord> parents = new HashMap<>();

        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            INode curNodeToCheck = queue.poll();

            if (curNodeToCheck.getId() == target.getId()) {
                break;
            }

            List<? extends IEdge> successors = successors(curNodeToCheck);


            for (IEdge i : successors) {

                INode connectingNode = i.getOtherNode(curNodeToCheck.getId());
                if (!visited.contains(connectingNode)) {
                    queue.add(connectingNode);
                    visited.add(connectingNode);


                    if (!parents.containsKey(connectingNode.getId())) {

                        parents.put(connectingNode.getId(), new ParentNodeRecord(curNodeToCheck, i));
                    }

                }
            }


        }


        List<IEdge> edgeSequence = new ArrayList<>();


        while (start.getId() != destination.getId()) {
            ParentNodeRecord curDestinationRecord = parents.get(destination.getId());
            edgeSequence.add(0, curDestinationRecord.getEdge());
            destination = curDestinationRecord.getParent();
        }


        return edgeSequence;
    }
    
    @Override
    public List<? extends IEdge> successors(INode node) {
        if (node == null) {
            throw new IllegalArgumentException("null values are not considered to be Nodes.");
        }
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
        if (edge == null) {
            throw new IllegalArgumentException("null values are not considered to be Edges.");
        }

        for (IEdge i : edgeList) {
            if (i.getLabel().equals(edge.getLabel())
                    && i.getNode1().getId() == edge.getNode2().getId() && i.getNode2().getId() == edge.getNode1().getId()) {
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
