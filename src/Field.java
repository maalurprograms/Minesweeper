import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;

public class Field {
	private int[] bombs = new int[10];
	private Cell[][] cells = new Cell[8][8];
	public final JFrame frame = new JFrame();
	
	public Field() {
		frame.setSize(500,500);
		GridLayout layout = new GridLayout(8,8);
		frame.setLayout(layout);;
		
		generateBombs(this.bombs);
		generateCells(this.cells, this.bombs, frame);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		setBombInRange();
	}
	
	private void setBombInRange(){
		int[][] range = new int[8][2];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				for (int i = 0; i < range.length; i++) {
					
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
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				boolean isBomb = checkCell(cellID);
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
			} else {
				isBomb = false;
			}
		}
		return isBomb;
	}
}
