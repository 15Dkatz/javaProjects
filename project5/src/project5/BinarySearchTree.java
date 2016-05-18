package project5;

public class BinarySearchTree {
	private Node root;
	private Node current;

	public BinarySearchTree() {
		this.root = null;
		this.current = null;
	}

	public void insert(Node newNode) {
		// this method ensures that the sorting begins at the root
		this.current = this.root;
		postInsert(newNode);
	}

	public void postInsert(Node newNode) {
		if (this.root == null) {
			this.root = newNode;
			this.current = this.root;
		}
		// this ensures that the sorting will go in the actual spot rather than simply to the left or right of the current Node. 
		else {
			if (newNode.compareTo(this.current)<0) {
				if (this.current.getLeft()!=null) {
					this.current = this.current.getLeft();
					postInsert(newNode);
				}
				else {
					this.current.setLeft(newNode);
				}
			} else {
				if (this.current.getRight()!=null) {
					this.current = this.current.getRight();
					postInsert(newNode);
				} else {
					this.current.setRight(newNode);
				}
			}
		}
	}

	public void search(String searchString) {
		System.out.println("*** Searching for: " + searchString + " ***");
		search(this.root, searchString);
	}

	public void search(Node searchNode, String searchString) {
		if (searchNode!=null) {
			search(searchNode.getLeft(), searchString);
			if (searchNode.getKey().contains(searchString)) {
				System.out.println("* Found: " + searchNode.toString() + " *");
			}
			search(searchNode.getRight(), searchString);
		}
	}

	public void printTree() {
		// this method allows you to call the printTree method without passing in a parameter.
		System.out.println("*** Printing List of Users ***");
		printTree(this.root);
	}

	public void printTree(Node printNode) {
		// printing just the screeNames
		if (printNode != null) {
			printTree(printNode.getLeft());
			System.out.println("ScreenName | " + printNode.getKey());
			printTree(printNode.getRight());
		}	
	}

}