
/* Josh Newsome COS230 ASG3
 * 
 * 
 * https://www.geeksforgeeks.org/splay-tree-set-2-insert-delete/?ref=lbp
 * https://algs4.cs.princeton.edu/33balanced/SplayBST.java.html
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Dictionary{

    private Node root;
    
    private class Node {
        private String key;
        private String value;
        private Node left, right;

        public Node(String key, String value) {
            this.key   = key;
            this.value = value;
        }
    }

    public boolean contains(String key) { // Checks if word is contained in tree
        return find(key) != null;
    }

    public String find(String key) { // Finds key in tree and returns value
        root = splay(root, key);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root.value;
        else          return null;
    }    

    public void add(String key, String value) { // Inserts key into tree.
        // splay key to root
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        // Insert new node at root
        if (cmp < 0) {
            Node n = new Node(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        else if (cmp > 0) {
            Node n = new Node(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        // It was a duplicate key. Simply replace the value
        else {
        	String ogS = root.value;
            root.value = ogS + "	other definition: " + value;
        }

    }
    
    public void postorder() { // Post-order print of tree.
		Node current = root;
		postOrder(current, 0);
	}
	
	public void postOrder(Node root, int i) { // Post-order print of tree.
		if (root != null) {
			for (int j = 0; j < i; j++) {
				System.out.print("...");
			}
			System.out.print(root.key);
			System.out.println();
			i++;
			postOrder(root.right, i);
			postOrder(root.left, i);
		}
	}
	
	public void inorder() { // In-order print of tree.
		Node current = root;
		inOrder(current);
	}

	public void inOrder(Node root) { // In-order print of tree.
		if(root != null) {
			inOrder(root.left);
			System.out.println(root.key + ": " + root.value); 
			inOrder(root.right);
		}
	}
	
	public void preorder() { // Pre-order print of tree
		Node current = root;
		preOrder(current, 0);
	}
	
	public void preOrder(Node root, int i) { // Pre-order print of tree
		if(root != null) {
			for (int j = 0; j < i; j++) {
				System.out.print("...");
			}
			System.out.print(root.key);
			System.out.println();
			i++;
			preOrder(root.left, i);
			preOrder(root.right, i);
		}
	}
	
	public void list(String b1, String b2) { // Lists tree keys alphabetically, with bounds.
		Node current = root;
		lister(current, b1, b2);
	}
	
	public void lister(Node root, String b1, String b2) { // Lists tree keys alphabetically, with bounds.
		Node current = root;
		if(current != null) {
			lister(current.left, b1, b2);
			if (current.key.compareTo(b1) >= 0 && current.key.compareTo(b2) <= 0) {
				System.out.println(current.key);
			}
			lister(current.right, b1, b2);
			
		}
	}
   
	public void remove(String key) { // Remove key from tree. Splays after
        if (root == null) return;
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } 
            else {
                Node x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }

        // else: it wasn't in the tree to remove
    }
    
    private Node splay(Node h, String key) { // Splays tree
        if (h == null) return null;

        int cmp1 = key.compareTo(h.key);

        if (cmp1 < 0) {
            if (h.left == null) {
                return h;
            }
            int cmp2 = key.compareTo(h.left.key);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }
            
            if (h.left == null) return h;
            else                return rotateRight(h);
        }

        else if (cmp1 > 0) { 
            if (h.right == null) {
                return h;
            }
            int cmp2 = key.compareTo(h.right.key);
            if (cmp2 < 0) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            
            if (h.right == null) return h;
            else                 return rotateLeft(h);
        }

        else return h;
    }

    public int height() { // Find height of tree
    	return height(root); 
    }
    
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    
    public int size() { // Find size
        return size(root);
    }
    
    private int size(Node x) { // Find size
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }
    
    private Node rotateRight(Node h) { // Right rotation
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node rotateLeft(Node h) { // Left rotation
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    public static void main(String[] args) {
        Dictionary st = new Dictionary();        
        Scanner myObj = new Scanner(System.in); 
        int i = 1;
        int counter = 0;
        String[] inputArr;
        String input = null;
        while (i != 0) {

        	if (args.length > counter) {
        		String fileLineLoader = "load " + args[counter];
        		inputArr = fileLineLoader.split(" ");
        		counter++;
        		for (int printer = 0; inputArr.length < printer; printer++) {
        			System.out.println(inputArr[printer]);
        		}
        	}
        	else {
        		System.out.println("Pick input:");
            	System.out.println("Add");
            	System.out.println("Find");
            	System.out.println("List");
            	System.out.println("Load");
            	System.out.println("Tree");
            	System.out.println("Delete");
            	System.out.println("Quit");
            	
            	input = myObj.nextLine();
            	input = input.toLowerCase();
            	inputArr = input.split(" ");
        	}
            
        	
        	System.out.println();
        	
			if (inputArr[0].equals("add")) {
        		String def = "";
        		for (int j = 2; j < inputArr.length; j++) {
        			def = def + inputArr[j] + " ";
        		}
        		
        		if (inputArr.length < 3) {
        			System.out.println("Wrong amount of inputs");
        		}
        		else {
        			st.add(inputArr[1], def);
        		}
        	}
        	else if (inputArr[0].equals("find")) {
        		
        		if (inputArr.length != 2) {
        			System.out.println("Wrong amount of inputs");
        		}
        		else {
        			if (st.find(inputArr[1]) == null) {
        				System.out.println("Doesn't exist");
        			}
        			else {
        				System.out.println("Definition for " + inputArr[1] + ": " + st.find(inputArr[1]));
        			}
        		}
        	}
        	else if (inputArr[0].equals("list")) {
        		if (inputArr.length == 3) {
        			st.list(inputArr[1], inputArr[2]);
        		}
        		else if (inputArr.length == 1) {
        			st.inorder();
        		}
        		else {
        			System.out.println("Wrong amount of inputs");
        		}
        	}
        	else if(inputArr[0].equals("load")) {
        		
        		try (BufferedReader br = new BufferedReader(new FileReader(inputArr[1]))) {
        		    String line;
        		    while ((line = br.readLine()) != null) {
        		    	line = line.toLowerCase();
        		    	String[] lineArr = line.split(" ");
        		    	if (lineArr[0].equals("add")) {
        		    		String def = "";
        	        		for (int j = 2; j < lineArr.length; j++) {
        	        			def = def + lineArr[j] + " ";
        	        		}
        	        		if (lineArr.length < 3) {
        	        			System.out.println("Wrong amount of inputs");
        	        		}
        	        		else {
        	        			st.add(lineArr[1], def);
        	        		}
        		    	}
        		    	else if (lineArr[0].equals("find")) {
        		    		if (lineArr.length != 2) {
        	        			System.out.println("Wrong amount of inputs");
        	        		}
        	        		else {
        	        			if (st.find(lineArr[1]) == null) {
        	        				System.out.println("Does not exist.");
        	        			}
        	        			else {
        	        				System.out.println("Definition for " + lineArr[1] + ": " + st.find(lineArr[1]));
        	        			}
        	        		}
        		    	}
        		    	else if (lineArr[0].equals("list")) {
        	        		if (lineArr.length == 3) {
        	        			st.list(lineArr[1], lineArr[2]);
        	        		}
        	        		else if (lineArr.length == 1) {
        	        			st.inorder();
        	        		}
        	        		else {
        	        			System.out.println("Not a valid input.");
        	        		}
        	        	}
        		    	else if (lineArr[0].equals("tree")) {
        	        		st.preorder();
        	        	}
        		    	else if (lineArr[0].equals("delete")) {
        		    		st.remove(lineArr[1]);
        		    	}
        				else if (lineArr[0].equals("quit")) {
        	        		i = 0;
        	        		break;
        	        	}
        				else {
        					System.out.println("Not a valid input.");
        				}
        		    }
        		} catch (FileNotFoundException e) {
					System.out.println("File does not exist.");
				} catch (IOException e) {
					System.out.println("File does not exist.");
				}
        	}
			else if (inputArr[0].equals("tree")) {
        		st.preorder();
        	}
			else if (inputArr[0].equals("delete")) {
				st.remove(inputArr[1]);
			}
			else if (inputArr[0].equals("quit")) {
        		i = 0;
        		break;
        	}
			else {
				System.out.println("Not a valid input.");
			}
        	System.out.println();
        }
        myObj.close();
    }
}