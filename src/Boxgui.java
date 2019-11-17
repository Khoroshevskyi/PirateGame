import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;

//this is main class that creating box for game and all buttons and gives work for another classes to calculate information for game
class Boxgui extends JFrame {

	public int[][] square;
	private JButton[][] buttons;
	public ArrayList<Integer> rowPos;
	public ArrayList<Integer> columnPos;
	public int rows;
	public int columns;
	public int[] startValues;
	
	private JLabel provisionText = new JLabel(" Provision values: ");
	private JLabel gunsText = new JLabel(" Guns quantity: ");
	private JLabel protectionText = new JLabel(" Protection: ");
	private JLabel treasureText = new JLabel(" Treasure: ");
	private ImageIcon blackIcon = new ImageIcon(".\\data\\black.png");
	private ImageIcon ichIcon = new ImageIcon(".\\data\\ich.jpg");
	private ImageIcon pirateIcon = new ImageIcon(".\\data\\pirate.png");
	private ImageIcon treasureIcon = new ImageIcon(".\\data\\treasure.jpg");
	private ImageIcon rocksIcon = new ImageIcon(".\\data\\rock.jpg");
	private ImageIcon monsterIcon = new ImageIcon(".\\data\\monster.png");
	private ImageIcon provisionIcon = new ImageIcon(".\\data\\provision.jpg");
	private ImageIcon nothingIcon = new ImageIcon(".\\data\\nothing.jpg");
	
	//keeps data for write in file document 
	public String[] winingData;
	
	//for opening window when player steps on items
	public Boolean helpWindow = true; 
	
