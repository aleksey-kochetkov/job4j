package list;

public class Node<T> {
   T value;
   Node<T> next;

   public Node(T value) {
       this.value = value;
   }
}