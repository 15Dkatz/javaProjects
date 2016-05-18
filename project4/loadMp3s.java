import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;


import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO; 
import org.jaudiotagger.audio.exceptions.CannotReadException; 
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException; 
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException; 
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class loadMp3s {
	//used to pair song titles and paths for MPlayerPanel to find songs
	public static HashMap<String, String> mp3TitlePathPairs = new HashMap<String, String>();
	
	public static HashMap<String, String> getMp3TitlePathPairs() {
		return mp3TitlePathPairs;
	}

	public static void setMp3TitlePathPairs(HashMap<String, String> mp3TitlePathPairs) {
		loadMp3s.mp3TitlePathPairs = mp3TitlePathPairs;
	}

	//additional methods ************************************************************************************
	public static int countSongs(File[] files) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		int fileCount = 0;
	    for (File file : files) {
	        if (file.isDirectory()) {
	            //System.out.println("Directory: " + file.getName());
	            countSongs(file.listFiles()); // Recursive call.
	        } else {
	    		fileCount++;
	        }   
	    }
	    return fileCount;   
	}
	
	
	public static void makeSongsTxt(File[] files, String fileName) throws IOException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		fileMaker songsFile = new fileMaker(fileName);
		int fileCount = countSongs(files);
		songsFile.writeToFile(fileCount);
		postMakeSongsTxt(files, songsFile);
		songsFile.closeFile();
	}

	
	public static void postMakeSongsTxt(File[] files, fileMaker songsFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            // System.out.println("Directory: " + file.getName());
	            postMakeSongsTxt(file.listFiles(), songsFile); // Recursive call.
	        } else {
	        	// System.out.println("File: " + file.getName());
	        	String fileName = file.getName();
	        	if ((fileName.substring(fileName.length()-4)).equals(".mp3")) {
	        		AudioFile f = AudioFileIO.read(file);
	    			Tag tag = f.getTag();
	    			String title = tag.getFirst(FieldKey.TITLE);
	    			String artist = tag.getFirst(FieldKey.ARTIST);
	    			String album = tag.getFirst(FieldKey.ALBUM);
	    			
	    			String path = file.toString();
	    			
	    			
	    			songsFile.writeToFile(title);
	    			songsFile.writeToFile(artist);
	    			songsFile.writeToFile(album);
	    			songsFile.writeToFile(path);
	    			
	    			mp3TitlePathPairs.put(title, path);
	    			
	    			songsFile.println();
	        	}
	        }   
	    }
	}
	
	public static void makePathTitlePairs(File[] files) throws IOException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		postMakePathTitlePairs(files);
	}

	
	public static void postMakePathTitlePairs(File[] files) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            // System.out.println("Directory: " + file.getName());
	        	postMakePathTitlePairs(file.listFiles()); // Recursive call.
	        } else {
	        	// System.out.println("File: " + file.getName());
	        	String fileName = file.getName();
	        	if ((fileName.substring(fileName.length()-4)).equals(".mp3")) {
	        		AudioFile f = AudioFileIO.read(file);
	    			Tag tag = f.getTag();
	        		String title = tag.getFirst(FieldKey.TITLE);
	    			String path = file.toString();
	    			mp3TitlePathPairs.put(title, path);
	        	}
	        }   
	    }
	}
	
	public static String[][] buildSongDarr(String fileName) throws NumberFormatException, IOException {
		BufferedReader bfReader = new BufferedReader(new FileReader(fileName));
		int rows = Integer.parseInt((bfReader.readLine().trim()));
		
		// read first line to find songDoubleArray rows
		String[][] songDarr = new String[rows][2];
		
		int rowIndex = 0;
		int index = 2;
		
		String line = bfReader.readLine();
		songDarr[rowIndex][0] = line;
		line = bfReader.readLine();
		songDarr[rowIndex][1] = line;
		rowIndex++;
		
		while (line != null) {
			if ((index-1)%5==0) {
				if (line!=null) {
					songDarr[rowIndex][0] = line;
//					mp3TitlePathPairs.put(line.trim(), path);
				}
				line = bfReader.readLine();
				if (line!=null) {
					songDarr[rowIndex][1] = line;
				}
				rowIndex++;
				index++;
			}
			line = bfReader.readLine();
			index++;
		}
		bfReader.close();
		return songDarr;
	}
	
	// saves the song.txt file to the directory
	public static String saveTextFile(String fileName) {
		return fileName;
	}

	public static void makeXmlFile(File[] files, String fileName) throws IOException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		fileMaker songsFile = new fileMaker(fileName);
		
		int fileCount = countSongs(files);
		songsFile.writeToFile("<songNumber value = " + String.valueOf(fileCount) + "/>");
		songsFile.writeToFile("<songs>");
		postMakeXmlFile(files, songsFile);
		
		songsFile.writeToFile("</songs>");
		songsFile.closeFile();
		
	}
	
	public static void postMakeXmlFile(File[] files, fileMaker songsFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            // System.out.println("Directory: " + file.getName());
	            postMakeXmlFile(file.listFiles(), songsFile); // Recursive call.
	        } else {
	        	// System.out.println("File: " + file.getName());
	        	String fileName = file.getName();
	        	if ((fileName.substring(fileName.length()-4)).equals(".mp3")) {
	        		AudioFile f = AudioFileIO.read(file);
	    			Tag tag = f.getTag();
	    			String title = tag.getFirst(FieldKey.TITLE);
	    			String artist = tag.getFirst(FieldKey.ARTIST);
	    			String album = tag.getFirst(FieldKey.ALBUM);
	    			String rating = tag.getFirst(FieldKey.RATING);
	    			String genre = tag.getFirst(FieldKey.GENRE);
	    			String producer = tag.getFirst(FieldKey.PRODUCER);
	    			
	    			songsFile.writeToFile("  <song>");
	    			songsFile.writeToFile("    <song title = " + title + ">");
	    			songsFile.writeToFile("    <artist name = " + artist + "/>");
	    			songsFile.writeToFile("    <album title = " + album + "/>");
	    			songsFile.writeToFile("    <producer name = " + producer + "/>");
	    			songsFile.writeToFile("    <genre title = " + genre + "/>");
	    			songsFile.writeToFile("    <rating value = " + rating + "/>");
	    			songsFile.writeToFile("  </song>");
	    			songsFile.println();
	        	}
	        }   
	    }
	}
	
	
	
	
}