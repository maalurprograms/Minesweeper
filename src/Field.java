import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JFrame;

public class Field {
	public final int[] bombs = new int[10];
	public final Cell[][] cells = new Cell[8][8];
	public final JFrame frame = new JFrame();
	public int countRevealedCells = 0;
	
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
					cells[x][y].bombsInRange = bombsInRange;
					bombsInRange = 0;
				}
			}
		}
	}
		
	private void generateBombs(int[] bombs){
		Random random = new Random();
		int randInt;
		int[] alreadyUsed = new int[10];
		boolean isUsed = false;
		
		for (int i = 0; i < bombs.length; i++) {
			randInt = random.nextInt(65);
			for (int j = 0; j < alreadyUsed.length; j++) {
				if (randInt == alreadyUsed[j]) {
					isUsed = true;
				}
			}
			if (!isUsed) {
				bombs[i] = randInt;
			} else {
				i -= 1;
				isUsed = false;
			}
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
