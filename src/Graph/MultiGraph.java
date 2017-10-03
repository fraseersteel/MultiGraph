package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

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


    public List<IEdge> getRoute(INode node1, INode node2) {

        INode destination = node2;

        List<INode> visited = new ArrayList<>();
        Queue<INode> queue = new LinkedList<>();

        List<ParentNodeRecord> parents = new ArrayList<>();

        visited.add(node1);
        queue.add(node1);
        while (!queue.isEmpty()) {
            INode temp = queue.poll();
            if (temp.getId() == node2.getId()) {
                break;
            }

            List<IEdge> successors = successors(temp);

            for (IEdge i : successors) {
                if (i.getNode1().getId() == temp.getId()) {
                    if (!visited.contains(i.getNode2())) {
                        INode child = i.getNode2();
                        queue.add(child);
                        parents.add(new ParentNodeRecord(child, i.getNode1(), i));
                    }
                } else {
                    if (!visited.contains(i.getNode1())) {
                        INode child = i.getNode1();
                        queue.add(child);
                        parents.add(new ParentNodeRecord(child, i.getNode2(), i));
                    }

                }
            }
        }


        List<IEdge> edgeSequence = new ArrayList<>();

        while (node1.getId() != destination.getId()) {
            System.out.println(destination.getId());


            for (ParentNodeRecord i : parents) {
                if (i.getNode().getId() == destination.getId()) {
                    edgeSequence.add(0, i.getEdge());
                    destination = i.getParent();
                    break;
                }
            }
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


    private List<IEdge> successors(INode node) {
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
        private INode node;
        private INode parent;
        private IEdge edge;

        ParentNodeRecord(INode node, INode parent, IEdge edge) {
            this.node = node;
            this.parent = parent;
            this.edge = edge;
        }

        INode getNode() {
            return node;
        }

        INode getParent() {
            return parent;
        }

        IEdge getEdge() {
            return edge;
        }
    }

}