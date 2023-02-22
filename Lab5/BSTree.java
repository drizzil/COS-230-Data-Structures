//package tree;
// builds a tree to allow traversals
public class BSTree {
    static int startValues[] = {50, 20, 45, 55, 75, 12, 97, 17, 83, 76, 34, 66, 27, 53, 48};
    
    private class Node {
		int key;
		Node left;
		Node right;
		Node(int key) {
			this.key = key;
			left = right = null;
		}
    }
    
    Node root;
    
	public BSTree(){
		for(int i = 0; i < startValues.length; i++){
			insert(startValues[i]);
		}
	}
	
	public void insert(int key) {
		// assumes no duplicates
		if(root == null) {
			root = new Node(key);
		} 
		else {
			Node c = root;
			while(true) {
				if(key < c.key) {
					// go left
					if(c.left == null) {
						c.left = new Node(key);
						break;
					} 
					else {
						c = c.left;
					}
				} 
				else {
					// go right
					if(c.right == null){
						c.right = new Node(key);
						break;
					} 
					else {
						c = c.right;
					}
				}
			}
		}
	}

	public void preorder() {
		Node current = root;
		preOrder(current, 0);
	}
	
	public void preOrder(Node root, int i) {
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
	
	public void postorder() {
		Node current = root;
		postOrder(current, 0);
	}
	
	public void postOrder(Node root, int i) {
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
	
	public void inorder() {
		Node current = root;
		inOrder(current);
	}

	private void inOrder(Node root) {
		if(root != null) {
			inOrder(root.left);
			System.out.print(root.key + " "); 
			inOrder(root.right);
		}
	}
	
	public static void main(String[] args) {
		BSTree bst = new BSTree();
		bst.inorder();
		System.out.println();
		bst.postorder();
	}
}