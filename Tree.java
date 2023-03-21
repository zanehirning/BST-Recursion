
import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    public String toString() {
        return reToString(root, 0);
    }

    private String reToString(BinaryTreeNode node, int depth) {
        if (node == null) {
            return "";
        }
        String parentStr;
        String indentStr = "";
        for (int i = 0; i<depth; i++) {
            indentStr += "  ";
        }
        return reToString(node.right, depth + 1) + indentStr + node.key + "\n" + reToString(node.left, depth + 1);

    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {
        return name + ": " + reInOrderToString(root);
    }
    private String reInOrderToString(BinaryTreeNode node) {
        if (node == null) {
            return "";
        }
        return reInOrderToString(node.left) + node.key + " " + reInOrderToString(node.right);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        // TODO:
        reFlip(root);
    }
    private void reFlip(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left!=null) {
            reFlip(node.left);
        }
        if (node.right!=null) {
            reFlip(node.right);
        }
        BinaryTreeNode temp = new BinaryTreeNode(node.key, node.left, node.right, node.parent);
        node.right = node.left;
        node.left = temp.right;
        // TODO: Finish flip
    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        // TODO:
        if (node == null) {
            return null;
        }
        if (node.right == null && node.parent == null) {
            return null;
        }
        if (node.right == null) {
            if (node.parent.parent == null) {
                return node.parent;
            }
            else if (node.key.compareTo(node.parent.key) < 0){
                return node.parent;
            }
            return node.parent.parent;
        }
        if (node.right != null) {
            if (node.right.left != null)
                return node.right.left;
            return inOrderSuccessor(node.right);
        }
        if (node.left == null && node.right == null) {
            return node.parent;
        }
        return inOrderSuccessor(node.left);
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        // TODO:
        return reNodesInLevel(root, level, 0);
    }
    private int reNodesInLevel(BinaryTreeNode node, int level, int depth) {
        if (node == null) {
            return 0;
        }
        if (level == 0) {
            return 1;
        }
        if (depth > level) {
            return reNodesInLevel(node, level, depth-1) + reNodesInLevel(node, level, depth-1);
        }
        if (depth == level) {
            return 1;
        }
        return reNodesInLevel(node.left, level, depth + 1) + reNodesInLevel(node.right, level, depth + 1);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        // TODO:
        rePrintAllPaths(root, "");
    }
    private void rePrintAllPaths(BinaryTreeNode node, String path) {
        if (node == null) {
            return;
        }
        path += node.key + " ";
        if (node.left == null && node.right == null) {
            System.out.println(path);
            return;
        }
        if (node.left != null) {
            rePrintAllPaths(node.left, path);
        }
        if (node.right != null) {
            rePrintAllPaths(node.right, path);
        }
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        // TODO:
        return reCountBST(root);
    }
    private int reCountBST(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            if (node.key.compareTo(node.right.key) < 0) {
                return 1+reCountBST(node.right);
            }
            return reCountBST(node.right);
        }
        if (node.right == null) {
            if (node.key.compareTo(node.left.key) > 0) {
                return 1+reCountBST(node.left);
            }
            return reCountBST(node.left);
        }
        if (node.key.compareTo(node.left.key) > 0 && node.key.compareTo(node.right.key) < 0)
            return 1 + reCountBST(node.left) + reCountBST(node.right);
        return reCountBST(node.left) + reCountBST(node.right);

    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    public BinaryTreeNode getByKey(E key) {
        // TODO:
        return reGetByKey(root, key);
    }

    private BinaryTreeNode reGetByKey(BinaryTreeNode node, E key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            return reGetByKey(node.left, key);
        }
        else {
            return reGetByKey(node.right, key);
        }
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        // TODO:
        ArrayList<E> list = new ArrayList<>();
        getNodes(root, list);
        root = null;
        binaryTraversal(list);
    }
    private void binaryTraversal(ArrayList<E> list) {
        if (list == null) {
            return;
        }
        int mid = list.size()/2;
        if (mid < 1) {
            return;
        }
        insert(list.get(mid));
        ArrayList<E> treeRight = new ArrayList<>(list.subList(mid, list.size()));
        binaryTraversal(treeRight);
        ArrayList<E> treeLeft = new ArrayList<>(list.subList(0, mid));
        binaryTraversal(treeLeft);
        //TODO: make work
    }

    private void getNodes(BinaryTreeNode node, ArrayList<E> list) {
        if (node == null) {
            return;
        }
        getNodes(node.left, list);
        list.add(node.key);
        getNodes(node.right, list);
    }


    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}
