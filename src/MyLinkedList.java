/**
 * Created by Ramtin on 2017-06-15.
 */
public class MyLinkedList<T> {

    Node head;

    public MyLinkedList(){
        head = null;
    }

    public void add(Object value){
        Node newNode = new Node(value, null);
        if(head == null){
          head = newNode;
        }
        else {
            newNode.next = head; // new node points to the current head node
            head = newNode; // and now the newnode is the head node, so we add from the head.
        }
    }

    public void delete(T value){
        boolean found = false;
        Node n = head;
        while(n.value != value){
            if(n.value == value){

            }
            n = n.next;
        }
    }

    public int getSize(){
        int size = 0;
        Node n = head;
        while(n.next != null){
            n = n.next;
            size++;
        }
        return size;
    }

    public void display(){
        Node n = head;
        while(n != null){
            System.out.println((T)n.value);
            n = n.next;
        }
    }
}
