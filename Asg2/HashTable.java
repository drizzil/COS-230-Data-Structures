import java.util.Iterator;

	class Node<A, B> {
		A key; 
		B val; 
		Node<A, B> next;
		
		public Node(A key, B val) { 
			this.key = key; 
			this.val = val; 
			//this.next = next; 
		}
		
		public A getKey() {
			return key;
		}
		
		public B getVal() {
			return val;
		}
				
		public void displayLink() { 
			System.out.print(key + " "); 
		}

	}
	

	
	
	class SortedList<K, V> {
		
		private Node<K, V> first;	
		
		public SortedList() {
			first = null;
		}
		
		public void insert(Node<K, V> theLink) {
			// K key = (K) theLink.getKey();
			Node<K, V> previous = null;
			Node<K, V> current = first;
			while( current != null) {
				
				previous = current; 
				current = current.next; 
			}
			if(previous==null) {
				first = theLink;
			}
			else {
				previous.next = theLink;
				theLink.next = current;
			}
		}
		
		public void printAll(Node<K, V> theLink) {
			
		}
		
		public void delete(K key) {
			Node<K, V> previous = null; 
			Node<K, V> current = first;
			while( current != null && !key.equals(current.getKey()) ) {
				previous = current; 
				current = current.next; 
			}
			if (current == null) {
				
			}
			else {
				if (first == null) {
					
				}
				else if(previous==null) {
					first = first.next;
				}
				else {
					previous.next = current.next; // delete current link }
				}
			}
		}
		
		public int len() {
			Node<K, V> current = first;
			int i = 0;
			while(current != null) {
				i++;
				current = current.next;
			}
			return i;
		}	
		
		public V find(K key) {
			// System.out.println(key);
			Node<K, V> current = first; // start at first
			// until end of list, 
			while(current != null) { // 
				if(current.getKey().equals(key)) {
					return current.getVal();
				}
				current = current.next;
			}
			return null;
		}
		
		public String [] cycleReturn() {
			
			Node<K, V> current = first;
			int i = 0;
			while(current != null) {
				i++;
				current = current.next;
			}
			current = first;
			String arr[] = new String[i]; 
			int j = 0;
			while(current != null) {
				arr[j] = (String) current.key;
				current = current.next; // move to next link
				j++;
			}
			return arr;
		}
		
		public void displayList() {
			System.out.print("List (first-->last): ");
			Node<K, V> current = first; 
			while(current != null) {
				current.displayLink();
				current = current.next; // move to next link 
			}
			System.out.println("");
		}
	
		public Iterator<K> iterator1() { 
			return new HashTableIterator<K, V>(this); 
		} 
	}

	
	public class HashTable<K, V> implements Table<K, V> {
		
		int size;
		
		SortedList<K, V>[] hashArray;
		public Node<K, V> tbl[];
		int idx;
		
		public int getHashIndex(K key) {
			//System.out.println(key.hashCode());
			if (key.hashCode() < 0) {
				return key.hashCode() % size * -1;
			}
			
			return key.hashCode() % size;
		}
		
		public HashTable(int size) { 
			this.size = size;
			hashArray = new SortedList[this.size];
			tbl =  new Node[size];//yes, this is bad 
			for (int i = 0; i < size; i++) {
				hashArray[i] = new SortedList<K, V>();
				tbl[i] = new Node<K, V>(null, null);
			}
			idx = 0; 
		} 
		
		public void put(K key, V value) {
			// System.out.println(key);
			Node<K, V> theLink = new Node<K, V>(key, value);
			K hKey = theLink.getKey();
			int hashVal = getHashIndex(hKey);
			//System.out.println(hashVal);
			hashArray[hashVal].insert(theLink);
			/*
			idx = getHashIndex(key);
	
			tbl[idx] = new Node<K, V>(key, value, null); 
			*/
			
		} 
		
		public void printAll(int index) {
			hashArray[index].displayList();
		}
		
		public V get(K key) {
			
			int hashVal = 0;
			hashVal = getHashIndex(key);
			V theLink = hashArray[hashVal].find(key); // get link 
			//V ret = (V) theLink.getKey();
			return theLink; // return link
		}
		
		public void delete(K key) {
			
			int hashVal = 0;
			hashVal = getHashIndex(key);
			hashArray[hashVal].delete(key); // delete link 
		}
		 
		public Iterator<K> iterator() {
			//Iterator<String> itr = SortedList.iterator1(); 
			//return new TestTableIterator2<K, V>(hashArray[idx]);
			return new HashTableIterator<K, V>(this); 
		} 
		
		public String [] cycle(int index) {
			return hashArray[index].cycleReturn();
		}		
} 
	