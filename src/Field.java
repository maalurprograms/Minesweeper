import java.util.Random;

public class Field {
	private int[] bombs;
	private Cell[][] cells;
	
	public Field() {
		this.bombs = generateBombs();
		this.cells = generateCells();
	}
	
	private static int[] generateBombs(){
		Random random = new Random();
		int[] bombs = new int[10];
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
		return bombs;
	}
	
	private static Cell[][] generateCells(){
		int cellID = 1;
		Cell[][] cells = new Cell[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				cells[x][y] = new Cell(cellID);
				cellID += 1;
			}
		}
		return cells;
	}
}
