public class CycleLinkedList<T> {

  int size = 0;

  Node<T> first;
  Node<T> last;

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void add(T element) {
    linkLast(element);
  }

  public void add(int index, T element) {
    checkIndex(index);

    if (index == size) {
      linkLast(element);
    } else {
      linkBefore(element, node(index));
    }
  }

  public void clear() {
    first = last = null;
    size = 0;
  }

  public T get(int index) {
    return node(index).element;
  }

  private Node<T> node(int index) {
    if (index < (size >> 1)) {
      Node<T> x = first;
      for (int i = 0; i < index; i++) {
        x = x.next;
      }
      return x;
    }
    Node<T> x = last;
    for (int i = size - 1; i > index; i--) {
      x = x.prev;
    }
    return x;
  }

  private void linkLast(T e) {
    final Node<T> l = last;
    final Node<T> newNode = new Node<>(l, e, first);
    last = newNode;
    if (l == null) {
      first = newNode;
      newNode.next = newNode;
    } else {
      l.next = newNode;
    }
    if (first != null) {
      first.prev = newNode;
    }
    size++;
  }

  private void linkBefore(T e, Node<T> succ) {
    final Node<T> pred = succ.prev;
    final Node<T> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;
    if (pred == null) {
      first = newNode;
    } else {
      pred.next = newNode;
    }
    size++;
  }

  private void checkIndex(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  private static class Node<T> {
    T element;
    Node<T> next;
    Node<T> prev;

    Node(Node<T> prev, T element, Node<T> next) {
      this.prev = prev;
      this.element = element;
      this.next = next;
    }
  }

  public static void main(String[] args) {
    CycleLinkedList<Integer> list = new CycleLinkedList<>();
    System.out.println(list.isEmpty());
    System.out.println(list.size());
    list.add(0);
    list.add(2);
    list.add(3);
    System.out.println(list.get(2));
    list.add(1, 1);
    System.out.println(list.get(2));
    System.out.println(list.isEmpty());
    System.out.println(list.size());
    list.clear();
    System.out.println(list.size());
  }
}