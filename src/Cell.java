import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
	Die Klasse Cell verwalted den Zustand der Zelle
*/

public class Cell {
	public final int cellID;
	public final boolean bomb;
	public boolean marked;
	public boolean revealed;
	public final JButton button = new JButton();
	public int bombsInRange;
	
	public Cell(int cellID, boolean bomb, JFrame layout) {
		this.cellID = cellID;
		this.bomb = bomb;
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		layout.add(button);  
	}
	
	public void setFontColor(){
		switch (bombsInRange) {
		case 0:
			button.setForeground(Color.decode("#00cc00"));
			break;
		case 1:
			button.setForeground(Color.decode("#00cc66"));
			break;
		case 2:
			button.setForeground(Color.decode("#00cccc"));
			break;
		case 3:
			button.setForeground(Color.decode("#0066cc"));
			break;
		case 4:
			button.setForeground(Color.decode("#0000cc"));
			break;
		case 5:
			button.setForeground(Color.decode("#6600cc"));
			break;
		case 6:
			button.setForeground(Color.decode("#cc00cc"));
			break;
		case 7:
			button.setForeground(Color.decode("#cc0066"));
			break;
		case 8:
			button.setForeground(Color.decode("#cc0000"));
			break;
		}
	}
}
