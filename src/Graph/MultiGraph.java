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
        edgeList.add(edge);
        return true;
    }

/* TODO consider removal
*
 */
    @Override
    public List<INode> getNodeList() {
        ArrayList<INode> nodeList = new ArrayList<>();
        nodeList.addAll(nodeSet);

        return nodeList;
    }

    public List<IEdge> getRoute(INode node1, INode node2) {
        System.out.println("got 3 (in MultiGraph)");
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
                if (i.getNode1().getId() == curNodeToCheck.getId()) {
                    if (!visited.contains(i.getNode2())) {
                        INode child = i.getNode2();
                        queue.add(child);
                        visited.add(child);

                        if (!parents.containsKey(child)) {
                            parents.put(child, new ParentNodeRecord(curNodeToCheck, i));
                        }


                    }
                } else if (i.getNode2().getId() == curNodeToCheck.getId()) {
                    if (!visited.contains(i.getNode1())) {

                        INode child = i.getNode1();
                        queue.add(child);
                        visited.add(child);

                        if (!parents.containsKey(child)) {
                            parents.put(child, new ParentNodeRecord(curNodeToCheck, i));
                        }
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

    @Override
    public Set<INode> getNodeSet() {
        HashSet<INode> copySet = new HashSet<>();
        copySet.addAll(nodeSet);
        return copySet;
    }

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