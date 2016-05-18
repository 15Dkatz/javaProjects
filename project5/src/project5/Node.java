// must implement comparable
package project5;

public class Node 
implements Comparable<Node>
{
	private String key;
	private String value;
	
	private Node left;
	private Node right;
	
	//Constructor
	public Node(String inputKey, String inputValue) {
		this.key = inputKey;
		this.value = inputValue;
		// this.left = inputLeft;
		// this.right = inputRight;
	}
	
	//Setters
	public void setKey(String newKey) {
		this.key = newKey;
	}
	
	public void setValue(String newValue) {
		this.value = newValue;
	}
	
	public void setLeft(Node newLeft) {
		this.left = newLeft;
	}
	
	public void setRight(Node newRight) {
		this.right = newRight;
	}
	
	//Getters
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public String toString() {
		return "userId: " + this.key + " | tweet: " + this.value;
	}

	// compare method sorts by key
	@Override
	public int compareTo(Node otherNode) {
		if (otherNode!=null) {
			return this.key.compareTo(otherNode.getKey());	
		} else {
			return 0;
		}
		
	}
	
}