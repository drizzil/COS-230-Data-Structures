
public class ElevSim<E> implements PriQue<E> {
	public class Queue {
		private Node head;
		class Node { // Nested protected Node class
			int timeArrive;
			int floor;
			int timeLeave;
			int id;
			boolean boarded;
			boolean reached;
			Node next;
			Node prev;
	
			Node(int id, int timeArrive, int floor, int timeLeave, boolean boarded, boolean reached) {
				super();
				this.id = id;
				this.timeArrive = timeArrive;
				this.floor = floor;
				this.timeLeave = timeLeave;
				this.boarded = boarded;
				this.reached = reached;
				//this.next = null;
			}
			
			public Node getNext() {
				return next;
			}
			public void setNext(Node next) {
				this.next = next;
			}
			public int getArriveTime() {
				return timeArrive;
			}
			public int floor() {
				return floor;
			}
			public int timeLeave() {
				return timeLeave;
			}
			public int getID() {
				return id;
			}
			public boolean getBoarded() {
				return boarded;
			}
			public boolean getReached() {
				return reached;
			}
	
			
		}
	
		public int len() { // find length of list
			int count = 0;
			if (head == null) {
				return count;
			} else {
				Node current = head;
				while (current != null) {
					count++;
					current = current.next;
				}
				return count;
			}
		}
		
		public int getArrival(int pos) { // get time of arrival
			if (head == null) {
				return -1;
			}
			else {
				Node current = head;
				for (int i = 0; i < pos; i++) {
					current = current.next;
				}
				return current.getArriveTime();
			}
		}
		
		public int getFloor(int pos) { // get destination floor
			if (head == null) {
				return 0;
			}
			else {
				Node current = head;
				if (pos == 0) {
					return current.floor();
				}
				for (int i = 0; i < pos; i++) {
					if (current.next == null) {
						
					}
					else {
						current = current.next;
					}
					
				}
				return current.floor();
			}
		}
		
		public int getLeave(int pos) { // get time to spend on destination floor
			if (head == null) {
				return 0;
			}
			else {
				Node current = head;
				for (int i = 0; i < pos; i++) {
					current = current.next;
				}
				return current.timeLeave();
			}
		}
		
		public void addElevator(int id, int d1, int d2, int d3, boolean c1, boolean c2) { // add node to elevator queue
			// Create a new node
			Node start = head;
		    Node temp = new Node(id, d1, d2, d3, c1, c2);

		    if (head == null) {
				head = new Node(id, d1, d2, d3, c1, c2);
			}
		    else if (head.floor > d2) {
		     
		        // Insert New Node before head1
		        temp.next = head;
		        (head) = temp;
		    }
		    else {
		     
		        // Traverse the list and find a
		        // position to insert new node
		        while (start.next != null &&
		            start.next.floor < d2) {
		            start = start.next;
		        }
		     
		        // Either at the ends of the list
		        // or at required position
		        temp.next = start.next;
		        start.next = temp;
		    }
		    
		    if (c2 == true) {
		    	System.out.println("at time " + timer + " person " + id + " got on at floor " + d2 + " destination floor 0");
		    }
		    else {
		    	System.out.println("at time " + timer + " person " + id + " got on at floor 0" + " destination floor " + d2);
		    }	    
		  }
		
		public void addFloor(int id, int d1, int d2, int d3, boolean c1, boolean c2) { // add node to floor queue
			// Create a new node
			Node start = head;
		    Node temp = new Node(id, d1, d2, d3, c1, c2);

		    if (head == null) {
				head = new Node(id, d1, d2, d3, c1, c2);
			}
		    else if (head.timeLeave > d3) {
		     
		        // Insert New Node before head1
		        temp.next = head;
		        (head) = temp;
		    }
		    else {
		     
		        // Traverse the list and find a
		        // position to insert new node
		        while (start.next != null &&
		            start.next.timeLeave < d3) {
		            start = start.next;
		        }
		     
		        // Either at the ends of the list
		        // or at required position
		        temp.next = start.next;
		        start.next = temp;
		    }
		    if (c1 == true) {
		    	System.out.println("at time " + timer + " person " + id + " got off at floor " + d2);
		    }
		    else {
		    	System.out.println("at time " + timer + " person " + id + " got off at floor 0");
		    }
		    
		  }

