/**
 * Identifying Isomorphic Trees algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package trees;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO : add unit tests
//TODO : check for empty trees
public class IsomorphicTree {

    // We construct bojections given trees encoding
    public static boolean isomorphicTree(AdjacencyList g1, AdjacencyList g2){
        int center1 = TreeCenter.treeCenter(g1).get(0);
        List<Integer> center2 = TreeCenter.treeCenter(g2);

        TreeNode root1 = RootingTree.rootTree(g1, center1);
        String encode1 = encode(root1);

        for (int c : center2){
            TreeNode root2 = RootingTree.rootTree(g2, c);
            String encode2 = encode(root2);

            // If the two Strings are equal then
            // their encoded canonical forms are isomorphic
            if (encode1.equals(encode2))
                return true;
        }
        return false;
    }

    public static String encode(TreeNode node){

        if (node == null) return "";
        List<String> labels = new ArrayList<String>();

        for (TreeNode child : node.getChildren()){
            labels.add(encode(child));
        }
        Collections.sort(labels);
        String result = "";

        for (String s : labels){
            result += s;
        }

        result = "(" + result + ")";
        return result;
    }
}
