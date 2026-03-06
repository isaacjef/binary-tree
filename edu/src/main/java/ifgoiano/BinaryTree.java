package ifgoiano;

//public class BinaryTree implements IBinaryTree {

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
  //T extends Object

  /** 
   * Nó raiz como atributo da classe BinaryTree, para evitar a criação 
   * de outro nó raiz a cada chamada do método createTree, para o mesmo objeto.
   * + Para utilizá-lo em outros métodos
   */
  Node<T> root = new Node<>();
  StringBuilder sb = new StringBuilder();

  @Override
  public Node<T> createTree(T element) {
    //Node<T> root = new Node<>();

    if (root.getValue() == null) {
      root.setValue(element);
    }

    return root;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    // Iterator<T> test = new Iterator<BinaryTree.T>() {   
    // };

    for (T element : elements) {
      if (root == null) {
        root.setValue(element);
      } else {
        insert(root, element);
      }
    }

    return root;
  }

  // public Integer degree(Node<T> rootNode, T nodeElement) {
  //   return null;
  // }

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

  public boolean remove(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    if (rootNode == null) {
      return null;
    }

    Node<T> father = rootNode;

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

    return father;
  }

  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    return null;
  }

  public Node<T> getByElement(Node<T> rootNode, T element) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    if (rootNode == null) {
      return 0;
    } else {
      // int leftDepth = calculateTreeDepth(rootNode.getLeft());
      // System.out.println("Left depth of node " + rootNode.getValue() + ": " + leftDepth);
      int rightDepth = calculateTreeDepth(rootNode.getRight());
      System.out.println("Right depth of node " + rootNode.getRight() + ": " + rightDepth);
      // return Math.max(leftDepth, rightDepth) + 1;
      return rightDepth + 1;
    }
    //return null;
  }

  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString(Node<T> rootNode) {
    if (rootNode == null) {
      return "";
    } else if (rootNode.equals(root)) {
      sb.append("root:").append(rootNode.getValue()).append(" ");
    }

    if (rootNode.getLeft() != null) {
      sb.append("(left:").append(rootNode.getLeft().getValue()).append(" ");
      toString(rootNode.getLeft());
      sb.append(")");
    }
   
    if (rootNode.getRight() != null && rootNode.getLeft() == null) {
      sb.append("(right:").append(rootNode.getRight().getValue()).append(" ");
      toString(rootNode.getRight());
      sb.append(")");
    }

     if (rootNode.getRight() != null) {
      sb.append("right:").append(rootNode.getRight().getValue()).append(" ");
      toString(rootNode.getRight());
      sb.append(")");
    }

    return sb.toString();
  }


  @Override
  public Boolean isComplete(Node<T> rootNode) {
    // TODO Auto-generated method stub
    return null;
  }
    
}