		public void addStart(int id, int d1, int d2, int d3, boolean c1, boolean c2) { // add node to start queue
			// Create a new node
			Node start = head;
		    Node temp = new Node(id, d1, d2, d3, c1, c2);

		    if (head == null) {
				head = new Node(id, d1, d2, d3, c1, c2);
			}
		    else if (head.timeArrive > d1) {
		     
		        // Insert New Node before head1
		        temp.next = head;
		        (head) = temp;
		    }
		    else {
		     
		        // Traverse the list and find a
		        // position to insert new node
		        while (start.next != null &&
		            start.next.timeArrive < d1) {
		            start = start.next;
		        }
		     
		        // Either at the ends of the list
		        // or at required position
		        temp.next = start.next;
		        start.next = temp;
		    }
		  }
	
		public int pos(int id) { // Method to find a Node's position in list
			int count = 0;
			if (head == null) {
			} else {
				Node current = head;
				while (current != null) {
					if (current.id == id) {
						return count;
					}
					count++;
					current = current.next;
				}
			}
			return count;
		}

		public int getID(int pos) { // Get passenger's ID
			if (head == null) {
				return 0;
			}
			else {
				Node current = head;
				for (int i = 0; i < pos; i++) {
					if (current.next == null) {
						break;
					}
					current = current.next;
				}
				return current.getID();
			}
		}

		public void pop(int pos) { // Remove from queue
			// If linked list is empty
	        if (head == null)
	            return;
	 
	        // Store head node
	        Node temp = head;
	 
	        // If head needs to be removed
	        if (pos == 0) {
	            head = temp.next; // Change head
	            return;
	        }
	 
	        // Find previous node of the node to be deleted
	        for (int i = 0; temp != null && i < pos - 1;
	             i++)
	            temp = temp.next;
	 
	        // If position is more than number of nodes
	        if (temp == null || temp.next == null)
	            return;
	 
	        // Node temp->next is the node to be deleted
	        // Store pointer to the next of node to be deleted
	        Node next = temp.next.next;
	 
	        temp.next
	            = next; // Unlink the deleted node from list
		}
	
		public void printQue() { // Print queue
			
			if (head == null) {
				
			}
			else {
				Node temp = head;
				while (temp.next != null) {
					System.out.println("Queue id: " + temp.id);
					temp = temp.next;
				}
				System.out.println("Queue id: " + temp.id);
			}
		}
		
		void deleteNode(int position) { // Method to delete node from linked list. 
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
	}
	class Node1 {
	    private E data;
	    private int pri;
	    private Node1 next;
	    
	    Node1(E data, int pri) {
			this.pri = pri;
			this.data = data;
			//this.next = null;
		}
	    public Node1 getNext() {
			return next;
		}
		public void setNext(Node1 next) {
			this.next = next;
		}
		public E getData() {
			return data;
		}
		public int getPri() {
			return pri;
		}
	     
	}
	
	private Queue pass1 = new Queue();
	private Queue f1 = new Queue();
	private Queue f2 = new Queue();
	private Queue f3 = new Queue();
	private Queue f4 = new Queue();
	private Queue f5 = new Queue();
	private Queue f6 = new Queue();
	private Queue f7 = new Queue();
	private Queue f8 = new Queue();
	private Queue elevator = new Queue();
	private Queue exit = new Queue();
	private Node1 head1;
	int timer = 0;
	
	public void clicker() {
		timer++;
	}
	
