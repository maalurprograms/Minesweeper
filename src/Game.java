import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Game {
	public static void main(String[] args) {
		Field field = new Field();
		
		for (int x = 0; x < field.cells.length; x++) {
			for (int y = 0; y < field.cells.length; y++) {
				setMouseListener(field.cells[x][y], field);
			}
		}		
	}
	
	public static void setMouseListener(Cell cell, Field field) {
		cell.button.addMouseListener (new MouseAdapter(){
		    public void mouseClicked(MouseEvent mouseEvent){
		    	int modifiers = mouseEvent.getModifiers();
		        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		        	reveal(cell, field);
		        }
		        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		        	mark(cell);
		        }
		    }
		});
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
	
	public static void reveal(Cell cell, Field field) {
		Cell[][] cells = field.cells;
		if (!cell.revealed) {
			cell.revealed = true;
			cell.button.setBackground(Color.WHITE);
			if (!cell.bomb) {
				if (cell.bombsInRange == 0) {
					revealAllZero(field, cell);
				} else {
					cell.button.setText(String.valueOf(cell.bombsInRange));
				}
				field.countRevealedCells += 1;
				cell.setFontColor();
				if (field.countRevealedCells == ((cells.length * cells.length) - field.bombs.length)) {
					win(cells);
				}
			} else {
				lose(cells);
			}
		}
	}
	
	public static void revealAllZero(Field field, Cell cell) {
		Cell[][] cells = field.cells;
		int[][] range = new int[][] {{-1, 1}, {0, 1},{1, 1},{-1, 0},{1, -1},{-1, -1},{0, -1},{1, 0}};
		Cell cellInRange;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (cells[x][y].cellID == cell.cellID) {
					for (int i = 0; i < range.length; i++) {
						try {
							cellInRange = cells[x + range[i][0]][y + range[i][1]];
							if (cellInRange.bombsInRange == 0) {
								reveal(cellInRange, field);
							} else if (cellInRange.bomb == false) {
								reveal(cellInRange, field);
							}
						} catch (Exception e) {

						}
					}
				}
			}
		}
	}
	
	public static void win(Cell[][] cells) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				try {
					if (cells[x][y].bomb) {
						cells[x][y].button.setBackground(Color.GREEN);
						cells[x][y].button.setText("X");
					} else {
						cells[x][y].button.setBackground(Color.WHITE);
						cells[x][y].button.setText(null);
					}
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public static void lose(Cell[][] cells) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				try {
					if (cells[x][y].bomb) {
						cells[x][y].button.setBackground(Color.RED);
					} else {
						cells[x][y].button.setBackground(Color.WHITE);
						cells[x][y].button.setText(null);
					}
				} catch (Exception e) {
					
				}
			}
		}
	}
}
