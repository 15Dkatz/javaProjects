package lab7;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException {

		CS112HashMap driverMap = new CS112HashMap();
		//reading from the command line, and using the .txt file to populate the hashmap
		if (args[0]!=null) {
			driverMap.populate(args[0]);
		} else {
			System.out.println("Please rerun the program by appending a text file to the command line arguments.");
		}
		
		int option = 0;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n***| Word Counter |***");
			System.out.println("This program counts the words of your .txt file \n\t** " + args[0] + " **");
			System.out.println("Please choose an option:");
			System.out.println("(1) PRINT all words longer than 4, and that have a frequency greater than 5.");
			System.out.println("(2) FIND the frequency of a specific word.");
			System.out.println("(3) EXIT the program.");
			
			option = sc.nextInt();
			
			
			switch(option) {
				case 1:
					driverMap.technicalPrint();
					break;
				case 2:
					System.out.println("Find a word's count in " + args[0]);
					String query = sc.next();
					String originalQuery = query;
					query = query.trim();
					query = query.replaceAll("[^A-Za-z]", "");
					query = query.replaceAll(" ", "");
					if (query!=null&&query.length()>0) {
						query = query.substring(0,1).toUpperCase() + query.substring(1, query.length()).toLowerCase();
					}
					int result = driverMap.find(query);
					if (!(result<0)) {
						System.out.println(originalQuery + " has a frequency of " + result);
					} else {
						System.out.println("\n\t*** " + originalQuery + " either does not exist in the file, or is too common of a word in the English language to be considered a Stop Word. ***"); 
					}
					
					break;
				default:
					break;
			}

		} while (option!=3);
		
		System.out.println("Exiting the word-counting program.");
		System.exit(0);

	}
}
