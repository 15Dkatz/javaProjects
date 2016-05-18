package lab7;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CS112HashMap {
	private LinkedList[] hashMap = new LinkedList[26];

	public CS112HashMap() {
		// initialize all linkedLists
		for (int i = 0; i < 26; i++) {
			hashMap[i] = new LinkedList();
		}
	}

	public void add(Node newNode) {
		int index = newNode.hashCode();
		hashMap[index].insert(newNode);
	}

	public void print() {
		int p = 0;
		while (p < 26) {
			hashMap[p].print();
			p++;
		};
	}

	public void technicalPrint() {
		int p = 0;
		while (p < 26) {
			hashMap[p].technicalPrint();
			p++;
		}
		;
	}

	public void populate(String textFile) throws IOException {
		for (int i = 0; i < 26; i++) {
			hashMap[i] = new LinkedList();
		}

		// *** manually adding in stop Words to this set, for this long block of
		String[] stopWordsArr = { "A", "About", "Above", "After", "Again", "Against", "All", "Am", "An", "And", "Any",
				"Are", "Aren't", "As", "At", "Be", "Because", "Been", "Before", "Being", "Below", "Between", "Both",
				"But", "By", "Can't", "Cannot", "Coudln't", "Did", "Didnt", "Do", "Does", "Doesn't", "Doing", "Don't",
				"Down", "During", "Each", "Few", "For", "From", "Further", "Had", "Hadn't", "Has", "Hasn't", "Have",
				"Haven't", "Having", "He", "Hed", "Hell", "Hes", "Her", "Here", "Heres", "Hers", "Herself", "Him",
				"Himself", "His", "How", "How's", "I", "Id", "Ill", "Im", "I've", "If", "In", "Into", "Is", "Isn't",
				"It", "Its", "Itself", "Lets", "Me", "More", "Most", "Mustnt", "My", "Myself", "No", "Nor", "Not", "Of",
				"Off", "On", "Once", "Only", "Or", "Other", "Ought", "Our", "Ours", "Ourselves", "Out", "Over", "Own",
				"Same", "Shant", "She", "Shed", "Shell", "Shell", "Shes", "Should", "So", "Some", "Such", "Than",
				"That", "Thats", "The", "Their", "Theirs", "Them", "Themselves", "Then", "There", "There's", "These",
				"They", "Theyd", "Theyll", "Theyre", "Theyve", "This", "Those", "Through", "To", "Too", "Under",
				"Until", "Up", "Very", "Was", "Wasnt", "We", "Wed", "Well", "Were", "Weve", "Were", "Werent", "What",
				"Whats", "When", "Whens", "Where", "Wheres", "Which", "While", "Who", "Whos", "Whom", "Why", "Whys",
				"With", "Wont", "Would", "Wouldnt", "You", "Youd", "Youll", "Youre", "Youve", "Your", "Yours",
				"Yourself", "Yourselves" };
		Set<String> stopWords = new HashSet<String>();

		for (String word : stopWordsArr) {
			stopWords.add(word);
		}

		try {
			FileReader fReader = new FileReader(textFile);
			BufferedReader buffReader = new BufferedReader(fReader);
			String line = "";
			while (line != null) {
				line = buffReader.readLine();

				if (line != null && line.length() > 1) {
					// eliminating all possible double spaces
					line = line.replaceAll("\\s+", " ");
					String[] words = line.split(" ");

					for (String word : words) {
						word = word.trim();
						word = word.replaceAll("[^A-Za-z]", "");
						word = word.replaceAll(" ", "");
						if (word != null && word.length() > 0) {
							// capitalizing
							word = word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase();
							// considering stopWords
							if (!stopWords.contains(word)) {
								this.add(new Node(word));
							}
						}
					}
				}
			}

			buffReader.close();

		} catch (FileNotFoundException exception) {
			System.out.println("Unable to open " + textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int find(String query) {
		// get hashCode of query to limit which LinkedList you need to search
		Node queryNode = new Node(query);
		int index = queryNode.hashCode();
		Node foundNode = hashMap[index].search(new Node(query));
		if (foundNode != null) {
			return foundNode.getCount();
		} else {
			return -1;
		}
	}

}
