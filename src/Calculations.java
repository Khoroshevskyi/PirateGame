import javax.swing.JOptionPane;

// makes calculations for Boxgui and shows what player has found
public class Calculations {
	
	public int[] startValues;
	private Boolean helpWindow;
	
	//0 - provision; 1 - guns; 2- protection;
	
	public Calculations(int[] startValues, Boolean helpWindow) {
		this.startValues = startValues;
		this.startValues[0] = startValues[0] - 1;
		this.helpWindow = helpWindow;
	}
	public int[] zero() {
		return startValues;
	}
	
	// we found treasury
	public int[] one() {
		startValues[3] = startValues[3] + 1;
		if (helpWindow) {
			treasury();
		}
		return startValues;
	}
	
	// we found provision
	public int[] two() {
		startValues[0] = startValues[0] + 5;
		startValues[1] = startValues[1] + 3;
		startValues[2] = startValues[2] + 3;
		if (helpWindow) {
			provision();
		}
		return startValues;
	}
	
	//reefs
	public int[] three() {
		startValues[2] = startValues[2] - 1;
		if (helpWindow) {
			reefs();
		}
		return startValues;
	}
	
	//sea monster
	public int[] four() {
		startValues[0] = startValues[0] + 2;
		startValues[1] = startValues[1] - 2;
		startValues[2] = startValues[2] - 2;
		if (helpWindow) {
			monster();
		}
		return startValues;
	}
	public int[] five() {
		startValues[0] = startValues[0] + 2;
		startValues[1] = startValues[1] + 2;
		startValues[2] = startValues[2] - 4;
		if (helpWindow) {
			pirate();
		}
		return startValues;
	}
	
	private void treasury() {
		
		String massage = "You found treasury - congratulations!";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
	private void provision() {
		
		String massage = "You found provision - congratulations,\n You get : \n -->5 provision \n --> 3 guns \n --> 3 protection";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
		
	}
	private void reefs() {
		
		String massage = "Uups, You step on the reefs, \n - 1 protection :/";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
	private void monster() {
		
		String massage = "You have to fight with sea monster. Your values are modified: \n provision +2 \n guns -2 \n protections -2";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
	private void pirate() {
		
		String massage = "You have to fight with another pirate. Your values are modified: \n provision +2 \n guns +1 \n protections -4";
		JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
	}
	
}
