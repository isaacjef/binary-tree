package ifgoiano;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

      assertEquals(Integer.valueOf(10), rootNode.getValue());
      assertEquals(Integer.valueOf(5), rootNode.getLeft().getValue());
      assertEquals(Integer.valueOf(15), rootNode.getRight().getValue());
    }

    @Test
    @DisplayName("Testa a inserção de um array de elementos")
    public void insertArrayOfElements() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(new Integer[]{5, 0, 10, -5, 15, -10, 20});

      // metodo getNode
      assertEquals(Integer.valueOf(5), tree.getByElement(rootNode, 5).getValue());
      assertEquals(Integer.valueOf(0), tree.getByElement(rootNode, 0).getValue());
      assertEquals(Integer.valueOf(10), tree.getByElement(rootNode, 10).getValue());
      assertEquals(Integer.valueOf(-5), tree.getByElement(rootNode, -5).getValue());
      assertEquals(Integer.valueOf(15), tree.getByElement(rootNode, 15).getValue());
      assertEquals(Integer.valueOf(-10), tree.getByElement(rootNode, -10).getValue());
      assertEquals(Integer.valueOf(20), tree.getByElement(rootNode, 20).getValue());
    }

    // excluir
    @Test
    @DisplayName("Testa a inserção de um elemento e um array de elementos")
    public void insertElementAndArray() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      //talvez nem seja necessário inserir um elemento depois de criar a arvore
      tree.insert(rootNode, 5);
    }

    @Test
    @DisplayName("Testa a inserção de um elemento duplicado")
    public void insertDuplicateElement() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);
      //talvez nem seja necessário inserir um elemento depois de criar a arvore
      tree.insert(rootNode, 5);
      tree.insert(rootNode, 5);
    }
  }

  /**
   * Todos os métodos que retornam Node<T>, retornam null caso o 
   * elemento não exista na árvore, para evitar NullPointerException.
   */
  @Nested
  public class GetElements { 
  
    @Test
    @DisplayName("Testa a obtenção do pai do nóraiz")
    public void getRootFather() {
      BinaryTree<Integer> tree = new BinaryTree<>();
      Node<Integer> rootNode = tree.createTree(10);

      assertEquals(Integer.valueOf(10), tree.getFather(rootNode, 10).getValue());
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
}
