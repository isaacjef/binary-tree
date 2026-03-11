package ifgoiano;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
  //T extends Object

  /** 
   * Nó raiz como atributo da classe BinaryTree, para evitar a criação 
   * de outro nó raiz a cada chamada do método createTree, para o mesmo objeto.
   * + Para utilizá-lo em outros métodos
   */
  Node<T> p_root = new Node<>();

  @Override
  public Node<T> createTree(T element) {
    this.p_root = new Node<>();
    if (element == null)  {
      return null;
    }

    if (p_root.getValue() == null) {
      p_root.setValue(element);
    }

    return p_root;
  }

  @Override
  public Node<T> createTree(T[] elements) {
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
  public Node<T> getRoot() {
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
    if (rootNode.getValue() == null) {
      rootNode.setValue(element);
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
      System.err.println("Elemento já existe na árvore: " + element);
    }
  }

  @Override
  public boolean remove(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null) {
      return false;
    }

    Node<T> father = getFather(p_root, nodeElement);

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
        // Caso 4
        Node<T> resto_esq = rootNode.getLeft();
        Node<T> successor = rootNode.getRight();
        Node<T> link_node = successor.getLeft() != null ? successor.getLeft() : null;

        if (father == null) {
          p_root = successor;
          //se verdadeiro
          if (aux_remove(link_node, resto_esq)) {
            rootNode.setValue(null);
            rootNode.setLeft(null);
            rootNode.setRight(null);
            
            return true;
          }
        } else if (father.getLeft() != null && father.getLeft().getValue().equals(rootNode.getValue())) {
          father.setLeft(rootNode.getRight());
        } else if (father.getRight() != null && father.getRight().getValue().equals(rootNode.getValue())) {
          father.setRight(successor);
          //father.setRight(rootNode.getRight());
        }
        
        rootNode.setValue(null);
        rootNode.setLeft(null);
        rootNode.setRight(null);

        // Se o nó sucessor (filho do nó removido) não estiver nenhum filho à
        // esquerda, ligue o resto à esquerda do nó deletado ao sucessor. 
        // Se não, percorra os nós filhos do nó sucessor, até que encontre o 
        // local apropriado para o resto.
        if (link_node == null) {
          successor.setLeft(resto_esq);
        } else {
          return aux_remove(link_node, resto_esq);
        }

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

      if (aux == 1) {
        resto_esq = null;
      }
    }

    return aux == 1;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    if (rootNode == null || nodeElement == null) {
      return null;
    }

    if (nodeElement.equals(p_root.getValue())) {
      return null;
    }

    if (rootNode.getLeft() != null && rootNode.getLeft().getValue().equals(nodeElement)) {
      return rootNode;
    }
    if (rootNode.getRight() != null && rootNode.getRight().getValue().equals(nodeElement)) {
      return rootNode;
    }

    if (nodeElement.compareTo(rootNode.getValue()) < 0) {
      return getFather(rootNode.getLeft(), nodeElement);
    } else {
      return getFather(rootNode.getRight(), nodeElement);
    }
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    if (rootNode == null || element == null) {
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
   
    int nodeLevel = calculateNodeLevel(getRoot(), rootNode.getValue());
    int treeDepth = calculateTreeDepth(getRoot());
    
    // Verifica se o nó está no último nível e é uma folha.
    if (nodeLevel == treeDepth && (rootNode.getLeft() == null && rootNode.getRight() == null)) {
      return true;
    }

    // Verifica se o nó está no último nível e tem um ou nenhum filho.
    if (nodeLevel == treeDepth - 1 && (rootNode.getLeft() == null || rootNode.getRight() == null)) {
      return true;
    }

    // Verifica se o nó está no penúltimo nível e tem ambos os filhos. *Caso especial*
    if (nodeLevel == treeDepth - 1 && rootNode.getLeft() != null && rootNode.getRight() != null) {
      return true;
    }

    // Percorre todos os nós da árvore recursivamente
    Boolean leftComplete = isComplete(rootNode.getLeft());
    Boolean rightComplete = isComplete(rootNode.getRight());
    
    return leftComplete != null && rightComplete != null && leftComplete && rightComplete;
  }
}
