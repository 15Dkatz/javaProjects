Name: David Katz
Student ID: 20355305

This lab took a lot of setup - both with a new IDE and the code itself. First, Getting myself oriented with Eclipse felt .jar-ring at first given that I've used Sublime for everything else up to this point. However, after learning its functionality I came to appreciate the editor for its Java powers. Next, with the code, I had to set up the jar files for JaudioTagger and JLayer - the two libraries needed to make the actual songPlayer functionality of this project. It took some time to figure out how to install these, but I learned it soon enough. Finally, with the actual program I encountered some more challenges. I first had to figure out how to include an effective FileWriter. I decided to use the FileWriter class and create my own fileMaker class to handle creating my songs.txt file (where I store all my songs). I also created a loadMp3s class to essentially handle all button funcitons needed in the GUI. Sorting the list of songs actually presented a unique difficulty, but eventually I found a solution with creating a SongsComparator tha timplemented the Comparable interface. 

Overall, I enjoyed this project - and who knows, maybe one day I will find myself on the development team of the next virtual reality music platform and fondly remember the basics of Audio-manipulation that I explored in project4.



I did not commit the large jaudiotagger.jar file nor the large jl1.0.1.jar file, because I guessed that the reviewer would have these built in the testing directory. This may be the cause of any issues then in running the code with anything to do with JaudioTagger or JLayer functions.

EXTRA CREDIT:
If you run MPlayerPanel (my main class), an xml file title "songs.xml" will automatically appear within the local directory containing the xml-ified version of whatever lies in the mp3s directory.


Thanks, 
David