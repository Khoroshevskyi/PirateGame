import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

//class for opening, reading, visualising and writing file
public class Reading extends JFrame {
	public String text = " ";
	public ArrayList<String> players ;
	String fileWinners = ".\\data\\results";
	
	//opens and reads file
	public Reading() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileWinners));
			String line;
			ArrayList<String> p = new ArrayList<String> () ;
			players = p;
			while ((line = br.readLine()) != null) {
				this.text += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			 try {
				 br.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		}
		System.out.println(text);
	}
	
	// makes window with previous results
	public void scoreWindow()  {
	    JPanel middlePanel = new JPanel ();

	    // create the middle panel components
	    JTextArea display = new JTextArea ( 16, 55 );
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

	    display.setText(text);
	    
	    //Add Textarea in to middle panel
	    middlePanel.add ( scroll );
	    
	    JFrame frame = new JFrame ();
	    frame.add ( middlePanel );
	    frame.pack ();
	    frame.setLocationRelativeTo ( null );
	    frame.setVisible ( true );
	    //fileCreator();
	}
	
	//write data to the file 
	public void fileCreator(String nickname, String level, String stepsMade) {
		text += nickname + "  ";
		text += level + "  ";
		text += stepsMade + "";
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileWinners),  "utf-8"));
		    writer.append(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception e) {e.printStackTrace();}
		}
	}	
}
