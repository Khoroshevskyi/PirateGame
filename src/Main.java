import java.util.Arrays;

//main class that starts everything and makes pre-calculation before opening game visualise class
public class Main {
	
	public static void main(String[] args) {
		new Startwindow();
	}
	
	// start takes lifeValues: 0)start values, 1)difficulty level, 3) Name of the player
	public static void start(String[] lifeValues) {
		
		int startValues = Integer.parseInt(lifeValues[0]);
		int difficultyLevel = Integer.parseInt(lifeValues[1]);
		String playerName = lifeValues[2];
		
		int sizeOfSquare = 15;
		int[][] squaree = new int[sizeOfSquare][sizeOfSquare];
		int[] firstPlayerLocation = new int[] {1,0};
		
		SquareCreator squareBox = new SquareCreator();
		int[][] square = squareBox.squreDefinder(difficultyLevel, squaree, sizeOfSquare, firstPlayerLocation);
		
		String [] winingData = new String[] {playerName, Integer.toString(difficultyLevel)};
		
		new Boxgui(square, sizeOfSquare, firstPlayerLocation, startValues, winingData);
		/*
		for (int i = 1; i <= square.length; i++) {			
			System.out.println(Arrays.toString(square[i-1]));		
		}
		*/
	}
}

