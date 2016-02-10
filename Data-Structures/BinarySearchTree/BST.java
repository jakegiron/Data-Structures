/**
 * The class for Binary Search Tree
 *
 * @author Jacobo Giron
 */
public class BST {
    protected BSTNode root; //the tree root
    protected BSTNode current;

    /**
     * Get the left subtree of this tree
     *
     * @return the left subtree of this tree
     */
    private BST getLeftSubtree() {
        return root.getLeft();
    }

    /**
     * Get the right subtree of this tree
     *
     * @return the right subtree of this tree
     */
    private BST getRightSubtree() {
        return root.getRight();
    }

    /**
     * Print the tree using in-order traversal strategy
     */
    public void inOrderPrt() {
        inOrderPrt(0);
    }

    /**
     * private, recursive function
     * I slightly changed the method to print right subtree first
     * It is to make the display more easier to read
     *
     * @param indentation
     */
    private void inOrderPrt(int indentation) {
        if (root != null) {
            if (getRightSubtree() != null) getRightSubtree().inOrderPrt(indentation + 1);

            for (int i = 0; i < indentation * 4; i++) System.out.print(" ");

            System.out.println(root.getData());

            if (getLeftSubtree() != null) getLeftSubtree().inOrderPrt(indentation + 1);
        }
    }


    /**
     * Debug function, print the tree for debugging purpose
     */
    public String toString() {
        if (root != null) return root.toString();
        else return null;
    }

    /**
     * insert method
     *
     * @param e
     * @return true if node was able to be inserted otherwise return false
     */
    public boolean insert(int e) {
        if (searchRecursion(e) == null) return insert(root, e);
        else return false;
    }

    /**
     * insert method
     *
     * @param node
     * @param e
     * @return true if the node was able to be inserted otherwise return false
     */
    private boolean insert(BSTNode node, int e) {
        boolean bool = false;
        if (node == null) {
            root = new BSTNode(e);
            bool = true;
        } else if (e < node.getData()) {
            if (node.getLeft().root == null) {
                BST newBSTree = new BST();
                newBSTree.root = new BSTNode(e);
                node.setLeft(newBSTree);
                bool = true;
            } else insert(getLeftSubtree().root, e);
        } else if (e > node.getData()) {
            if (node.getRight().root == null) {
                BST newBSTree = new BST();
                node.setRight(newBSTree);
                newBSTree.root = new BSTNode(e);
                bool = true;
            } else insert(getRightSubtree().root, e);
        }
        return bool;
    }

    /**
     * remove
     *
     * @param e
     * @return return true
     */
    private boolean remove(int e) {
        if (root == null) return false;
        if (root.getData() == e) {
            removeNode(root);
            return true;
        } else if (e < root.getData()) {
            root = getLeftSubtree().root;
            return remove(e);
        } else {
            root = getRightSubtree().root;
            return remove(e);
        }
    }

    /**
     * remove
     *
     * @param node
     */
    private void removeNode(BSTNode node) {
        if (node.getLeft() == null && node.getRight() == null) {
            root = null;
        } else if (node.getLeft() == null && node.getRight() != null) {
            root.setData(root.getRight().root.getData());
            root.setRight(getRightSubtree().getRightSubtree());
        } else if (node.getRight() == null && node.getLeft() != null) {
            root.setData(root.getLeft().root.getData());
            root.setLeft(getLeftSubtree().getLeftSubtree());
        } else {
            BSTNode largeLeft = new BSTNode(node.getData(), node.getLeft(), node.getRight());
            while (largeLeft != null && largeLeft.getRight() != null) largeLeft = largeLeft.getRight().root;
            assert largeLeft != null;
            node.setData(largeLeft.getData());
            root.setLeft(getLeftSubtree());
        }
    }//end removeNode


    /**
     * searchRecursion method
     *
     * @param e
     * @return
     */
    private BSTNode searchRecursion(int e) {
        if (root == null) return null;
        else if (e == root.getData()) return root;
        else if (e < root.getData()) {
            root = getLeftSubtree().root;
            return searchRecursion(e);
        } else {
            root = getRightSubtree().root;
            return searchRecursion(e);
        }
    }

    /**
     * searchNonRecursion method
     *
     * @param e
     * @return
     */
    public BSTNode searchNonRecursion(int e) {
        return searchNonRecursion(root, e);
    }

    private BSTNode searchNonRecursion(BSTNode node, int e) {
        current = node;
        int nodeVal = current.getData();
        boolean found = false;
        while (current != null && nodeVal == e) {
            nodeVal = current.getData();
            if (nodeVal > e) current = current.getLeft().root;
            else if (nodeVal < e) current = current.getRight().root;
        }
        return current;
    }


    public int sum() {
        return sum(root);
    }

