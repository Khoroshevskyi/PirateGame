import java.util.Arrays;
import java.util.Random;

//SquareCreator is generating random places for item in the game.
public class SquareCreator {
	Random rand = new Random(); 
	
	// 0-(treasury) , 1 - provisions,  2- Reefs, 3- Giant Sea monster, 4- Pirates
	// creating array with 	quantity of enemies and another things - 5 levels
	int[][] squareItemsdifficulties = new int[][]{{10, 7, 6, 5, 4}, {6, 6, 6, 6, 6}, {5, 7, 6, 6, 6}, {4, 7, 7, 7, 5},{5, 4, 9, 3, 3}};
	int difficultyLevel;
	private int[][] square;

	//main method for creating square
	public int[][] squreDefinder(int difficultyLevel, int[][] square, int sizeOfSquare, int[] firstPlayerLocation) {		
		int[][] squarecreation = square;
		int[] squareItems = squareItemsdifficulties[difficultyLevel];
		squarecreation = itemsAdding(squarecreation, squareItems, sizeOfSquare, firstPlayerLocation);	
		return squarecreation;
	}
	
	// adding items
	private int[][] itemsAdding(int[][] squarecreation, int[] squareItems, int sizeOfSquare, int[] firstPlayerLocation) {
		// 1-(treasury) , 2 - provisions,  3- Reefs, 4- Giant Sea monster, 5- Pirates
		for(int item = 1; item <= squareItems.length; item++) {
			for(int b = 1; b <= squareItems[item-1]; b++) {
				while (true) {
					int column = rand.nextInt(sizeOfSquare) ;
					int line = rand.nextInt(sizeOfSquare) ;

					if (isEpty(squarecreation, column, line, firstPlayerLocation)) { 
						squarecreation[column][line] = item;						
						break; 
					}
				}
				
			}
		
		}
		squarecreation[firstPlayerLocation[0]][firstPlayerLocation[1]] = 0;
		return squarecreation;
	}
	
	// checking for free place
	private Boolean isEpty(int[][] square,int column,int line, int[] firstPlayerLocation) {
		this.square = square;
		this.square[firstPlayerLocation[0]][firstPlayerLocation[1]] = 8;
		Boolean goodPlace;
		int down = 1;
		int up = -1;
		int left = -1;
		int right = 1;
		
		if (column == 0) {up = 0;}
		if (column == this.square.length-1) {down = 0;}
		if (line == 0) {left = 0;}
		if (line == this.square.length-1) {right = 0;}
		
		// this functions didn't give program to make mistake when this number is not exist
		if (this.square[column][line] != 0 ) {goodPlace = false;}
		else if (this.square[column + up ][line] != 0) {goodPlace = false;}
		else if (this.square[column][line + left] != 0) {goodPlace = false;}
		else if (this.square[column + down ][line] != 0) {goodPlace = false;}
		else if (this.square[column][line + right] != 0) {goodPlace = false;}
		else {goodPlace = true;}
		System.out.println(goodPlace); 
		return goodPlace;
	} 
	
	
}
