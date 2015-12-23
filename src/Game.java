import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Game {
	public static void main(String[] args) {
		Field field = new Field();
		
		for (int x = 0; x < args.length; x++) {
			for (int y = 0; y < args.length; y++) {
				field.cells[x][y].button.addMouseListener(new MouseAdapter() mousePressed(mouseEvent, field.cells[x][y]);
			}
		}		
	}
	
	public static void mousePressed(MouseEvent mouseEvent, Cell cell) {
        int modifiers = mouseEvent.getModifiers();
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
        	reveal(cell);
        }
        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
        	mark(cell);
        }
	}
	
	public static void mark(Cell cell) {
		if (!cell.revealed) {
			if (cell.marked) {
				cell.marked = false;
				cell.button.setBackground(new JButton().getBackground());
			} else {
				cell.marked = true;
				cell.button.setBackground(Color.RED);
			}
		}
	}
	
	public static void reveal(Cell cell) {
		if (!cell.revealed) {
			cell.revealed = true;
			cell.button.setBackground(Color.WHITE);
			if (!cell.bomb) {
				cell.button.setText(String.valueOf(cell.bombsInRange));
				cell.setFontColor();
			} else {
				cell.button.setText(String.valueOf("X"));
			}
		}
	}
}