	//constructor 
	public Boxgui (int[][] square, int sizeOfSquare, int[] firstPlayerLocation, int startValue, String[] winingData) {
		//0 - provision; 1 - guns; 2- protection, 3 - treasure;
		int[][] allStartValues = new int[][] {{17, 7, 7, 0},{10, 15, 7, 0},{10, 7, 15, 0}};
		this.startValues = allStartValues[startValue];
		
		this.winingData = winingData;
		
		this.square = square;
		this.rows = sizeOfSquare;
		this.columns = sizeOfSquare;
		
		// creating arraylist for keeping information where player played
		ArrayList<Integer> rowqPos = new ArrayList<Integer> ();
		ArrayList<Integer> columnqPos = new ArrayList<Integer> ();	
		this.rowPos = rowqPos;
		this.columnPos = columnqPos;
		this.columnPos.add(firstPlayerLocation[0]);
		this.rowPos.add(firstPlayerLocation[1]);
	
		//setting layout and gui in the game
		setLayout(new BorderLayout());
		
		JPanel values = new JPanel(new GridLayout(6, 1, 5, 5));
		JPanel bord = new JPanel();
		
		//adding buttons in the game
		buttons = new JButton[rows][columns];
		setTitle("Puzzle Time!");
		
		//getContentPane().setLayout(new GridLayout(rows, columns), BorderLayout.CENTER);
		GridLayout grid = new GridLayout(rows, columns);
		bord.setLayout(grid);
		
		values.add(provisionText);
		values.add(gunsText);
		values.add(protectionText);
		values.add(treasureText);
		
		provisionText.setText(" Provision values:  "+ startValues[0] + "  ");
		gunsText.setText(" Guns quantity: " + startValues[1] + "  ");
		protectionText.setText(" Protection: " + startValues[2] + "  ");
		treasureText.setText(" Treasure find: " + startValues[3] + " of 4  ");
		
		//exit button
		JButton cancelButton = new JButton(" Exit ");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		values.add(cancelButton);
		
		//getting back button
		JButton backButton = new JButton(" Go to Start menu ");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Startwindow();
				dispose();
			}
		});
		values.add(backButton);
		
		//ActionListener for game buttons
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font(Font.SERIF, Font.PLAIN, 20));
				buttons[i][j].setBackground(Color.white);
				buttons[i][j].setPreferredSize(new Dimension(60,60));
				
				// making action buttons 
				int my_i = i;
				int my_j = j;
				
				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						select(my_i, my_j);
					}
				});
				bord.add(this.buttons[i][j]);	
			}
		}
		newGame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(values, BorderLayout.EAST);
		add(bord, BorderLayout.WEST);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		pack();
		setVisible(true);	
	}	
	
	public void newGame() {
		setIcons();
		resetBackgrounds();
	}
	
	// set icons in buttons
	void setIcons() {
		for (int i = 1; i <= square.length; i++) {			
			for (int k = 1; k <= square.length; k++) {
				buttons[i-1][k-1].setIcon(blackIcon);
			}
		}
		this.buttons[columnPos.get(columnPos.size()-1)][rowPos.get(rowPos.size()-1)].setIcon(ichIcon);
		itemsNear();
	}
	
	//making Background in buttons
	private void resetBackgrounds() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (square[i][j] != 0 ) {
					buttons[i][j].setBackground(Color.black);
				} 
				else {
					buttons[i][j].setBackground(Color.black);
				}			
			}
		}
	}
	
	//method doing action when player press it
	//first validation whether you can step on it
	//second it calls two another methods. If it's not valid - it deleted last items in arraylist 
	private void select(int row, int column) {
		Validmove valid = new Validmove();
		columnPos.add(row);
		rowPos.add(column);
		
		if (valid.validation(columnPos, rowPos)) {	
			gameMovements();
			gameCalculations();
		} else {
			columnPos.remove(columnPos.size()-1);
			rowPos.remove(rowPos.size()-1);
		}
	}
	
	//set icons which are visible for player + set icons for buttons, where user killed anemy or find treasure
	private void gameMovements() {		
		this.buttons[columnPos.get(columnPos.size()-2)][rowPos.get(rowPos.size()-2)].setBackground(Color.black);
		this.buttons[columnPos.get(columnPos.size()-1)][rowPos.get(rowPos.size()-1)].setBackground(Color.red);
		
		this.buttons[columnPos.get(columnPos.size()-1)][rowPos.get(rowPos.size()-1)].setIcon(ichIcon);
		this.buttons[columnPos.get(columnPos.size()-2)][rowPos.get(rowPos.size()-2)].setIcon(nothingIcon);	
		square[columnPos.get(columnPos.size()-2)][rowPos.get(rowPos.size()-2)] = 0;
		itemsNear();
	}
	
	//makes calculation of player values 
	private void gameCalculations() {
		Calculations count = new Calculations(startValues, helpWindow);
		//System.out.println(Integer.valueOf(square[columnPos.get(columnPos.size()-1)][rowPos.get(rowPos.size()-1)]));
		
		switch(square[columnPos.get(columnPos.size()-1)][rowPos.get(rowPos.size()-1)]) {
			case 0:
				startValues=count.zero();
				break;
			case 1:
				startValues=count.one();
				break;
			case 2:
				startValues=count.two();
				break;
			case 3:
				startValues=count.three();
				break;
			case 4:
				startValues=count.four();
				break;
			case 5:
				startValues=count.five();
				break;	
		}
		
		// checking for wining or losing 
		Validmove valid = new Validmove(startValues);
		Boolean winer = valid.win();
		
		if (winer) {
			Reading reader = new Reading();
			reader.fileCreator(winingData[0], winingData[1], Integer.toString(columnPos.size()));
			new Startwindow();
			dispose();
		}
		if (!winer) { 
			Boolean loser = valid.lose(); 
			if (loser) {
				new Startwindow();
				dispose();
			}
		}
		
		//makes last information about player values visible
		//System.out.print(Arrays.toString(startValues));
		provisionText.setText(" Provision values:  "+ startValues[0] + "  ");
		gunsText.setText(" Guns quantity: " + startValues[1] + "  ");
		protectionText.setText(" Protection: " + startValues[2] + "  ");
		treasureText.setText(" Treasure find: " + startValues[3] + " of 4  ");
	}
	
	//checking if player is on border buttons
	private void itemsNear() {
		if ( columnPos.get(columnPos.size()-1) != 0) {
			imageItemsNear(columnPos.get(columnPos.size() - 1 ) - 1, rowPos.get(rowPos.size() - 1));
		}
		if ( columnPos.get(columnPos.size()-1) != columns -1 ) {
			imageItemsNear(columnPos.get(columnPos.size() - 1 ) + 1, rowPos.get(rowPos.size() - 1));
		}
		if ( rowPos.get(rowPos.size()-1) != 0 ) {
			imageItemsNear(columnPos.get(columnPos.size() - 1 ), rowPos.get(rowPos.size() - 1) - 1);
		}
		if ( rowPos.get(rowPos.size()-1) != rows - 1 ) {
			imageItemsNear(columnPos.get(columnPos.size() - 1 ), rowPos.get(rowPos.size() - 1) + 1);
		}
	}
	
	//And now makes visible this buttons
	private void imageItemsNear(int itemColumn,int itemRow) {
		switch(square[itemColumn][itemRow]) {
		case 0:
			buttons[itemColumn][itemRow].setIcon(nothingIcon);
			break;
		case 1:
			buttons[itemColumn][itemRow].setIcon(treasureIcon);
			break;
		case 2:
			buttons[itemColumn][itemRow].setIcon(provisionIcon);
			break;
		case 3:
			buttons[itemColumn][itemRow].setIcon(rocksIcon);
			break;
		case 4:
			buttons[itemColumn][itemRow].setIcon(monsterIcon);
			break;
		case 5:
			buttons[itemColumn][itemRow].setIcon(pirateIcon);
			break;	
		}
	}
}

