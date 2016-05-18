import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class MPlayerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton playButton, stopButton, exitButton, loadMp3Button, saveButton, openButton;
	private JTable table = null;
	private PlayerThread currThread;
	
	MPlayerPanel() {
	
	this.setLayout(new BorderLayout());
	
	// buttonPanelTop contains the top row of buttons:
	// load mp3 files, save and open
	JPanel buttonPanelTop = new JPanel();
	buttonPanelTop.setLayout(new GridLayout(1,3));
	loadMp3Button = new JButton("Load mp3");
	saveButton = new JButton("Save Library");
	openButton = new JButton("Load Library");

	loadMp3Button.addActionListener(new MyButtonListener());
	saveButton.addActionListener(new MyButtonListener());
	openButton.addActionListener(new MyButtonListener());

	buttonPanelTop.add(loadMp3Button);
	buttonPanelTop.add(saveButton);
	buttonPanelTop.add(openButton);
	this.add(buttonPanelTop, BorderLayout.NORTH);
		
	
	// Bottom panel with panels: Play, Stop, Exit buttons
	JPanel buttonPanelBottom = new JPanel();
	buttonPanelBottom.setLayout(new GridLayout(1,3));
	playButton = new JButton("Play");
	stopButton = new JButton("Stop");
	exitButton = new JButton("Exit");

	playButton.addActionListener(new MyButtonListener());
	stopButton.addActionListener(new MyButtonListener());
	exitButton.addActionListener(new MyButtonListener());

	buttonPanelBottom.add(playButton);
	buttonPanelBottom.add(stopButton);
	buttonPanelBottom.add(exitButton);
	this.add(buttonPanelBottom, BorderLayout.SOUTH);
	currThread = null;
}
	class MyButtonListener implements ActionListener {
		String[][] tableElems = null;
		
		
		public void actionPerformed(ActionEvent e) {
			// The function that does something whenever each
			// button is pressed
			if (e.getSource() == loadMp3Button) {
				//System.out.println("Load mp3 button");
				try {
					loadMp3s.makeSongsTxt(files, "songs.txt");
				} catch (IOException | CannotReadException | TagException | ReadOnlyFileException
						| InvalidAudioFrameException e2) {
					e2.printStackTrace();
				}
				
				try {
					tableElems = loadMp3s.buildSongDarr("songs.txt");
					// Sorting the tableElems after building it.
					Arrays.sort(tableElems, new SongComparator());
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
				
				
				String[] columnNames = {"Title", "Artist"};
			
				// you do not need to change the code below
				table = new JTable(tableElems, columnNames );
				JScrollPane scrollPane = new JScrollPane( table );
				add( scrollPane, BorderLayout.CENTER );
				
				updateUI();
			
			}
			else if (e.getSource() == saveButton) {
				// FILL IN CODE :  make the calls to other methods/classes, do NOT place all the code here
				// save the song catalog into a file called "songCatalog". 
				// The format is described in the handout.
				loadMp3s.saveTextFile("songs.txt");
				System.out.println("songs.txt saved in local directory");
			}
			else if (e.getSource() == openButton) {
				// FILL IN  CODE:  make the calls to other methods/classes, do NOT place all the code here
				// open the file songCatalog and load songs
				// into the arraylist of songs
				// assuming songs.txt exists
				try {
					tableElems = loadMp3s.buildSongDarr("songs.txt");
					// Sorting the tableElems after building it.
					Arrays.sort(tableElems, new SongComparator());
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
				
				
				String[] columnNames = {"Title", "Artist"};
			
				// you do not need to change the code below
				table = new JTable(tableElems, columnNames );
				JScrollPane scrollPane = new JScrollPane( table );
				add( scrollPane, BorderLayout.CENTER );
				
				updateUI();
				
			}
			else if (e.getSource() == playButton) {
				int selectedRow = table.getSelectedRow();
				String selectedSong = (String)table.getModel().getValueAt(selectedRow, 0);
				String path = loadMp3s.getMp3TitlePathPairs().get(selectedSong.trim());				
				currThread = new PlayerThread(path);
				currThread.start();
			}
			else if (e.getSource() == stopButton) {
				currThread.end();
				System.out.println("interrupted? " + currThread.isInterrupted());
					
			}
			else if (e.getSource() == exitButton) {
				// Exit the program
				System.exit(0);
			}
		}
	}
	
	public static File[] files;
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Mp3 player");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      MPlayerPanel panel  = new MPlayerPanel();
	      panel.setPreferredSize(new Dimension(400,400));
	      frame.getContentPane().add (panel);
	      
	      frame.pack();
	      frame.setVisible(true);
	      
	      files = new File("mp3s/").listFiles();
	      
	      try {
	    	  loadMp3s.makePathTitlePairs(files);
	    	  loadMp3s.makeXmlFile(files, "songs.xml");
	      } catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
	      }
	}
	
	
}