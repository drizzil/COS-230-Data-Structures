import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class LinkedList {
	public class Node {
		private String num1; // data stored in linked list
		private Node next; // self-referencing pointer

		Node(String number1, Node nextNode) { // Constructor
			num1 = number1;
			next = nextNode;
		}
	}

	Node head; // pointer to the first node in the linked list.
	private Node sorted;

	public LinkedList() { // Constructor for linked list
		head = null;
	}
	
	public void addToFront(String n1) { // Add new node to front of list.
		head = new Node(n1, head);
	}

	public void addToEnd(String n1) { // Add new node to end of list.
		if (head == null) {
			addToFront(n1);
		} else {
			Node current = null;
			// could use while loop, but not going to
			for (current = head; current.next != null; current = current.next) {
				// do nothing; just get me to the end
			}
			current.next = new Node(n1, null);
		}
	}
	
	public void insertionSort(Node headref) {
		// Initialize sorted linked list
		sorted = null;
		Node current = headref;
		// Traverse the given linked list and insert every
		// node to sorted
		while (current != null) {
			// Store next for next iteration
			Node next = current.next;
			// insert current in sorted linked list
			sortedInsert(current);
			// Update current
			current = next;
		}
		// Update head_ref to point to sorted linked list
		head = sorted;
	}
	public void sortedInsert(Node newnode) {
		/* Special case for the head end */
		if (sorted == null || sorted.num1.compareTo(newnode.num1) <= 0) {
			newnode.next = sorted;
			sorted = newnode;
		} else {
			Node current = sorted;
			/* Locate the node before the point of insertion */
			while (current.next != null && current.next.num1.compareTo(newnode.num1) > 0) {
				current = current.next;
			}
			newnode.next = current.next;
			current.next = newnode;
		}
	}

	public int len() {
		int i = 0;
		Node current = head;
		while (current != null) {
			i++;
			current = current.next;
		}
		return i;
	}
	
	
	public String printList(int i) {
		String word = "";
		Node current = head;
		for (int j = 0; j < i; j++) {
			current = current.next;
		}
		word = current.num1;
		return word;
	}
}

public class WordCounting {
	public static void main(String args[]) throws IOException{
		if (args.length != 1) {
			System.err.println("Error: Please enter the correct amount of files.");
		}
		else {
			try {
				LinkedList ll2 = new LinkedList();
				int length = 5000;
				HashTable<String, Integer> hash1 = new HashTable<String, Integer>(length);
				
				FileReader freader1 = new FileReader(args[0]);
				BufferedReader inputFile1 = new BufferedReader(freader1);
		
				String line = "";
				// Pattern p = Pattern.compile("[a-zA-Z]+(?:[']?[a-zA-Z]+)*\\b");
				String pat = "[^a-zA-Z0-9']";
				String a = "";
				int i = 0;
				while ((line = inputFile1.readLine()) != null) {
					String arr[] = line.split(" ");
					for (int j = 0; j < arr.length; j++) {
						
						a = arr[j].replaceAll(pat, "").toLowerCase();

						a = a.replaceAll("[\\p{Cf}]", "");
						
						
						
						a = a.replaceAll("\\s+", "");
						
						if (hash1.get(a) == null) {
							hash1.put(a, 1);
							ll2.addToEnd(a);
							// System.out.println(hash1.get(a) + ": " + a);
						}
						else {
							int amount = hash1.get(a) + 1;
							hash1.delete(a);
							hash1.put(a, amount);
							//ll2.addToEnd(arr[j].toLowerCase());
							//System.out.println(hash1.get(a) + ": " + a);
						}
						//System.out.println(hash1.get(a) + ": " + a);
					}
				}
				for (int ii = 0; ii < length; ii++) {
					//hash1.printAll(ii);
				}
				inputFile1.close();
					
				
				//System.out.println("off)".replaceAll(pat, ""));
				
				
				
				LinkedList ll = new LinkedList();
				
				String newArr[];
				for (int jj = 0; jj < length; jj++) {
					newArr = hash1.cycle(jj);
					for (i = 0; i < newArr.length; i++) {
						//System.out.println(newArr[i]);
						ll.addToEnd(newArr[i].replaceAll(pat, ""));
					}
				}
				ll2.insertionSort(ll.head);
				String fWord = "";
				for (int f = 0; f < ll2.len(); f++) {
					fWord = (ll2.printList(f));
					System.out.println(fWord + ": " + hash1.get(fWord));
					
				}
				inputFile1.close();
			
			} catch(IOException e) {
				System.err.println("Error: File does not exist:" + args[0]);
			}	
		}
	}
}