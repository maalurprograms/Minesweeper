import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Game {
	
	static final Field field = new Field();
	static final Cell[][] cells = field.cells;
	
	public static void main(String[] args) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				setMouseListener(cells[x][y]);
			}
		}		
	}
	
	private static void setMouseListener(final Cell cell) {
		cell.button.addMouseListener (new MouseAdapter(){
		    public void mouseClicked(MouseEvent mouseEvent){
		    	int modifiers = mouseEvent.getModifiers();
		        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		        	reveal(cell);
		        }
		        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		        	mark(cell);
		        }
		    }
		});
	}
	
	private static void mark(Cell cell) {
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
	
	private static void reveal(Cell cell) {
		if (!cell.revealed) {
			cell.revealed = true;
			cell.button.setBackground(Color.WHITE);
			if (!cell.bomb) {
				if (cell.bombsInRange == 0) {
					revealAllZero(cell);
				} else {
					cell.button.setText(String.valueOf(cell.bombsInRange));
				}
				field.countRevealedCells += 1;
				cell.setFontColor();
				if (field.countRevealedCells == ((cells.length * cells.length) - field.bombs.length)) {
					end(true);
				}
			} else {
				end(false);
			}
		}
	}
	
	private static void revealAllZero(Cell cell) {
		int[][] range = new int[][] {{-1, 1}, {0, 1},{1, 1},{-1, 0},{1, -1},{-1, -1},{0, -1},{1, 0}};
		Cell cellInRange;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (cells[x][y].cellID == cell.cellID) {
					for (int i = 0; i < range.length; i++) {
						try {
							cellInRange = cells[x + range[i][0]][y + range[i][1]];
							if (cellInRange.bombsInRange == 0) {
								reveal(cellInRange);
							} else if (cellInRange.bomb == false) {
								reveal(cellInRange);
							}
						} catch (Exception e) {

						}
					}
				}
			}
		}
	}
	
	private static void end(boolean win) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				try {
					if (cells[x][y].bomb) {
						if (win) {
							cells[x][y].button.setBackground(Color.GREEN);
						} else {
							cells[x][y].button.setBackground(Color.RED);
						}

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