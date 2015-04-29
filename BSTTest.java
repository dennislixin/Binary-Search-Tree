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

    bst.remove(1);
    System.out.println("remove 1");
    System.out.println(bst);

    bst.remove(5);
    System.out.println("remove 5");
    System.out.println(bst);

    bst.remove(6);
    System.out.println("remove 6");
    System.out.println(bst);

    bst.remove(10);
    System.out.println("remove 10");
    System.out.println(bst);

    bst.remove(9);
    System.out.println("remove 9");
    System.out.println(bst);

    bst.remove(3);
    System.out.println("remove 3");
    System.out.println(bst);

    bst.remove(10);
    System.out.println("remove 10");
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

    Node<E> child = head;
    Node<E> cur = null;
    boolean isLeftChildNull = false;

    while(child != null){
      cur = child;
      if(value.compareTo(cur.getValue()) <= 0){
        child = cur.getLeft();
        isLeftChildNull = true;
      } else {
        child = cur.getRight();
        isLeftChildNull = false;
      }
    }

    if(isLeftChildNull){
      cur.setLeft(newNode);
    } else {
      cur.setRight(newNode);
    }

    return true;
  }

  public boolean remove(E value){
    if(value == null || head == null){
      return false;
    }

    Node<E> parent = null;
    Node<E> cur = head;
    boolean isCurLeftChild = false;
    while(cur != null && cur.getValue().compareTo(value) != 0){
      parent = cur;
      if(value.compareTo(cur.getValue()) < 0){
        cur = cur.getLeft();
        isCurLeftChild = true;
      } else {
        cur = cur.getRight();
        isCurLeftChild = false;
      }
    }

    Node<E> left = cur.getLeft();
    Node<E> right = cur.getRight();

    if(left == null || right == null){
      Node<E> newChild;
      if(left != null){
        newChild = left;
      } else{
        newChild = right;
      }
      if(cur == head){
        head = newChild;
      } else {
        if(isCurLeftChild){
          parent.setLeft(newChild);
        } else {
          parent.setRight(newChild);
        }
      }
    } else{
      Node<E> successorOfCur = cur.getRight();
      Node<E> parentOfSuccessor = cur;
      while(successorOfCur.getLeft() != null){
        parentOfSuccessor = successorOfCur;
        successorOfCur = successorOfCur.getLeft();
      }

      cur.setValue(successorOfCur.getValue());

      if(cur == parentOfSuccessor){
        cur.setRight(successorOfCur.getRight());
      } else {
        parentOfSuccessor.setLeft(successorOfCur.getRight());
      }
    }

    return true;
  }

  public String toString(){
    if(head != null){
      return head.toString();
    } else {
      return "BST is empty";
    }

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

  public void setValue(E value){
    this.value = value;
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
