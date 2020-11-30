/**
 * An implementation of the TreeNode object structure
 */
package trees;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    // Identifier of the node in the tree
    private int id;

    // Parent of the current node
    private TreeNode parent;

    // children of the current node
    private List<TreeNode> children;

    // Constructor with id
    public TreeNode(int id){
        this(id, null);
    }

    // Constructor with id and parent
    public TreeNode(int id, TreeNode parent){
        this.id = id;
        this.parent = parent;
        this.children = new ArrayList<TreeNode>();
    }

    // Constructor with id, parent and children
    public TreeNode(int id, TreeNode parent, List<TreeNode> children){
        this(id, parent);
        this.children = children;
    }

    // add childred
    public void addChildren(TreeNode... nodes){
        for (TreeNode node : nodes){
            children.add(node);
        }
    }

    // getter of the identifier
    public int getId(){
        return id;
    }

    // getter of the field parent
    public TreeNode getParent(){
        return parent;
    }

    // getter of the filed children
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeNode){
            return getId() == ((TreeNode)obj).getId();
        }
        return false;
    }
}