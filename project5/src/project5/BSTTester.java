
package project5;

public class BSTTester {
	public static void main(String[] args) {
		BinarySearchTree userTweets = new BinarySearchTree();

		
		// testing code
		userTweets.insert(new Node("Mario", "it's a me"));
		userTweets.insert(new Node("Luigi", "Welcome to my mansion"));
		userTweets.insert(new Node ("Bowser", "You can't have her"));
		userTweets.insert(new Node("Peach", "Would you like come cake?"));
		userTweets.insert(new Node("Dry Bones", "Click Click"));
		userTweets.insert(new Node("Dry Cakes", "Squish Squish"));
		userTweets.insert(new Node("Kupla", "Don't step on my head for the love of God!"));
		userTweets.insert(new Node("Bullet Bill", "Get out of my way - please, for your own sake."));

		// testing methods
		userTweets.search("Dry");
		userTweets.printTree();
	}

}