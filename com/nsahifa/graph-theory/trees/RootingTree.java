/**
 * Class providing method for routing a tree
 */
package trees;

import graph.AdjacencyList;

import java.util.ArrayList;

public class RootingTree {

    public static TreeNode rootTree(AdjacencyList graph, int root){
        TreeNode nodeRoot = new TreeNode(root);
        return buildTree(graph, nodeRoot,null);
    }

    public static TreeNode buildTree(AdjacencyList graph, TreeNode node, TreeNode parent){
        for (int id : graph.adj(node.getId())){
            if (parent!=null && id==parent.getId() )
                continue;

            TreeNode child = new TreeNode(id, node, new ArrayList<TreeNode>());
            node.addChildren(child);
            buildTree(graph, child, node);
        }
        return node;
    }
}
