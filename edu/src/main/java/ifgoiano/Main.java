package ifgoiano;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Node<Integer> root = tree.createTree(10);
        // tree.insert(root, 15);
        tree.insert(root, 4);
        tree.insert(root, 5);
        tree.insert(root, 3);
        tree.insert(root, 12);
        tree.insert(root, 11);
        tree.insert(root, 13);
        // tree.insert(root, 14);
        // tree.insert(root, 15);
        // root = tree.createTree(11);
        // tree.insert(root, 5);
        System.out.println("Zephyr: " + tree.getFather(root, 10).getValue());
        //tree.calculateTreeDepth(root);
        System.out.println(tree.toString(root));

        // BinaryTree<Integer> tree2 = new BinaryTree<>();
        // Node<Integer> root2 = tree2.createTree(new Integer[]{6, 2, 1, 4, 3, 8});
        // System.out.println(tree2.toString(root2));
        

    }
}