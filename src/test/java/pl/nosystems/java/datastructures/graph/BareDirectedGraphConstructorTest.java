package pl.nosystems.java.datastructures.graph;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

public final class BareDirectedGraphConstructorTest {

    @Test
    public void defaultConstructorTest() {
        final BareDirectedGraph<Integer> graph = new BareDirectedGraph<>();
        assertNotNull(graph.getNodesInGraph());
        assertThat(graph.getNodesInGraph().size(),is(equalTo(0)));
    }
}
