package lab7;

public class LinkedList {
	private Node root;
	private Node previous;
	private Node current;
	
	
	public LinkedList() {
		this.root = null;
		this.current = null;
	}

	
	public void print() {
		this.current = this.root;
		if (this.root!=null) {
			recursivePrint(this.current);
		}
	}
	
	public void recursivePrint(Node currentNode) {
		if (currentNode!=null) {
			System.out.println(currentNode.toString());
		}
		if (currentNode.getNext()!=null) {
			recursivePrint(currentNode.getNext());
		}
	}
	
	public void technicalPrint() {
		this.current = this.root;
		if (this.root!=null) {
			recursiveTechPrint(this.current);
		}
	}
	
	public void recursiveTechPrint(Node currentNode) {
		if (currentNode!=null&&currentNode.getWord().length()>=5&&currentNode.getCount()>5) {
			System.out.println(currentNode.toString());
		}
		if (currentNode.getNext()!=null) {
			recursiveTechPrint(currentNode.getNext());
		}
	}
	
	public Node search(Node target) {
		this.current = this.root;
		
		while (this.current!=null) {
			int wordsCompared = this.current.compareTo(target);
			
			if (wordsCompared==0) {
				return this.current;
			}
			
			
			this.current = this.current.getNext();
		}
		return null;
	}

	public void insert(Node newNode) {
		//sorted Insert
		if (this.root==null) {
			this.root = newNode;
			// this.current = this.root;
			return;
		} else {
			boolean setNext = true;
			int initWordsCompared = this.root.compareTo(newNode);
			if (initWordsCompared<0) {
				Node rootNext = this.root;
				this.root = newNode;
				this.root.setNext(rootNext);
				setNext = false;
			} else if (initWordsCompared==0) {
				this.root.setCount(this.root.getCount()+1);
				setNext = false;
			}
			
			this.current = this.root;			
			
			while (this.current.getNext()!=null) {
				
				int wordsCompared = this.current.compareTo(newNode);
				if (wordsCompared==0&&setNext==true) {
					this.current.setCount(this.current.getCount()+1);
					setNext = false;
					return;
				}
				
				if (wordsCompared<0&&setNext==true) {
					Node newNext = this.current;
					
					this.previous.setNext(newNode);
					newNode.setNext(newNext);
					setNext = false;
				}
				
				this.previous = this.current;
				this.current = this.current.getNext();
			}
			
			if (setNext==true) {
				this.current.setNext(newNode);
			}	
		}
	}
}
