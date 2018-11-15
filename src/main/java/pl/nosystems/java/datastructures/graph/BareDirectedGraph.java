package pl.nosystems.java.datastructures.graph;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
public final class BareDirectedGraph<T> {

    public static class Node<T> {
        private final T data;
        private final Collection<Node<T>> edgesOutwards = new ArrayList<>();

        public Node(T data) {
            this.data = data;
        }

        public void addEdgeToNode(final Node<T> target) {
            edgesOutwards.add(target);
        }

        public Collection<Node<T>> getEdgesOutwards() {
            return edgesOutwards;
        }
    }

    private final Collection<Node<T>> nodesInGraph = new ArrayList<>();

    public void addNewNode(final Node<T> node) {
        nodesInGraph.add(node);
    }

    public Collection<Node<T>> getNodesInGraph() {
        return nodesInGraph;
    }
}
