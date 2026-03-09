package ifgoiano;

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

    if (p_root.getValue() == null) {
      p_root.setValue(element);
    }

    return p_root;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    // Iterator<T> test = new Iterator<BinaryTree.T>() {   
    // };

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
    if (rootNode == null) {
      return 0;
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
    if (rootNode == null) {
      return false;
    }

    // Modificar -> rootNode para p_root
    Node<T> father = getFather(p_root, nodeElement);

    /// Caso quando p_root = rootNode
    if (rootNode.getValue().equals(nodeElement)) {
      // Case 1: Node has no children
      if (rootNode.getLeft() == null && rootNode.getRight() == null) {
        rootNode.setValue(null);
        //father.setLeft(null);
        father.setRight(null);
        return true;
      } 
      
      // Case 2: Node has one child
      // Filho apenas na direita
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
        Node<T> successor = rootNode.getRight();
        rootNode.setValue(null);
        while (successor.getLeft() != null) {
          successor = successor.getLeft();
        }
        return remove(rootNode.getRight(), successor.getValue());
      }
    }

    if (rootNode.getLeft() != null && nodeElement.compareTo(rootNode.getValue()) < 0) {
      return remove(rootNode.getLeft(), nodeElement);
    } else if (rootNode.getRight() != null && nodeElement.compareTo(rootNode.getValue()) > 0) {
      return remove(rootNode.getRight(), nodeElement);
    }

    return false;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
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
      return 0;

    int left = 0;
    int right = 0;

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
    // TODO Auto-generated method stub
    return null;
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
    // TODO Auto-generated method stub
    return null;
  }
    
}
