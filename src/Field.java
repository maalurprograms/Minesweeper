import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Diese Klasse generiert die Zellen und Bomben.
 * @author Jonas Cosandey
 */
public class Field {
	
	/**
	 * Array für die Bomben. Werte werden mit der Methode generateBombs hinzugefügt.
	 */
	public final int[] bombs = new int[10];
	
	/**
	 * Zwei dimensionales Array für die Zellen. Diese werden später in der Methode generateCells hinzugefügt.
	 * @see Cell
	 */
	public final Cell[][] cells = new Cell[8][8];
	
	/**
	 * JFrame Objekt dem die Buttons der Zellen zugeordnet werden.
	 * @see JFrame
	 */
	public final JFrame frame = new JFrame();
	
	/**
	 * Konfiguriert das JFrame Objekt und ruft die Methoden zum generieren der Zellen (generateCells) 
	 * sowie der Bomben (gegerateBombs) auf. Ruft ausserdem die Methode setBombInRange auf um für jede Zelle 
	 * die anzahl Bomben in der Umgebung zu bestimmen.
	 * 
	 * @see Cell
	 * @see JFrame
	 */
	public Field() {
		frame.setSize(500,500);
		frame.setLayout(new GridLayout(8,8));;
		
		generateBombs(this.bombs);
		generateCells(this.cells, this.bombs, frame);
		setBombInRange();
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void setBombInRange(){
		int bombsInRange = 0;
		int[][] range = new int[][] {{-1, 1}, {0, 1},{1, 1},{-1, 0},{1, -1},{-1, -1},{0, -1},{1, 0}};
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (!cells[x][y].bomb) {
					for (int i = 0; i < range.length; i++) {
						try {
							if (cells[x + range[i][0]][y + range[i][1]].bomb) {
								bombsInRange += 1;
							}
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					cells[x][y].bombsInRange(bombsInRange);
					bombsInRange = 0;
				}
			}
		}
	}
		
	private void generateBombs(int[] bombs){
		Random random = new Random();
		int randInt;
		int[] alreadyUsed = new int[10];
		boolean isUsed;
		
		for (int i = 0; i < bombs.length; i++) {
			do {
				isUsed = false;
				randInt = random.nextInt(65);
				for (int j = 0; j < alreadyUsed.length; j++) {
					if (randInt == alreadyUsed[j]) {
						isUsed = true;
					}
				}
			} while (isUsed);

			System.out.println(randInt);
			bombs[i] = randInt;
		}
	}
	
	private void generateCells(Cell[][] cells, int[] bombs, JFrame frame){
		int cellID = 1;
		boolean isBomb;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells.length; y++) {
				isBomb = checkCell(cellID);
				if (isBomb) {
					cells[x][y] = new Cell(cellID, true, frame);
				} else {
					cells[x][y] = new Cell(cellID, false, frame);
				}
				cellID += 1;
			}
		}
	}
	
	private boolean checkCell(int cellID){
		boolean isBomb = false;
		for (int i = 0; i < bombs.length; i++) {
			if (cellID == bombs[i]) {
				isBomb = true;
				break;
			} else {
				isBomb = false;
			}
		}
		return isBomb;
	}
}
