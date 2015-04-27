public class BSTTest{
  public static void main(String[] args){
    BST<Integer> bst = new BST<>();
    bst.insert(10);
    bst.insert(6);
    bst.insert(9);
    bst.insert(1);
    bst.insert(3);
    bst.insert(5);
    bst.insert(10);
    System.out.println(bst);
  }
}

class BST <E extends Comparable<? super E>>{
  Node<E> head;
  public BST(){
    head = null;
  }

  public boolean insert(E value){
    if(value == null){
      return false;
    }
    Node<E> newNode = new Node<E>(value);

    if(head == null){
      head = newNode;
      return true;
    }

    Node<E> next = head;
    Node<E> cur = null;
    boolean isLeft = false;

    while(next != null){
      cur = next;
      if(value.compareTo(cur.getValue()) <= 0){
        next = cur.getLeft();
        isLeft = next == null;
      } else {
        next = cur.getRight();
      }
    }

    if(isLeft){
      cur.setLeft(newNode);
    } else {
      cur.setRight(newNode);
    }

    return true;
  }

  public String toString(){
    return head.toString();
  }
}

class Node <E extends Comparable<? super E>>{
  E value;
  Node<E> left;
  Node<E> right;

  private Node(){}

  public Node(E value, Node<E> left, Node<E> right){
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public Node(E value){
    this(value, null, null);
  }

  public void setLeft(Node<E> newLeft){
    left = newLeft;
  }

  public void setRight(Node<E> newRight){
    right = newRight;
  }

  public E getValue(){
    return value;
  }

  public Node<E> getLeft(){
    return left;
  }

  public Node<E> getRight(){
    return right;
  }

  public String toString(){
    StringBuilder strBuilder = new StringBuilder();
    if(left != null){
      strBuilder.append(left.toString());
    }

    strBuilder.append(value.toString());
    strBuilder.append(", ");

    if(right != null){
      strBuilder.append(right.toString());
    }

    return strBuilder.toString();
  }
}
