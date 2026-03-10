package ifgoiano;

import java.util.ArrayList;
import java.util.List;

//public class BinaryTree implements IBinaryTree {

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
  //T extends Object

  /** 
   * Nó raiz como atributo da classe BinaryTree, para evitar a criação 
   * de outro nó raiz a cada chamada do método createTree, para o mesmo objeto.
   * + Para utilizá-lo em outros métodos
   */
  Node<T> p_root = new Node<>();
  //StringBuilder sb = new StringBuilder();

  @Override
  public Node<T> createTree(T element) {
    //Node<T> root = new Node<>();
    //if element == null)

    if (p_root.getValue() == null) {
      p_root.setValue(element);
    }

    return p_root;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    // if element == null)

    for (T element : elements) {
      if (p_root == null) {
        p_root.setValue(element);
      } else {
        insert(p_root, element);
      }
    }

    return p_root;
  }

  @Override
  public Integer degree(Node<T> rootNode, T nodeElement) {
    //if (rootNode == null || nodeElement == null)
    if (rootNode == null) {
      return null;
    }

    if (rootNode.getValue().equals(nodeElement)) {
      int degree = 0;
      if (rootNode.getLeft() != null) {
        degree++;
      }
      if (rootNode.getRight() != null) {
        degree++;
      }
      return degree;
    } else if (rootNode.getValue().compareTo(nodeElement) > 0) {
      return degree(rootNode.getLeft(), nodeElement);
    } else {
      return degree(rootNode.getRight(), nodeElement);
    }
  }

  @Override
  public void insert(Node<T> rootNode, T element) {
    //if (element == null)
    if (rootNode.getValue() == null) {
      //createTree(element);
      rootNode.setValue(element);
      //Verificar método compareTo
    } else if (rootNode.getValue().compareTo(element) > 0) {
      if (rootNode.getLeft() == null) {
        Node<T> new_node = new Node<>();
        new_node.setValue(element);
        rootNode.setLeft(new_node);
      } else 
        insert(rootNode.getLeft(), element);
    } else if (rootNode.getValue().compareTo(element) < 0) {
      if (rootNode.getRight() == null) {
        Node<T> new_node = new Node<>();
        new_node.setValue(element);
        rootNode.setRight(new_node);
      } else 
        insert(rootNode.getRight(), element);
    } else {
      System.err.println("Element already exists in the tree: " + element);
    }
  }

  @Override
  public boolean remove(Node<T> rootNode, T nodeElement) {
    //if (rootNode == null || nodeElement == null)
    if (rootNode == null) {
      return false;
    }

    // Modificar -> rootNode para p_root
    Node<T> father = getFather(p_root, nodeElement);

    // if (father == null && p_root.getValue().equals(nodeElement)) {
    //   if (rootNode.getLeft() != null && rootNode.getRight() == null) {
    //     p_root = rootNode.getLeft();
    //   } else if (rootNode.getRight() != null) {
    //     p_root = rootNode.getRight();
    //   } else {
    //     p_root.setValue(null);
    //   }
    // }

    /// Caso quando p_root = rootNode
    if (rootNode.getValue().equals(nodeElement)) {
      // Caso 1: Nó sem filhos
      if (rootNode.getLeft() == null && rootNode.getRight() == null) {
        if (father.getLeft() != null && father.getLeft().getValue().equals(rootNode.getValue())) {
          father.setLeft(rootNode.getRight());
        } else if (father.getRight() != null && father.getRight().getValue().equals(rootNode.getValue())) {
          father.setRight(rootNode.getRight());
        }

        rootNode.setValue(null);
        rootNode.setRight(null);
        rootNode.setLeft(null);
        return true;
      } 
      // Caso 2: Filho apenas na direita
      if (rootNode.getLeft() == null) {
        // Criar método que executa a verificação abaixo, para que o código
        // fique mais limpo e legível, evitando a repetição de código.
        if (father.getLeft() != null && father.getLeft().getValue().equals(rootNode.getValue())) {
          father.setLeft(rootNode.getRight());
        } else if (father.getRight() != null && father.getRight().getValue().equals(rootNode.getValue())) {
          father.setRight(rootNode.getRight());
        }

        rootNode.setValue(null);
        rootNode.setRight(null);
        rootNode.setLeft(null);
        return true;
      // Caso 3: Filho apenas na esquerda
      } else if (rootNode.getRight() == null) {
          if (father.getLeft() != null && father.getLeft().getValue().equals(rootNode.getValue())) {
            father.setLeft(rootNode.getLeft());
          } else if (father.getRight() != null && father.getRight().getValue().equals(rootNode.getValue())) {
            father.setRight(rootNode.getLeft());
          }

        rootNode.setValue(null);
        rootNode.setLeft(null);
        rootNode.setRight(null);
        return true;
      } else {
        // Case 3: Node has two children

        Node<T> resto_esq = rootNode.getLeft();
        Node<T> successor = rootNode.getRight();
        Node<T> link_node = successor.getLeft() != null ? successor.getLeft() : null;

        if (father == null) {
          p_root = successor;
          if (aux_remove(link_node, resto_esq)) {
            rootNode.setRight(null);
            rootNode.setLeft(null);
            return true;
          }
        } else if (father.getLeft() != null && father.getLeft().getValue().equals(rootNode.getValue())) {
          father.setLeft(rootNode.getRight());
        } else if (father.getRight() != null && father.getRight().getValue().equals(rootNode.getValue())) {
          father.setRight(successor);
          //father.setRight(rootNode.getRight());
        }

        if (link_node == null) {
          successor.setLeft(resto_esq);
        }
        
        //int aux = 0;

        // while (resto_esq != null && link_node != null) {
        //   if (link_node.getLeft() != null && resto_esq.getValue().compareTo(successor.getValue()) < 0) {
        //     link_node = link_node.getLeft();
        //   }
        //   if (link_node.getRight() != null && resto_esq.getValue().compareTo(successor.getValue()) > 0) {
        //     link_node = link_node.getRight();
        //   }
        //   if(link_node.getLeft() == null && resto_esq.getValue().compareTo(successor.getValue()) < 0) {
        //     link_node.setLeft(resto_esq);
        //     aux = 1;
        //   }
        //   if(link_node.getRight() == null && resto_esq.getValue().compareTo(successor.getValue()) > 0) {
        //     link_node.setRight(resto_esq);
        //     aux = 1;
        //   }
        //   System.out.println("Link node: " + link_node.getValue());
        //   System.out.println("Resto esq: " + resto_esq.getValue());
        //   if (aux == 1) {
        //     resto_esq = null;
        //   }
        // }

        rootNode.setValue(null);
        rootNode.setLeft(null);
        rootNode.setRight(null);
        return true;
      }
    }

    if (rootNode.getLeft() != null && nodeElement.compareTo(rootNode.getValue()) < 0) {
      return remove(rootNode.getLeft(), nodeElement);
    } else if (rootNode.getRight() != null && nodeElement.compareTo(rootNode.getValue()) > 0) {
      return remove(rootNode.getRight(), nodeElement);
    }

    return false;
  }

  public Boolean aux_remove(Node<T> link_node, Node<T> resto_esq) {
    int aux = 0;

    while (resto_esq != null && link_node != null) {
      if (link_node.getLeft() != null && resto_esq.getValue().compareTo(link_node.getValue()) < 0) {
        link_node = link_node.getLeft();
      }
      if (link_node.getRight() != null && resto_esq.getValue().compareTo(link_node.getValue()) > 0) {
        link_node = link_node.getRight();
      }
      if(link_node.getLeft() == null && resto_esq.getValue().compareTo(link_node.getValue()) < 0) {
        link_node.setLeft(resto_esq);
        aux = 1;
      }
      if(link_node.getRight() == null && resto_esq.getValue().compareTo(link_node.getValue()) > 0) {
        link_node.setRight(resto_esq);
        aux = 1;
      }
      System.out.println("Link node: " + link_node.getValue());
      System.out.println("Resto esq: " + resto_esq.getValue());
      if (aux == 1) {
        resto_esq.setLeft(null);
        resto_esq.setRight(null);
        resto_esq = null;
      }
    }

    return aux == 0;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    //if (rootNode == null || nodeElement == null)
    if (rootNode == null) {
      return null;
    }

    if (nodeElement.equals(p_root.getValue())) {
      return null;
    }

    Node<T> father = rootNode;
    // vERIFICAR
    if (rootNode.getLeft() != null && nodeElement.compareTo(rootNode.getValue()) < 0) {
      if (rootNode.getLeft().getValue().equals(nodeElement)) {
        return father;
      }
      father = getFather(rootNode.getLeft(), nodeElement);
    }

    if (rootNode.getRight() != null && nodeElement.compareTo(rootNode.getValue()) > 0) {
      if (rootNode.getRight().getValue().equals(nodeElement)) {
        return father;
      }
      father = getFather(rootNode.getRight(), nodeElement);
    }

    // Falta o caso, quando o nó não é raiz e não possui filhos.
    return father;
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    //if (rootNode == null || nodeElement == null)
    if (rootNode == null) {
      return null;
    }

    if (rootNode.getValue().equals(element)) {
      return rootNode;
    } else if (rootNode.getValue().compareTo(element) > 0) {
      return getByElement(rootNode.getLeft(), element);
    } else {
      return getByElement(rootNode.getRight(), element);
    }
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    if (rootNode == null)
      return null;

    int left;
    int right;

    if (rootNode.getRight() == null && rootNode.getLeft() == null) {
      return 0;
    }

    if (rootNode.getLeft() == null) {
      right = 1 + calculateTreeDepth(rootNode.getRight());
      return right;
    }

    if (rootNode.getRight() == null) {
      left = 1 + calculateTreeDepth(rootNode.getLeft());
      return left;
    }

    if (rootNode.getRight() != null && rootNode.getLeft() != null) {
      left = 1 + calculateTreeDepth(rootNode.getLeft());
      right = 1 + calculateTreeDepth(rootNode.getRight());

      if (left > right) {
        return left;
      } else {
        return right;
      }
    }

    return 1;
  }

  @Override
  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null)
      return null;

    int aux = 0;

    if (rootNode.getRight() == null && rootNode.getLeft() == null) {
      if (rootNode.getValue().equals(nodeElement)) {
        return aux;
      } 
      else {
        return null;
      }
    }

    if (nodeElement.compareTo(rootNode.getValue()) < 0) {
      aux = 1 + calculateNodeLevel(rootNode.getLeft(), nodeElement);
    } else if (nodeElement.compareTo(rootNode.getValue()) > 0) {
      aux = 1 + calculateNodeLevel(rootNode.getRight(), nodeElement);
    }

    if(rootNode.getValue().equals(nodeElement)) {
      return 0;
    }
    return aux;
  }

  @Override
  public String toString(Node<T> rootNode) {
    StringBuilder sb = new StringBuilder();

    if (rootNode == null) {
      return "";
    } else if (rootNode.equals(p_root)) {
      sb.append("root:").append(rootNode.getValue()).append(" ");
    }

    if (rootNode.getLeft() != null && rootNode.getRight() == null) {
      sb.append("(left:").append(rootNode.getLeft().getValue()).append(" ");
      sb.append(toString(rootNode.getLeft()));
      return sb.append(")").toString();
    }

    if (rootNode.getLeft() != null) {
      sb.append("(left:").append(rootNode.getLeft().getValue()).append(" ");
      sb.append(toString(rootNode.getLeft()));
      //return sb.toString();
      //sb.append(")");
    }
   
    if (rootNode.getRight() != null && rootNode.getLeft() == null) {
      sb.append("(right:").append(rootNode.getRight().getValue()).append(" ");
      sb.append(toString(rootNode.getRight()));
      return sb.append(")").toString();
    }

    if (rootNode.getRight() != null) {
      sb.append("right:").append(rootNode.getRight().getValue()).append(" ");
      sb.append(toString(rootNode.getRight()));
      return sb.append(")").toString();
    }

    return sb.toString();
  }

  @Override
  public Boolean isComplete(Node<T> rootNode) {
    if (rootNode == null)
      return null;

    

    return false;
  }

  /**
   * Método para percorrer a árvore binária em ordem inorder, visitando todos os nós.
   * Retorna uma lista com todos os elementos da árvore em ordem crescente.
   * 
   * @param rootNode o nó raiz da árvore
   * @return lista com todos os elementos da árvore
   */
  public List<T> inorderTraversal(Node<T> rootNode) {
    List<T> result = new ArrayList<>();
    inorderHelper(rootNode, result);
    return result;
  }

  private void inorderHelper(Node<T> node, List<T> result) {
    if (node != null) {
      inorderHelper(node.getLeft(), result);
      result.add(node.getValue());
      inorderHelper(node.getRight(), result);
    }
  }
    
}
