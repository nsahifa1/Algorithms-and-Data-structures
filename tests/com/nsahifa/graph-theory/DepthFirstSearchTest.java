/**
 * Class providing unit tests for the DepthFirstSearch class
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import graph.AdjacencyList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearchTest {

    private AdjacencyList graph;

    @Before
    public void setup() {
        int V = 5;
        graph = new AdjacencyList(V);

        graph.addDirectedEdge(0 ,1);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 4);

        // 0->1->2
        // 3->4
    }

    @Test
    public void nodeCount(){

        assertEquals(2, DepthFirstSearch.connectedComponents(graph));

        graph.clear();
        assertEquals(3, DepthFirstSearch.dfs(graph, 0));

        graph.clear();
        assertEquals(2, DepthFirstSearch.dfs(graph, 1));

        graph.clear();
        assertEquals(1, DepthFirstSearch.dfs(graph, 2));

        graph.clear();
        assertEquals(2, DepthFirstSearch.dfs(graph, 3));

        graph.clear();
        graph.addDirectedEdge(2, 3);
        assertEquals(5, DepthFirstSearch.dfs(graph, 0));

        graph.clear();
        assertEquals(1, DepthFirstSearch.connectedComponents(graph));
    }
}
