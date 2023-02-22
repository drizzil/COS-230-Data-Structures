public class MemMgr implements MMInterface{
	
	public class LinkedList {
		protected class Node {
			private int id;
			private int memory;
			private Node next;

			Node(int number1, int number2, Node nextNode) {
				id = number1;
				memory = number2;
				next = nextNode;
			}
		}
		
		private Node head;
		
		public LinkedList() {
			head = null;
		}

		public void addToFront(int n1, int n2) {
			head = new Node(n1, n2, head);
		}

		public void addToEnd(int n1, int n2) {
			if (head == null) {
				addToFront(n1, n2);
			} else {
				Node current = null;
				for (current = head; current.next != null; current = current.next) {
				}
				current.next = new Node(n1, n2, null);
			}
		}
		
		public void printList() {
			if (head == null) {
				System.out.println("The list is empty.");
			} else {
				Node current = head;
				while (current != null) {
					if (current.memory == 0) {
						System.out.println("Free: 			Id: " + current.id + "         Memory: " + current.memory);
					}
					else {
						System.out.println("Allocated: 		Id: " + current.id + "         Memory: " + current.memory);
					}
					current = current.next;
				}
				System.out.println();
			}
		}
		
		void deleteNode(int position)
	    {
	        if (head == null)
	            return;
	 
	        Node temp = head;
	 
	        if (position == 0) {
	            head = temp.next; 
	            return;
	        }
	 
	        for (int i = 0; temp != null && i < position - 1;
	             i++)
	            temp = temp.next;

	        if (temp == null || temp.next == null)
	            return;

	        Node next = temp.next.next;
	 
	        temp.next
	            = next; 
	    }
		
		public int combo2() {
			int pos = 0;
			int kPos = 0;
			if (head != null) {
				Node current = head;
				while (current.next != null) {
					pos++;
					if (current.memory == 0) {
						if (current.next.memory == 0) {
							kPos = pos;
						}
					}
					current = current.next;
				}
				
			}
			return kPos;
		}

		public int nuClearMemory(int num1) {
			int mem = 0;
			if (head.id == num1) {
				mem = head.memory;
				head.memory = 0;
			}
			else {
				Node current = head;
				while (current != null) {
					if (current.id == num1) {
						mem = current.memory;
						current.memory = 0;
						break;
					}
					current = current.next;
				}
			}
			return mem;
				
		}
	
		public boolean contains(int num1) {
			boolean cont = false;
			if (head.id == num1) {
				cont = true;
			}
			else {
				Node current = head;
				while (current != null) {
					if (current.id == num1) {
						cont = true;
						break;
					}
					current = current.next;
				}
			}
			return cont;
		}
		
		public void combo() {
			if (head != null) {
				Node current = head;
				while (current.next != null) {
					if (current.memory == 0) {
						if (current.next.memory == 0) {
							if (current.next.next != null) {
								//System.out.println(current.next.id);
								//System.out.println(current.next.next.id);
								current.next = current.next.next;
								//System.out.println(current.next.id);
							}
							current.next = null;
							break;
						}
					}
					current = current.next;
				}
			}
		}
	}
	
	private LinkedList ll = new LinkedList();;
	private int memSize;
	private int initSize;
	private int altCounter;
	
	public void init(int size) {
		memSize = size;
		initSize = size;
		altCounter = 0;
		
	}

	public void malloc(int id, int size) throws MyOutOfMemoryException {
		if (size > altCounter) {
			if (size <= memSize) {
				ll.addToEnd(id, size);
				memSize = memSize - size;
			}
			else {
				throw new MyOutOfMemoryException();
			}
		}
		else {
			throw new MyOutOfMemoryException();
		}
	}

	public void free(int id) throws MyInvalidMemoryException {
		if (ll.contains(id) != true) {
			throw new MyInvalidMemoryException();
		}
		else {
			if (ll.combo2() > 0) {
				ll.deleteNode(ll.combo2());
				memSize = memSize + ll.nuClearMemory(id);
			}
			else {
				memSize = memSize + ll.nuClearMemory(id);
			}
			altCounter++;
		}
		if (ll.combo2() > 0) {
			altCounter--;
			ll.deleteNode(ll.combo2());
			memSize = memSize + ll.nuClearMemory(id);
		}
		if (ll.combo2() > 0) {
			altCounter--;
			ll.deleteNode(ll.combo2());
			memSize = memSize + ll.nuClearMemory(id);
		}
	}

	public void print() {
		System.out.println("Memory left: " + (memSize - 1)+ "/" + (initSize - 1));
		ll.printList();
	}

}