/*
 * Author: John Hunt
 * This class is a binary heap implemented as a tree
 * it provides a method to build the tree, print the
 * tree and to remove the last child from the tree.
 * The primary operations of remove and insert are left
 * as exercises
 */

public class Heap {

	class Node {
		int num;
		Node left;
		Node right;

		Node(int num, Node left, Node right){
			this.num = num;
			this.left = left;
			this.right = right;
		}

		public String toString(){
			return num + "";
		}
	}

	Node root;
	int nodeCnt;

	Heap(){
		root = null;
		nodeCnt = 0;
	}

	/*
	 * Builds a complete binary tree from the array
	 * passed in.  This method does not attempt to
	 * order the nodes on value.  For the resulting tree
	 * to have the quality of heapness the array must
	 * be in a correct order.
	 */
	void build(int nums[]){
		Node nodes[] = new Node[nums.length];
		for(int i = 0; i < nums.length; i++){
			nodes[i] = new Node(nums[i], null, null);
		}
		root = nodes[0];
		for(int i=0; i < nums.length/2; i++){
			nodes[i].left = nodes[2*i+1];
			nodes[i].right = nodes[2*i+2];
		}
		nodeCnt = nums.length;
	}

	void print(Node c, String indent){
		if(c != null) {
			System.out.println(indent + c.num);
			print(c.left, indent + "    ");
			print(c.right, indent + "    ");
		}
	}

	/*
	 * Performs a pre-order traversal of the tree
	 * printing the data value
	 */
	void printHeap() {
		print(root, "");
	}

	/*
	 * Returns the number of nodes in the tree
	 */
	int size() {
		return nodeCnt;
	}

	/*
	 * Removes the last child in the tree
	 * returning the last child's value
	 * The nodeCnt is decremented
	 */
	int removeLastChild() {
		int cnt = nodeCnt;
		String path = "";
		while(cnt >= 1) {
			path = (cnt %2) + path;
			cnt = cnt / 2;
		}

		int value = -1;
		Node c = root;
		for(int i = 1; i < path.length()-1; i++){
			if(path.charAt(i)== '0') {
				c = c.left;
			} else {
				c = c.right;
			}
		}
		if(path.length() == 1) {
			value = root.num;
			root = null;
		} else if(path.charAt(path.length()-1)== '0') {
			value = c.left.num;
			c.left = null;
		} else {
			value = c.right.num;
			c.right = null;
		}
		nodeCnt--;
		return value;
	}

	int remove() {
		
		System.out.println();
		int cnt = nodeCnt;
		String path = "";
		while(cnt >= 1) {
			path = (cnt %2) + path;
			cnt = cnt / 2;
		}

		// System.out.println("Path length: " + path.length());
		Node c = root;
		int removed = c.num;
		int lastChild = removeLastChild();
		//System.out.println("Replace " + c.num + " with " + lastChild + " and then trickle down");
		c.num = lastChild;
		
		

		for (int i = 0; i < path.length(); i++) {

			int nextLeft = 0;
			int nextRight = 0;
			boolean check1 = false;
			boolean check2 = false;
			int current = c.num;
			
			if (c.left != null) {
				nextLeft = c.left.num;
				check1 = true;
			}
			if (c.right != null) {
				nextRight = c.right.num;
				check2 = true;
			}
			
			if (check1 == false && check2 == true && c.right.num > c.num) {
				c.num = nextRight;
				c.right.num = current;
				//System.out.println("Switch: " + current + " for: " + nextRight);
				c = c.right;
			}
			else if (check1 == true && check2 == false && c.left.num > c.num) {
				c.num = nextLeft;
				c.left.num = current;
				//System.out.println("Switch: " + current + " for: " + nextLeft);
				c = c.left;
			}
			else if (check1 == true && check2 == true) {
				if (c.num < c.left.num && c.num < c.right.num) {
					int leftOver = c.left.num - c.num;
					int rightOver = c.right.num - c.num;
					if (leftOver > rightOver) {
						c.num = nextLeft;
						c.left.num = current;
						//System.out.println("Switch: " + current + " for: " + nextLeft);
						c = c.left;
					}
					else {
						c.num = nextRight;
						c.right.num = current;
						//System.out.println("Switch: " + current + " for: " + nextRight);
						c = c.right;
					}
				}
				else if (c.num < c.left.num) {
					c.num = nextLeft;
					c.left.num = current;
					//System.out.println("Switch: " + current + " for: " + nextLeft);
					c = c.left;
				}
				else if (c.num < c.right.num) {
					c.num = nextRight;
					c.right.num = current;
					//System.out.println("Switch: " + current + " for: " + nextRight);
					c = c.right;
				}
				else {
					// System.out.println("Left: " + c.left.num + " Right: " + c.right.num + "Num: " + c.num);
				}
			}
			else {
				//System.out.println("Greatest?");
			}
			
		}
		System.out.print("Removed: ");
		return removed;
	}

	public static void main(String[] args) {
		Heap h = new Heap();
		int numbers[] = {50, 30, 20, 25, 15, 7, 18, 22, 13, 14, 9, 2,3, 1,17};
		h.build(numbers);
		h.printHeap();

		// put your code to test remove here
		//commented out until you add a working remove
		while(h.size() > 0) {
		    System.out.println(h.remove());
		}
		//h.printHeap();


	}

}
