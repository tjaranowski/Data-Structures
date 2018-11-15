package pl.nosystems.java.datastructures.graph;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class BareDirectedGraphTest {

    private BareDirectedGraph<Integer> graph;

    @Before
    public void setUp() {
        graph = new BareDirectedGraph<>();
    }

    @Test
    public void addNewNodeTest() {
        final BareDirectedGraph.Node<Integer> node = new BareDirectedGraph.Node<>(100);
        graph.addNewNode(node);

        assertThat(graph.getNodesInGraph().size(),is(equalTo(1)));
        assertEquals(node,graph.getNodesInGraph().iterator().next());

        assertNotNull(graph.getNodesInGraph().iterator().next().getEdgesOutwards());
        assertThat(graph.getNodesInGraph().iterator().next().getEdgesOutwards().size(),is(equalTo(0)));
    }

    @Test
    public void addEdgeToNodeTest() {
        final BareDirectedGraph.Node<Integer> node1 = new BareDirectedGraph.Node<>(100);
        final BareDirectedGraph.Node<Integer> node2 = new BareDirectedGraph.Node<>(200);

        node1.addEdgeToNode(node2);
        assertThat(node1.getEdgesOutwards().size(),is(equalTo(1)));
        assertThat(node1.getEdgesOutwards().iterator().next(),is(equalTo(node2)));
    }
}
