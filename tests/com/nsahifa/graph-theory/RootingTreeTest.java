import graph.AdjacencyList;
import org.junit.Before;
import org.junit.Test;
import trees.RootingTree;
import trees.TreeNode;

// TODO : complete unit tests
public class RootingTreeTest {

    AdjacencyList graph;

    @Before
    public void setup(){
        int V = 7;
        graph = new AdjacencyList(V);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 6);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(2,3);
        graph.addUndirectedEdge(2,5);
        graph.addUndirectedEdge(2,4);
    }

    @Test
    public void testRootingTree(){
        TreeNode node = RootingTree.rootTree(graph, 1);
    }
}