	public void runElevator(int floors) { // Run's the elevator simulation. Iterates up and down the floors, adding passengers to elevator and floors as it goes
		int pass1LenS = pass1.len();
		boolean ifBoarded = false;
		boolean ifUnboarded = false;
		while(exit.len() != pass1LenS) {
			int elevatorLen = elevator.len();
			int f1Len = f1.len();
			int f2Len = f2.len();
			int f3Len = f3.len();
			int f4Len = f4.len();
			int f5Len = f5.len();
			int f6Len = f6.len();
			int f7Len = f7.len();
			int f8Len = f8.len();
			for (int i = 0; i < floors; i++) {
				if (i == 0) {
					int passLen = pass1.len();
					
					for (int ii = 0; ii < passLen; ii++) {
						int passArrival = (pass1.getArrival(pass1.pos(pass1.getID(ii))));
						if (passLen > 0) {
							if (passArrival < timer) {
								if (elevator.len() < 5) {
									if (pass1.getFloor(ii) < 1) {
										
									}
									else {
										ifBoarded = true;
										elevator.addElevator(pass1.getID(pass1.pos(pass1.getID(ii))), pass1.getArrival(pass1.pos(pass1.getID(ii))), pass1.getFloor(pass1.pos(pass1.getID(ii))), pass1.getLeave(pass1.pos(pass1.getID(ii))), true, false);
										pass1.deleteNode(pass1.pos(pass1.getID(ii)));
										ii--;
									}
									
								}
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
						
				}
				if (i == 1) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 1) {
							ifUnboarded = true;
							f1.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f1Len; ii++) {
						if (f1.getLeave(f1.pos(f1.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f1.getID(f1.pos(f1.getID(ii))), f1.getArrival(f1.pos(f1.getID(ii))), f1.getFloor(f1.pos(f1.getID(ii))), f1.getLeave(f1.pos(f1.getID(ii))), true, true);
								f1.deleteNode(f1.pos(f1.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
			

				if (i == 2) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 2) {
							ifUnboarded = true;
							f2.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f2Len; ii++) {
						if (f2.getLeave(f2.pos(f2.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f2.getID(f2.pos(f2.getID(ii))), f2.getArrival(f2.pos(f2.getID(ii))), f2.getFloor(f2.pos(f2.getID(ii))), f2.getLeave(f2.pos(f2.getID(ii))), true, true);
								f2.deleteNode(f2.pos(f2.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}

				if (i == 3) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 3) {
							ifUnboarded = true;
							f3.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f3Len; ii++) {
						if (f3.getLeave(f3.pos(f3.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f3.getID(f3.pos(f3.getID(ii))), f3.getArrival(f3.pos(f3.getID(ii))), f3.getFloor(f3.pos(f3.getID(ii))), f3.getLeave(f3.pos(f3.getID(ii))), true, true);
								f3.deleteNode(f3.pos(f3.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
				if (i == 4) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 4) {
							ifUnboarded = true;
							f4.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f4Len; ii++) {
						if (f4.getLeave(f4.pos(f4.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f4.getID(f4.pos(f4.getID(ii))), f4.getArrival(f4.pos(f4.getID(ii))), f4.getFloor(f4.pos(f4.getID(ii))), f4.getLeave(f4.pos(f4.getID(ii))), true, true);
								f4.deleteNode(f4.pos(f4.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
				if (i == 5) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 5) {
							ifUnboarded = true;
							f5.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f5Len; ii++) {
						if (f5.getLeave(f5.pos(f5.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f5.getID(f5.pos(f5.getID(ii))), f5.getArrival(f5.pos(f5.getID(ii))), f5.getFloor(f5.pos(f5.getID(ii))), f5.getLeave(f5.pos(f5.getID(ii))), true, true);
								f5.deleteNode(f5.pos(f5.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}

				}
				if (i == 6) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 6) {
							ifUnboarded = true;
							f6.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f6Len; ii++) {
						if (f6.getLeave(f6.pos(f6.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f6.getID(f6.pos(f6.getID(ii))), f6.getArrival(f6.pos(f6.getID(ii))), f6.getFloor(f6.pos(f6.getID(ii))), f6.getLeave(f6.pos(f6.getID(ii))), true, true);
								f6.deleteNode(f6.pos(f6.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
				if (i == 7) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 7) {
							ifUnboarded = true;
							f7.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f7Len; ii++) {
						if (f7.getLeave(f7.pos(f7.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f7.getID(f7.pos(f7.getID(ii))), f7.getArrival(f7.pos(f7.getID(ii))), f7.getFloor(f7.pos(f7.getID(ii))), f7.getLeave(f7.pos(f7.getID(ii))), true, true);
								f7.deleteNode(f7.pos(f7.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
				if (i == 8) {
					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						if (elevator.getFloor(elevator.pos(elevator.getID(ii))) == 8) {
							ifUnboarded = true;
							f8.addFloor(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), (elevator.getLeave(elevator.pos(elevator.getID(ii))) + timer), true, false);
							elevator.deleteNode(elevator.pos(elevator.getID(ii)));
						}
					}
					for (int ii = 0; ii < f8Len; ii++) {
						if (f8.getLeave(f8.pos(f8.getID(ii))) <= timer) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f8.getID(f8.pos(f8.getID(ii))), f8.getArrival(f8.pos(f8.getID(ii))), f8.getFloor(f8.pos(f8.getID(ii))), f8.getLeave(f8.pos(f8.getID(ii))), true, true);
								f8.deleteNode(f8.pos(f8.getID(ii)));
							}
						}
					}
					if (ifUnboarded == true || ifBoarded == true) {
						clicker();
						ifUnboarded = false;
						ifBoarded = false;
					}
				}
				if (i != 8) {
					clicker();
				}
				
				
			}
			elevatorLen = elevator.len();
			f1Len = f1.len();
			f2Len = f2.len();
			f3Len = f3.len();
			f4Len = f4.len();
			f5Len = f5.len();
			f6Len = f6.len();
			f7Len = f7.len();
			f8Len = f8.len();
			
			for (int j = floors; j > -1; j--) {
				if (j == 8) {
					for (int ii = 0; ii < f8Len; ii++) {
						if (timer >= f8.getLeave(f8.pos(f8.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f8.getID(f8.pos(f8.getID(ii))), f8.getArrival(f8.pos(f8.getID(ii))), f8.getFloor(f8.pos(f8.getID(ii))), f8.getLeave(f8.pos(f8.getID(ii))), true, true);
								f8.deleteNode(f8.pos(f8.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 7) {
					for (int ii = 0; ii < f7Len; ii++) {
						if (timer >= f7.getLeave(f7.pos(f7.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f7.getID(f7.pos(f7.getID(ii))), f7.getArrival(f7.pos(f7.getID(ii))), f7.getFloor(f7.pos(f7.getID(ii))), f7.getLeave(f7.pos(f7.getID(ii))), true, true);
								f7.deleteNode(f7.pos(f7.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 6) {
					for (int ii = 0; ii < f6Len; ii++) {
						if (timer >= f6.getLeave(f6.pos(f6.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f6.getID(f6.pos(f6.getID(ii))), f6.getArrival(f6.pos(f6.getID(ii))), f6.getFloor(f6.pos(f6.getID(ii))), f6.getLeave(f6.pos(f6.getID(ii))), true, true);

								f6.deleteNode(f6.pos(f6.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 5) {
					for (int ii = 0; ii < f5Len; ii++) {
						if (timer >= f5.getLeave(f5.pos(f5.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f5.getID(f5.pos(f5.getID(ii))), f5.getArrival(f5.pos(f5.getID(ii))), f5.getFloor(f5.pos(f5.getID(ii))), f5.getLeave(f5.pos(f5.getID(ii))), true, true);
								f5.deleteNode(f5.pos(f5.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 4) {
					for (int ii = 0; ii < f4Len; ii++) {
						if (timer >= f4.getLeave(f4.pos(f4.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f4.getID(f4.pos(f4.getID(ii))), f4.getArrival(f4.pos(f4.getID(ii))), f4.getFloor(f4.pos(f4.getID(ii))), f4.getLeave(f4.pos(f4.getID(ii))), true, true);
								f4.deleteNode(f4.pos(f4.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 3) {
					for (int ii = 0; ii < f3Len; ii++) {
						if (timer >= f3.getLeave(f3.pos(f3.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f3.getID(f3.pos(f3.getID(ii))), f3.getArrival(f3.pos(f3.getID(ii))), f3.getFloor(f3.pos(f3.getID(ii))), f3.getLeave(f3.pos(f3.getID(ii))), true, true);
								f3.deleteNode(f3.pos(f3.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 2) {
					for (int ii = 0; ii < f2Len; ii++) {
						if (timer >= f2.getLeave(f2.pos(f2.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f2.getID(f2.pos(f2.getID(ii))), f2.getArrival(f2.pos(f2.getID(ii))), f2.getFloor(f2.pos(f2.getID(ii))), f2.getLeave(f2.pos(f2.getID(ii))), true, true);
								f2.deleteNode(f2.pos(f2.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				if (j == 1) {
					for (int ii = 0; ii < f1Len; ii++) {
						if (timer >= f1.getLeave(f1.pos(f1.getID(ii)))) {
							if (elevator.len() < 5) {
								ifBoarded = true;
								elevator.addElevator(f1.getID(f1.pos(f1.getID(ii))), f1.getArrival(f1.pos(f1.getID(ii))), f1.getFloor(f1.pos(f1.getID(ii))), f1.getLeave(f1.pos(f1.getID(ii))), true, true);
								f1.deleteNode(f1.pos(f1.getID(ii)));
							}
						}
					}
					if (ifBoarded == true) {
						clicker();
						ifBoarded = false;
					}
				}
				

				if (j == 0) {

					elevatorLen = elevator.len();
					for (int ii = 0; ii < elevatorLen; ii++) {
						ifUnboarded = true;
						exit.addStart(elevator.getID(elevator.pos(elevator.getID(ii))), elevator.getArrival(elevator.pos(elevator.getID(ii))), elevator.getFloor(elevator.pos(elevator.getID(ii))), elevator.getLeave(elevator.pos(elevator.getID(ii))), true, true);
						System.out.println("at time " + timer + " person " + elevator.getID(elevator.pos(elevator.getID(ii))) + " got off at floor 0");
						elevator.deleteNode(elevator.pos(elevator.getID(ii)));
					}
				}
				if (j != 0) {
					clicker();
				}
			}
		}
	}

	public void insert(int pri, E data) { // priority queue inserts inital data and then adds to start queue
		Node1 start = (head1);
	     
	    // Create new Node
	    Node1 temp = new Node1(data, pri);
	     
	    // Special Case: The head1 of list has lesser
	    // priority than new node. So insert new
	    // node before head1 node and change head1 node.
	    if (head1 == null) {
			head1 = new Node1(data, pri);
		}
	    else if (head1.pri > pri) {
	     
	        // Insert New Node before head1
	        temp.next = head1;
	        (head1) = temp;
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
	    String [] str = ((String) data).split(" ");
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.valueOf(str[i]);
        }
	    pass1.addStart(pri, arr[0], arr[1], arr[2], false, false);
	}

	public E remove() { // remove from priority queue
		Node1 temp = head1;
	    if (temp == null) {
	    	return null;
	    }
	    else {
	    	head1 = head1.next;
	    	return temp.data;
	    }
	    
	}

	public E peek() { // peek priority queue
		if (this.head1 == null) {
			return null;
		}
		else {
			return head1.data;
		}
	}

	
	public boolean isEmpty() { // checks priority que if its empty
		if (this.head1 == null) {
			return true;
		}
		else {
			return false;
		}
	}


}


