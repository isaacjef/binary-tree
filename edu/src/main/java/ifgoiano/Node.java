package ifgoiano;

public final class Node<T extends Comparable<T>> {
  
  private T value;
  private Node<T> left;
  private Node<T> right;

  public Node() {
  }

  // public Node(Node<T> node) {
  //   this.setValue(node.getValue());
  //   this.setLeft(node.getLeft());
  //   this.setRight(node.getRight());
  // }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public Node<T> getLeft() {
    return left;
  }

  public Node<T> getRight() {
    return right;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }
}

