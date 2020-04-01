/**
 * Units tests for the breadth first search class
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import graph.AdjacencyList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearchTest {

    private AdjacencyList graph;

    @Before
    public void setup(){
        int V = 13;
        graph = new AdjacencyList(V);

        graph.addUndirectedEdge(0,8);
        graph.addUndirectedEdge(0,10);
        graph.addUndirectedEdge(10,2);
        graph.addUndirectedEdge(10,3);
        graph.addUndirectedEdge(10,4);
        graph.addUndirectedEdge(4,12);
        graph.addUndirectedEdge(12,11);
        graph.addUndirectedEdge(4,1);
        graph.addUndirectedEdge(1,7);
        graph.addUndirectedEdge(7,11);
        graph.addUndirectedEdge(1,9);
        graph.addUndirectedEdge(1,5);
        graph.addUndirectedEdge(5,6);
    }

    @Test
    public void testShortestPath(){
        List<Integer> path = BreadthFirstSearch.reconstructPath(graph, 10, 11);
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(10);
        expected.add(4);
        expected.add(12);
        expected.add(11);
        assertEquals(expected, path);
    }
}
