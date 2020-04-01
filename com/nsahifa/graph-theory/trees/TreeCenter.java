package trees;

import graph.AdjacencyList;

import java.util.*;

/**
 * An implementation of the Tree Center algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
// TODO : Add Unit tests
public class TreeCenter {

    // Approach : We iteratively pick off each leaf node at a time
    // We end up with a list of vertexes representing potential centers
    public static List<Integer> treeCenter(AdjacencyList graph){

        int[] degree = new int[graph.V()];
        List<Integer> leaf = new ArrayList<Integer>();

        for (int v=0;v<graph.V();++v){
            degree[v] = graph.adj(v).size();
            if (degree[v]==0 || degree[v]==1){
                leaf.add(v);
                degree[v] = 0;
            }
        }

        while (!leaf.isEmpty()){

            List<Integer> new_leaf = new ArrayList<Integer>();

            for (int node : leaf){
                for (int child : graph.adj(node)){
                    degree[child] -= 1;
                    if (degree[child]==1){
                        new_leaf.add(child);

                    }
                }
                degree[node] = 0;
            }

            leaf = new_leaf;
        }
        return leaf;
    }
}
