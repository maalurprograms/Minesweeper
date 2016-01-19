import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
	Die Klasse Cell verwalted den Zustand der Zelle
	@author Jonas Cosandey
*/
public class Cell {
	
	/**
	 * ID die beim generieren der Zellen in der Klasse Field verteilt wird.
	 * @see Field
	 */
	public final int cellID;
	
	/**
	 * Beim generieren der Zellen in der Klasse Field wird festgelegt welche Zellen Bomben sind.
	 * @see Field
	 */
	public final boolean bomb;
	
	/**
	 * Button der dann über die Klasse Game kontrolliert wird.
	 * @see Game
	 */
	public final JButton button = new JButton();
	private boolean marked;
	private boolean revealed;
	private int bombsInRange;
	
	/**
	 * Es werden die Schriftart, Schriftstil und Schriftgrösse für den Button festgelegt
	 * und dieser dann zum JFrame element hinzugefügt.
	 * Ausserdem werden die Werte der Klassenvariabeln CellID und bomb von ihren gleichnamigen Parametern überschrieben.
	 * @param cellID
	 * @param bomb 
	 * @param layout 
	 * @see JButton	
	 */
	public Cell(int cellID, boolean bomb, JFrame layout) {
		this.cellID = cellID;
		this.bomb = bomb;
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		layout.add(button);  
	}
	
	/**
	 * Diese Methode gibt die Klassen Variabel bombsInRange zurück und falls als Parameter eine Zahl übergeben wird, 
	 * wird der Klassen Variabel bombsInRange diesen Wert gegeben.
	 * @param bombsInRange
	 * @return Gibt die Klassen Variabel bombsInRange zurück.
	 * @see Integer
	 */
	public int bombsInRange(Integer bombsInRange){
		if(bombsInRange != null) {
			this.bombsInRange = bombsInRange;
		}
		return this.bombsInRange;
	}
	
	/**
	 * Wenn getInfo auf True gesetzt ist, wird die Klassen Variabel revealed auf True gesetzt.
	 * @param getInfo Wenn True wird nur der Wert von revealed zurückgegeben. Bei False wird revealed auf True gesetzt.
	 * @return Gibt die Klassen Variabel revealed zurück.
	 */
	public boolean reveal(boolean getInfo){
		if (!getInfo) {
			revealed = true;
		}
		return revealed;
	}
	
	/**
	 * Wenn die Zelle noch nicht Markiert wurde, also marked auf False gesezt ist, wird sie markiert. 
	 * Also marked auf True gesetzt.
	 * Beim "markieren" wird die Button Farbe auf Rot gesezt, und beim "entmarkieren" auf Standart.
	 * @see JButton
	 */
	public void mark() {
		if (!revealed) {
			if (marked) {
				marked = false;
				button.setBackground(new JButton().getBackground());
			} else {
				marked = true;
				button.setBackground(Color.RED);
			}
		}
	}
	
	/**
	 * Die Schriftfarbe des Buttons wird je nach anzahl Bomben in der nähe gefärbt.
	 * @see JButton
	 */
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
