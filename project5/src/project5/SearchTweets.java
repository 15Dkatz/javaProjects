package project5;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Scanner;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class SearchTweets {
  
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true) 
        .setOAuthConsumerKey("6fQa131AghIEVzGfQS22yGjyL")
        .setOAuthConsumerSecret("sOCvftTopnywyyp5gajNYWdxKxo6Ag8kB8er7GnTGxYonD7xoP")
        .setOAuthAccessToken("2610149947-RTvO5xfjSraSJxNaPjiyrV6JDq9rX1sgpLlPLzc")
        .setOAuthAccessTokenSecret("f3XmfLEtx9puEmAkRVWugiWUhOK9g5EaaXAgS08SGtbnZ");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        BinarySearchTree userTweets = new BinarySearchTree();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a seach query (i.e. Warriors, USF, ramen): ");
        String searchTerm = sc.next();
        
        try {
        	Query query = new Query(searchTerm);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {    	
                    userTweets.insert(new Node(tweet.getUser().getScreenName(), tweet.getText()));
                }
            } while ((query = result.nextQuery()) != null);

            userTweets.printTree();
            
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        
        System.out.println("Now please search a ScreenName in the list: ");
        String userSearch = sc.next();
        userTweets.search(userSearch);
        sc.close();
        System.out.println("*** Now exiting the program. Please re-run to perform a new search. ***");
        System.exit(0);
    }
    
    
}
