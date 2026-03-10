package ifgoiano;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
  
  @Nested
  public class Insert {
    @Test
    @DisplayName("Testa a criação de uma árvore ")
    public void insertAnElement() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      tree.insert(rootNode, 5);
      tree.insert(rootNode, 15);

      assertEquals(tree.toString(rootNode), "root:10 (left:5 right:15 )");
    }

    @Test
    @DisplayName("Inserção de um array de elementos")
    public void insertArrayOfElements() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{5, 0, 10, -5, 15, -10, 20});

      assertEquals(tree.toString(rootNode), "root:5 (left:0 (left:-5 (left:-10 ))right:10 (right:15 (right:20 )))");
      // assertEquals(Integer.valueOf(0), tree.getByElement(rootNode, 0).getValue());
      // assertEquals(Integer.valueOf(10), tree.getByElement(rootNode, 10).getValue());
      // assertEquals(Integer.valueOf(-5), tree.getByElement(rootNode, -5).getValue());
      // assertEquals(Integer.valueOf(15), tree.getByElement(rootNode, 15).getValue());
      // assertEquals(Integer.valueOf(-10), tree.getByElement(rootNode, -10).getValue());
      // assertEquals(Integer.valueOf(20), tree.getByElement(rootNode, 20).getValue());
    }

    // excluir
    @Test
    @DisplayName("Inserção de um elemento e um array de elementos")
    public void insertElementAndArray() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      //talvez nem seja necessário inserir um elemento depois de criar a arvore
      tree.insert(rootNode, 5);
    }

    @Test
    @DisplayName("Inserção de um elemento duplicado")
    public void insertDuplicateElement() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      //talvez nem seja necessário inserir um elemento depois de criar a arvore
      tree.insert(rootNode, 5);
      tree.insert(rootNode, 5);
    }

    @Test
    @DisplayName("Testa a inserção de um elemento duplicado")
    public void elementAlreadyExists() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      tree.insert(rootNode, 5);
      tree.insert(rootNode, 15);
      tree.insert(rootNode, 5); // Tenta inserir um elemento duplicado

      assertEquals(Integer.valueOf(10), rootNode.getValue());
      assertEquals(Integer.valueOf(5), rootNode.getLeft().getValue());
      assertEquals(Integer.valueOf(15), rootNode.getRight().getValue());
    }

    @Test
    @DisplayName("Inserção de elementos somente à esquerda")
    public void insertOnlyLeft() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{6, 5, 4, 4, 3, 2, 1});
      //talvez nem seja necessário inserir um elemento depois de criar a árvore

      assertEquals(tree.toString(rootNode), "root:6 (left:5 (left:4 (left:3 (left:2 (left:1 )))))");
    }

    @Test
    @DisplayName("Inserção de elementos somente à direita")
    public void insertOnlyRight() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{1, 2, 3, 4, 5, 6});
      //talvez nem seja necessário inserir um elemento depois de criar a árvore

      assertEquals(tree.toString(rootNode), "root:1 (right:2 (right:3 (right:4 (right:5 (right:6 )))))");
    }

    @Test
    @DisplayName("Inserção de uma sequência de elementos")
    void performASetOfInsertions() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Node<Integer> rootNode = binaryTreeOps.createTree(6);

      assertEquals(binaryTreeOps.toString(rootNode), "root:6 ");

      binaryTreeOps.insert(rootNode, 2);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 )");

      binaryTreeOps.insert(rootNode, 8);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 right:8 )");

      binaryTreeOps.insert(rootNode, 1);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 )right:8 )");

      binaryTreeOps.insert(rootNode, 1);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 )right:8 )");

      binaryTreeOps.insert(rootNode, 4);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 right:4 )right:8 )");

      binaryTreeOps.insert(rootNode, 9);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 right:4 )right:8 (right:9 ))");

      binaryTreeOps.insert(rootNode, 3);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 right:4 (left:3 ))right:8 (right:9 ))");

      binaryTreeOps.insert(rootNode, 3);
      assertEquals(binaryTreeOps.toString(rootNode), "root:6 (left:2 (left:1 right:4 (left:3 ))right:8 (right:9 ))");
    }
  }

  /**
   * Todos os métodos que retornam Node<T>, retornam null caso o 
   * elemento não exista na árvore, para evitar NullPointerException.
   */
  @Nested
  public class GetElements { 
  
    @Test
    @DisplayName("Testa a obtenção do pai da raiz")
    public void getRootFather() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);

      assertNull(tree.getFather(rootNode, 10));
    }

    @Test
    @DisplayName("Testa a obtenção do pai de um nó")
    public void getFather() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      tree.insert(rootNode, 5);
      tree.insert(rootNode, 15);

      assertEquals(Integer.valueOf(10), tree.getFather(rootNode, 5).getValue());
      assertEquals(Integer.valueOf(10), tree.getFather(rootNode, 15).getValue());
    }

    @Test
    void getFatherArray() {
      IBinaryTree<Integer> binaryTree = new BinaryTree<>();
      Integer[] elements = new Integer[] { 6, 2, 8, 1, 4, 3 };

      Node<Integer> rootNode = binaryTree.createTree(elements);

      assertEquals(binaryTree.getFather(rootNode, 8).getValue(), 6);
      assertEquals(binaryTree.getFather(rootNode, 1).getValue(), 2);
      assertEquals(binaryTree.getFather(rootNode, 3).getValue(), 4);
      assertNull(binaryTree.getFather(rootNode, 6));
    }

    @Test
    @DisplayName("Testa a obtenção do pai de uma árvore somente à direita")
    public void onlyRight() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{10, 15, 25, 16});

      assertEquals(Integer.valueOf(10), tree.getFather(rootNode, 15).getValue());
      assertEquals(Integer.valueOf(15), tree.getFather(rootNode, 25).getValue());
      assertEquals(Integer.valueOf(25), tree.getFather(rootNode, 16).getValue());
    }
  }

  @Nested
  public class Remove {
    @Test
    @DisplayName("Remoção de uma folha À esquerda")
    public void removeLeftLeaf() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{10, 15, 25, 16});

      assertEquals(tree.toString(rootNode), "root:10 (right:15 (right:25 (left:16 )))");
      tree.remove(rootNode, 16);
      assertEquals(tree.toString(rootNode), "root:10 (right:15 (right:25 ))");
    }

    @Test
    @DisplayName("Remoção de uma folha À direita")
    public void removeRightLeaf() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{10, 15, 25, 16, 17});

      assertEquals(tree.toString(rootNode), "root:10 (right:15 (right:25 (left:16 (right:17 ))))");
      //tree.remove(rootNode, 25);
      tree.remove(rootNode, 16);
      //tree.remove(rootNode, 17);
      assertEquals(tree.toString(rootNode), "root:10 (right:15 (right:25 (left:17 )))");
    }
  }

  @Nested
  public class DepthAndCompleteness {
    @Test
    void calculateTreeRoot() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] { 6};

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);

      assertEquals(binaryTreeOps.calculateTreeDepth(rootNode), 0);
    }

    @Test
    void calculateTreeDepthsRight() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] { 6, 2, 8, 1, 4, 3, 5, 9, 10, 11, 12};

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);

      assertEquals(binaryTreeOps.calculateTreeDepth(rootNode), 5);
    }

    @Test
    void calculateTreeDepthsLeft() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);

      assertEquals(binaryTreeOps.calculateTreeDepth(rootNode), 10);
    }

    @Test
    void calculateTreeNull() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] {};

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);
      assertNull(binaryTreeOps.calculateTreeDepth(rootNode));
    }

    @Test
    void calculateTreeDepths() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[]{15, 14, 16, 13, 17, 12, 18, 11, 19, 10, 20, 1, 2, 100, 80, 90};

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);
      assertEquals(binaryTreeOps.calculateTreeDepth(rootNode), 8);
    }
  }

  @Nested
  public class Others {
    @Test
    void checkNodeDegree() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] { 6, 2, 8, 1, 4, 3 };

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);

      assertEquals(binaryTreeOps.degree(rootNode, 8), 0);
      assertEquals(binaryTreeOps.degree(rootNode, 2), 2);
      assertEquals(binaryTreeOps.degree(rootNode, 4), 1);
      assertNull(binaryTreeOps.degree(rootNode, 10));
    }

    @Test
    void calculateNodeLevel() {
      IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
      Integer[] elements = new Integer[] { 6, 2, 8, 1, 4, 3 };

      Node<Integer> rootNode = binaryTreeOps.createTree(elements);

      assertEquals(binaryTreeOps.calculateNodeLevel(rootNode, 8), 1);
      assertEquals(binaryTreeOps.calculateNodeLevel(rootNode, 4), 2);
      assertNull(binaryTreeOps.calculateNodeLevel(rootNode, null));
    }
  }
}
