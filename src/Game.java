import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Zuständig für den Start des Spiels und die interkation mit User.
 * @author Jonas Cosandey
 */
public class Game {
	
	/**
	 * Feld Objekt das im Konstruktor nun alle Zellen generiert hat und das Spielfeld vorbereitet hat.
	 * @see Field
	 */
	private Field field = new Field();
	
	/**
	 * Zwei dimensionales Array das in der Klasse Field generiert wurde.
	 * @see Field
	 * @see Cell
	 */
	private Cell[][] cells = field.cells;
	
	/**
	 * Anzahl bereits aufgedeckter Zellen damit bestimmt werden kann, wann der Spieler alle Zellen (Ohne Bomben) aufgedeckt hat.
	 */
	private int countRevealedCells = 0;
	
	/**
	 * Für jede Zelle wird die Methode setNouseListener aufgerufen,
	 * die ein MouseListener für die Linke Und Rechte taste sezt.
	 * @param args
	 * @see MouseAdapter
	 * @see MouseEvent
	 * @see InputEvent
	 */
	public static void main(String[] args) {
		Game game = new Game(); 
		for (int x = 0; x < game.cells.length; x++) {
			for (int y = 0; y < game.cells.length; y++) {
				game.setMouseListener(game.cells[x][y]);
			}
		}		
	}
	
	private void setMouseListener(final Cell cell) {
		cell.button.addMouseListener (new MouseAdapter(){
		    public void mouseClicked(MouseEvent mouseEvent){
		    	int modifiers = mouseEvent.getModifiers();
		        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		        	reveal(cell);
		        }
		        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		        	cell.mark();
		        }
		    }
		});
	}
	
	private void reveal(Cell cell) {
		if (!cell.reveal(true)) {
			cell.reveal(false);
			cell.button.setBackground(Color.WHITE);
			if (!cell.bomb) {
				if (cell.bombsInRange(null) == 0) {
					revealAllZero(cell);
				} else {
					cell.button.setText(String.valueOf(cell.bombsInRange(null)));
				}
				countRevealedCells += 1;
				cell.setFontColor();
				if (countRevealedCells == ((cells.length * cells.length) - field.bombs.length)) {
					end(true);
				}
			} else {
				end(false);
			}
		}
	}
	
	private void revealAllZero(Cell cell) {
		int[][] range = new int[][] {{-1, 1}, {0, 1},{1, 1},{-1, 0},{1, -1},{-1, -1},{0, -1},{1, 0}};
		Cell cellInRange;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (cells[x][y].cellID == cell.cellID) {
					for (int i = 0; i < range.length; i++) {
						try {
							cellInRange = cells[x + range[i][0]][y + range[i][1]];
							if (cellInRange.bombsInRange(null) == 0) {
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
	
	private void end(boolean win) {
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
					continue;
				}
			}
		}
	}
}