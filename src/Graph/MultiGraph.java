package Graph;

import java.util.*;

/**
 * This class provides a concrete implementation of the IMultigraph interface. Thus allowing the user to represent
 * a multigraph consisting of INodes and IEdges.
 */
public class MultiGraph implements IMultigraph {

    private List<IEdge> edgeList;
    private Map<Integer, INode> nodeMap;

    /**
     * Effects: Initialises internal data used for storing multigraph information.
     * <br><br>
     * modifies:this
     */
    public MultiGraph() {
        nodeMap = new HashMap<>();
        edgeList = new ArrayList<>();
    }


    /**
     * Effects: Attempts to add the node parameter to the multigraph's collection of INodes.
     * The node will be added only if there is no existing INode with the same ID.
     * <br><br>
     * modifies:this
     *
     * @param node (INode) The INode which the multigraph will attempt to add. Must not be null.
     * @return true if node was successfully added; otherwise false.
     * @throws IllegalArgumentException If node parameter is null.
     */
    @Override
    public boolean addNode(INode node) {
        if (node == null) {
            throw new IllegalArgumentException("null values are not considered to be Nodes.");
        }
        if (nodeMap.containsKey(node.getId())) {
            return false;
        }
        nodeMap.put(node.getId(), node);
        return true;
    }


    /**
     * Effects: Attempts to add the edge parameter to the multigraph's collection of IEdges.
     * The edge will be added only if there is no existing IEdge between the two specifeid nodes with the same label.
     * <br><br>
     * modifies:this
     *
     * @param edge (IEdge) The IEdge which the multigraph will attempt to add. Must not be null.
     * @return true if edge was successfully added; otherwise false.
     * @throws IllegalArgumentException If edge parameter is null.
     */
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


    /**
     * Effects: Returns a java.util.List containing references to the INode(s) stored within the multigraph.
     *
     * @return A List containing references to the INode(s) stored within the multigraph.
     */
    @Override
    public List<? extends INode> getNodes() {
        ArrayList<INode> nodeList = new ArrayList<>();
        nodeList.addAll(nodeMap.values());

        return nodeList;
    }


    /**
     * Effects: Returns the INode which has the same ID as specified by the ID parameter.
     *
     * @param ID (int) The ID for which the multigraph will return all matching INodes for.
     * @return A List containing references to the INodes with names matching the name parameter.
     */
    @Override
    public INode getNode(int ID) {
        return nodeMap.get(ID);
    }


    /**
     * Effects: Returns a java.util.List containing references to the IEdge(s) which contain the specified INode.
     * Will return an empty list if no corresponding IEdges are found.
     *
     * @param node (INode) The INode for which the graph will return all edges that contain said INode.
     * @return A List containing references to any IEdges which contain the node specified by the node parameter.
     * @throws IllegalArgumentException If node parameter is null.
     */
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


    /**
     * Effects: Attempts to find and return a path of IEdges from the first provided INode (start parameter) to the second INode
     * (target parameter). If no path exists, or the start is the same as the target, an empty list will be returned.
     * The path found will contain the least number of IEdges possible. The List returned will have the IEdge from start
     * being at index 0, and IEdge going into target being at path size -1.
     *
     * @param start  (INode) The INode which the multigraph will attempt to find a route from. Must not be null.
     * @param target (INode) The INode which the multigraph will attempt to find a route to. Must not be null.
     * @return A List containing references to the IEdges in sequence of the path found from start to target.
     * @throws IllegalArgumentException If either start or target parameters are null.
     */
    @Override
    public List<? extends IEdge> getRoute(INode start, INode target) {
        if (start == null || target == null) {
            throw new IllegalArgumentException("null values are not considered to be Nodes.");
        }

        if (!nodeMap.containsKey(start.getId()) || !nodeMap.containsKey(target.getId())) {
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

    /**
     * Effects: This private method is used to check if an IEdge of identical qualities is already stored.
     *
     * @param edge the IEdge to check if an identical IEdge already exists. Can not be null.
     * @return true if an identical valued IEdge exists within the multigraph; otehrwise false.
     * @throws IllegalArgumentException If edge parameter is null.
     */
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

    /**
     * Effects: This class is used to maintain information about paths constructed within the multigraph. This includes the
     * parent of an INode, and the edge that connects the INode to the parent.
     */
    private class ParentNodeRecord {
        private INode parent;
        private IEdge edge;

        /**
         * Effects: Initialises internal data used for storing INode parent information.
         *
         * @param parent (INode) The parent of the INode that the instance of this class will represent.
         * @param edge   (IEdge) The edge that connects to the parent of the INode that the instance of this class will represent.
         */
        ParentNodeRecord(INode parent, IEdge edge) {
            this.parent = parent;
            this.edge = edge;
        }

        /**
         * Effects: This method will return the INode that is the parent of the INode that this class represents.
         *
         * @return an INode which is the parent of the INode that this class represents.
         */
        INode getParent() {
            return parent;
        }

        /**
         * Effects: This method will return the IEDge that connects to the parent of the INode that this class represents.
         *
         * @return an IEdge which connects to the parent of the INode that this class represents.
         */
        IEdge getEdge() {
            return edge;
        }
    }
}