    private int sum(BSTNode node) {
        if (node == null) return 0;
        else {
            if (node.getLeft() != null || node.getRight() != null)
                return sum(getLeftSubtree().root)
                        + sum(getRightSubtree().root);
        }
        return node.getData();
    }


    /**
     * Test cases provided by the instructor
     *
     * @throws Exception
     */
    private static void test1() throws Exception {
        BST tree = new BST();
        System.out.println("Initial tree:");
        tree.inOrderPrt(); //test inOrder traversal

        boolean rc = true;

        //test 1: insert method, and test 2 in-order traversal
        rc = tree.insert(12);
        System.out.println("\nInsert 12, inserted=" + rc + ", after adding 12:");
        tree.inOrderPrt();
        rc = tree.insert(6);
        System.out.println("\nInsert 6, inserted=" + rc + ", after adding 6:");
        tree.inOrderPrt();
        rc = tree.insert(19);
        System.out.println("\nInsert 19, inserted=" + rc + ", after adding 19:");
        tree.inOrderPrt();
        rc = tree.insert(4);
        System.out.println("\nInsert 4, inserted=" + rc + ", after adding 4:");
        tree.inOrderPrt();
        rc = tree.insert(8);
        System.out.println("\nInsert 8, inserted=" + rc + ", after adding 8:");
        tree.inOrderPrt();
        rc = tree.insert(16);
        System.out.println("\nInsert 16, inserted=" + rc + ", after adding 16:");
        tree.inOrderPrt();
        rc = tree.insert(8);
        System.out.println("\nInsert 8 (second), inserted=" + rc + ", after adding 8:");
        tree.inOrderPrt();
        rc = tree.insert(5);
        System.out.println("\nInsert 5, inserted=" + rc + ", after adding 5:");
        tree.inOrderPrt();
        rc = tree.insert(9);
        System.out.println("\nInsert 9, inserted=" + rc + ", after adding 9:");
        tree.inOrderPrt();
        rc = tree.insert(20);
        System.out.println("\nInsert 20, inserted=" + rc + ", after adding 20:");
        tree.inOrderPrt();

        System.out.println("Inorder traversal results:");
        tree.inOrderPrt();
        System.out.print("\n\n");

        //test 3: search method
        BSTNode node = tree.searchRecursion(6);
        System.out.println("searchRecursion 6, node=" + node);
        node = tree.searchRecursion(22);
        System.out.println("searchRecursion 22, node=" + node);
        node = tree.searchRecursion(8);
        System.out.println("searchRecursion 8, node=" + node + "\n");

        node = tree.searchNonRecursion(6);
        System.out.println("searchNonRecursion 6, node=" + node);
        node = tree.searchNonRecursion(22);
        System.out.println("searchNonRecursion 22, node=" + node);
        node = tree.searchNonRecursion(8);
        System.out.println("searchNonRecursion 8, node=" + node);

        //test 4: remove method
        rc = tree.remove(30); //test case 0: e does not exist
        System.out.println("\nRemove 30, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(20); //test case 1: leaf
        System.out.println("\nRemove 20, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(4); //test case 2: left is empty
        System.out.println("\nRemove 4, rc=" + rc);
        tree.inOrderPrt();


        rc = tree.remove(19); //test case 3: right is empty
        System.out.println("\nRemove 19, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(6); //test case 4: no subtree is empty
        System.out.println("\nRemove 6, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(12); //more tests: remove root
        System.out.println("\nRemove 12, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(8); //more tests
        System.out.println("\nRemove 8, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(5); //more tests
        System.out.println("\nRemove 5, rc=" + rc);
        tree.inOrderPrt();

        rc = tree.remove(8); //more tests
        System.out.println("\nRemove 8, rc=" + rc);
        tree.inOrderPrt();
        rc = tree.remove(16); //more tests
        System.out.println("\nRemove 16, rc=" + rc);
        tree.inOrderPrt();
        System.out.print("sum=" + tree.sum() + "\n");

        System.out.println("\nAdding a series of numbers:");
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.insert(25);
        tree.insert(28);
        tree.insert(24);
        tree.insert(11);
        tree.insert(5);
        tree.insert(11);
        tree.insert(12);
        tree.insert(50);
        tree.insert(40);
        tree.insert(35);
        tree.inOrderPrt();
        System.out.print("sum=" + tree.sum() + "\n");

        System.out.print("sum=" + tree.sum() + "\n");
        System.out.print("\n\n");

        System.out.println("\nRemove 20 (removeNode case 4):");
        tree.remove(20);
        tree.inOrderPrt();
        System.out.print("sum=" + tree.sum() + "\n");

        System.out.println("Inorder traversal results: ");
        tree.inOrderPrt();
        System.out.print("\n");
        System.out.print("sum=" + tree.sum() + "\n");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        test1();
    }

}
