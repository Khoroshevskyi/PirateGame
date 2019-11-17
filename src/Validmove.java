import java.util.ArrayList;
import javax.swing.*;

//checks player of winning and losing + valid move or not 
public class Validmove {
	private int[] startValues;
	
	public Validmove() {
	}
	
	public boolean validation(ArrayList<Integer> columnPos, ArrayList<Integer> rowPos) {
		boolean valid = false;
		int rownew = rowPos.get(rowPos.size()-1);
		int rowlast = rowPos.get(rowPos.size()-2);
		int columnnew = columnPos.get(columnPos.size()-2) ;
		int columnlast = columnPos.get(columnPos.size()-1);
		
		if  (rownew == rowlast || rownew == rowlast+1 || rownew == rowlast-1) {
			if (columnnew == columnlast || columnnew == columnlast - 1 || columnnew == columnlast + 1) {
				valid = true;
			}
		}
		if 	( columnPos.get(columnPos.size()-1) == columnPos.get(columnPos.size()-2) && 
				rowPos.get(rowPos.size()-1) == rowPos.get(rowPos.size()-2)
				) { valid = false; }
		if (!valid) { invalidWindow();}
		return valid;
	}
	
	//Shows window if move is not valid
	private void invalidWindow() {
		String massage = "Upps, this is invalid movement,\n try to go to square near you!";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
	
	//returns boolean value of valid move or not 
	public Validmove(int[] startValues) {
		this.startValues = startValues;
	}
	
	//checks on losing
	public Boolean lose() {
		if (startValues[0] < 0 || startValues[1] < 0 || startValues[2] < 0 ) {
			String massage = "Upps, You Lost! :(";
			JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
			return true;
		} else {
			return false;
		}
	}
	
	//checks on winning
	public Boolean win() {
		if (startValues[3] == 4
				) {
			String massage = "You Won!!! \n"
					+ "All your results were saved \n";
					JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
			return true;
		} else {return false;}
		
	}	
}
