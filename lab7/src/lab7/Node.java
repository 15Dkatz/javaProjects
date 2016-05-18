package lab7;

public class Node {
	private String word;
	private int count;
	private Node next;
	private Node prev;
	
	
	public Node(String word) {
		this.word = word;
		this.count = 1;
		this.next = null;
		this.prev = null;
	}

	
	public Node getPrev() {
		return prev;
	}


	public void setPrev(Node prev) {
		this.prev = prev;
	}




	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Node getNext() {
		return next;
	}


	public void setNext(Node next) {
		this.next = next;
	}
	

	@Override
	public String toString() {
		return "* " + word + " | Count: " + count;
	}
	
	public int hashCode() {
		char fLetter = this.word.toUpperCase().charAt(0);
		int index = (int)fLetter-65;
		if (index<0 || index>26) {
			index=0;
		}
		return index;
	}

	public int compareTo(Node otherNode) {
		return otherNode.getWord().compareTo(this.word);
	}
	
}