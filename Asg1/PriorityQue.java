
public class PriorityQue<E> implements PriQue<E>{
	
	class Node {
	    private E data;
	    private int pri;
	    private Node next;
	    
	    Node(E data, int pri) {
			this.pri = pri;
			this.data = data;
			//this.next = null;
		}
	    public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public E getData() {
			return data;
		}
		public int getPri() {
			return pri;
		}
	     
	}
	private Node head;
	//private Node tail;

	public void insert(int pri, E data) {
		Node start = (head);
	     
	    // Create new Node
	    Node temp = new Node(data, pri);
	     
	    // Special Case: The head of list has lesser
	    // priority than new node. So insert new
	    // node before head node and change head node.
	    if (head == null) {
			head = new Node(data, pri);
		}
	    else if (head.pri > pri) {
	     
	        // Insert New Node before head
	        temp.next = head;
	        (head) = temp;
	    }
	    else {
	     
	        // Traverse the list and find a
	        // position to insert new node
	        while (start.next != null &&
	            start.next.pri < pri) {
	            start = start.next;
	        }
	     
	        // Either at the ends of the list
	        // or at required position
	        temp.next = start.next;
	        start.next = temp;
	    }
	}

	public E remove() {
		Node temp = head;
	    if (temp == null) {
	    	return null;
	    }
	    else {
	    	head = head.next;
	    	return temp.data;
	    }
	    
	}

	public E peek() {
		if (this.head == null) {
			return null;
		}
		else {
			return head.data;
		}
	}

	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		}
		else {
			return false;
		}
	}

}
