//Starting point for BSTArray
// Author John M Hunt
public class BSTArray {
	int tree[];
	public BSTArray() {
		tree = new int[100];
	}
	// insert a single entry into the tree
	public void insert(int key) {
		int root = 0;
		int leftSon = 1;
		int rightSon = 2;
		
		for (int i = 0; i < tree.length; i++) {
			
			if (tree[0] == 0) {
				setRoot(key);
				break;
			}
			else if (tree[leftSon] == 0 && tree[root] > key) {
				tree[leftSon] = key;
				break;
			}
			else if (tree[rightSon] == 0 && tree[root] < key) {
				tree[rightSon] = key;
				break;
			}
			if (tree[root] > key) {
				root = (2 * root) + 1;
			}
			else {
				root = (2 * root) + 2;
			}
			leftSon = (2 * root) + 1;
			rightSon = (2 * root) + 2;
		}
	}
	

	public void preorder() {
		preOrder(0, 0);
	}
	
	public void preOrder(int index, int i) {
		if (index >= tree.length) {
			return;
		}
		if (tree[index] == 0) {
		
		}
		else {
			for (int j = 0; j < i; j++) {
				System.out.print("...");
			}
			System.out.print(tree[index]);
			System.out.println();
		}
		i++;
		preOrder((index * 2) + 1, i);
		preOrder((index * 2) + 2, i);
	}
	
	public void setRoot(int key) {
		tree[0] = key;
	}
	
	public static void main(String[] args) {
		int numbers[] = {50,25,75,10,15,5,53,29,79,78,111,33};
		BSTArray bst = new BSTArray();
		for (int i = 0; i < numbers.length; i++) {
			bst.insert(numbers[i]);
		}
		bst.preorder();
	}
}