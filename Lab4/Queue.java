
public class Queue{
	class Node {
	    public int num;
	    private Node next;
	    
	    Node(int num) {
			this.num = num;
		}
	    public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public int getNum() {
			return num;
		}
	     
	}
	Node head;
		    
		  public int remove() throws QueueEmptyException{
			  Node temp = head;
			    if (temp == null) {
			    	return 0;
			    }
			    else {
			    	head = head.next;
			    	return temp.num;
			    }
		  }
		    
		  public void addToFront(int num) { // Method to add node to front of linked list.
				head = new Node(num);
			}
		  
		  public void add(int num) { // Method to add node to end of linked list.
				if (head == null) {
					addToFront(num);
				} else {
					Node current = null;
					for (current = head; current.next != null; current = current.next) {
					}
					current.next = new Node(num);
				}
			}
		    
		  public boolean isEmpty(){
		    return head == null;
		  }

		  public void printList() { // Method to print each available node of linked list, providing ID and memory for each node.
				System.out.println("List: ");
			  if (head == null) {
					System.out.println("The list is empty.");
				} else {
					Node current = head;
					while (current != null) {
						System.out.println(current.num);
						current = current.next;
					}
					System.out.println();
				}
			}
}