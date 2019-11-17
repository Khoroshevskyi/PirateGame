import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

//this class is making starting window where you can type your nickname and preferable options in the game
public class Startwindow extends JFrame  {
	
	public String[] gameValues;
	private JLabel text1 = new JLabel(" Hallo in Pirate game!");
	private JLabel text2 = new JLabel(" Choose your preferneces below  ");
	private JLabel usNameText = new JLabel(" Set your Username:");
	private JTextArea usNameField = new JTextArea("Player", 1,10);
	private JLabel diffLevelText = new JLabel(" Choose your level:");
	private JComboBox diffLevelChoose = new JComboBox<String>(
		    new String[]{
		    	      "---1---",
		    	      "---2---",
		    	      "---3---",
		    	      "---4---",
		    	      "---5---"
		    	});
	private JLabel startValuesText = new JLabel(" Choose your start values(quantity: )");
	private JComboBox startValuesChoose = new JComboBox<String>(
		    new String[]{
		    	      "More provision",
		    	      "More guns",
		    	      "More protection"
		    	});
	private JLabel scoreText = new JLabel(" Check score list: ");
	private JButton scoreButton = new JButton(" Scores ");
	private JButton cancelButton = new JButton(" Exit ");
	private JButton playButton = new JButton(" Lets Play! ");
	
	public Startwindow() {
		super("Pirate game. Start!");
		this.setBounds(450, 200, 250, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mainContainer = this.getContentPane();
		
		mainContainer.setLayout(new GridLayout(6, 2, 7, 7));
		
		// adding necessary information + fields for writing
		mainContainer.add(text1);
		mainContainer.add(text2);
		mainContainer.add(usNameText);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		this.usNameField.setBorder(border);
		mainContainer.add(usNameField);
		
		mainContainer.add(startValuesText);
		mainContainer.add(startValuesChoose);
		
		mainContainer.add(diffLevelText);
		mainContainer.add(diffLevelChoose);
		
		mainContainer.add(scoreText);
		
		//button for opening Reading class for see score of all players
		this.scoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reading read = new Reading();
				read.scoreWindow();
			}
		});
		mainContainer.add(scoreButton);
		
		//exit button
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//button for retrieving all necessary information for starting game and starting the game 
		mainContainer.add(cancelButton);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actions();
			}
		});
		mainContainer.add(playButton);

		pack();
		setVisible(true);
				
	}
	
	//retrieve all information which was put by user
	private void actions() {
		String[] gameValues = new String[3];
		
		int startValue = startValuesChoose.getSelectedIndex();
		gameValues[0] = String.valueOf(startValue);
		int difficulty = diffLevelChoose.getSelectedIndex();
		gameValues[1] = String.valueOf(difficulty);
		String playerName = usNameField.getText();
		gameValues[2] = playerName;
		
		massageRules();
		Main nextStep = new Main();
		nextStep.start(gameValues);
		dispose();
	}
	
	//opening massage with rules how player has to play
	private void massageRules() {
		String massage = "Welcome in Pirate game \n"
				+ "You have possibility to go on the square that is near you \n "
				+ "You have to collect 4 treasures.\n"
				+ "But we have oponents: \n"
				+ "--> pirates (you will lose 4 protection, but if you will kill him, you will get 2 provision and 2 guns \n"
				+ "--> sea monster (you will lose 2 protection and 2 guns, but you will recieve 2 provision \n"
				+ "Also we have --> rocks - don't step on them!- you will lose 1 protection \n"
				+ "Every step you will give 1 provision \n"
				+ "Have a nice game, and Good luck! ";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
}
