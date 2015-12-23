
public class Cell {
	public final int cellID;
	public final boolean bomb;
	public boolean marked;
	public boolean revealed;
	
	public Cell(int cellID, boolean bomb) {
		this.cellID = cellID;
		this.bomb = bomb;
	}
	
	public boolean mark(boolean changeState) {
		if (changeState) {
			if (marked) {
				marked = false;
			} else {
				marked = true;
			}
			return marked;
		} else {
			return marked;
		}
	}
	
	public void reveal() {
		if (!revealed) {
			revealed = true;
		} else {
			System.out.println("Bereits aufgedeckt");
		}
	}
}
