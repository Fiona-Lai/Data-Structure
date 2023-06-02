// CS 0445 Spring 2023
// Public Node<T> class for a doubly-linked list
// Compare this with the author's public Node<T> class from earlier
// in the term.

public class Node<T>
{
     private T data;
     private Node<T> next;
     private Node<T> prev;

     public Node(T dataPortion)
     {
          this(dataPortion, null, null);
     } // end constructor

     public Node(T dataPortion, Node<T> nextNode, Node<T> prevNode)
     {
          data = dataPortion;
          next = nextNode;
          prev = prevNode;
     } // end constructor

     public T getData()
     {
          return data;
     } // end getData

     public void setData(T newData)
     {
          data = newData;
     } // end setData

     public Node<T> getNextNode()
     {
          return next;
     } // end getNextNode

     public void setNextNode(Node<T> nextNode)
     {
          next = nextNode;
     } // end setNextNode
     
     public Node<T> getPrevNode()
     {
     	return prev;
     }
     
     public void setPrevNode(Node<T> prevNode)
     {
     	prev = prevNode;
     }
} // end Node