package ifgoiano;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Node<Integer> root = tree.createTree(10);
        // tree.insert(root, 15);
        // tree.insert(root, 4);
        // tree.insert(root, 6);
        // tree.insert(root, 3);
        // tree.insert(root, 5);
        // tree.insert(root, 7);
        // tree.insert(root, 12);
        // tree.insert(root, 11);
        // tree.insert(root, 30);
        // tree.insert(root, 15);
        // tree.insert(root, 40);
        // tree.insert(root, 14);
        // tree.insert(root, 16);
        // tree.insert(root, 29);
        // tree.insert(root, 41);
        //System.out.println("Zephyr: " + tree.getFather(root, 10).getValue());

        //tree.calculateTreeDepth(root);
        // System.out.println(tree.toString(root));
        // System.out.println("Removing 10..." + tree.remove(root, 10));
        // System.out.println(tree.toString(root));

        Integer[] elements = new Integer[]{15, 14, 16, 13, 17, 12, 18, 11, 19, 10, 20, 1, 2, 100, 80, 90};
        BinaryTree<Integer> tree2 = new BinaryTree<>();
        Node<Integer> root2 = tree2.createTree(elements);
        //System.out.println(tree2.toString(root2));

        System.out.println(tree2.calculateNodeLevel(root2, 12));
        System.err.println("Aba: " + tree2.inorderTraversal(root2));
        // Node<Integer> node = tree.getByElement(root, 5).getRight();
        // node = null;
    }
}