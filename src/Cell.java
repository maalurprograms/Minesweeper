import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

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
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
		        int modifiers = mouseEvent.getModifiers();
		        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		        	reveal();
		        }
		        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		        	mark();
		        }
			}
		});
		layout.add(button);  
	}
	
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
	
	public void reveal() {
		if (!revealed) {
			revealed = true;
			button.setBackground(Color.WHITE);
			button.setText(String.valueOf(bombsInRange));
			setFontColor();
		}
	}
	
	private void setFontColor(){
		switch (bombsInRange) {
		case 0:
			
			break;
		case 1:
			button.setForeground(Color.RED);
			break;
		case 2:
			button.setForeground(Color.RED);
			break;
		case 3:
			button.setForeground(Color.RED);
			break;
		case 4:
			button.setForeground(Color.RED);
			break;
		case 5:
			button.setForeground(Color.RED);
			break;
		case 6:
			button.setForeground(Color.RED);
			break;
		case 7:
			button.setForeground(Color.RED);
			break;
		case 8:
			button.setForeground(Color.RED);
			break;
		}
	}
}
