import java.util.Random;

public class Spiel {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] bomben = generateBombs();
		System.out.println("OK");
	}
	
	private static int[] generateBombs(){
		Random random = new Random();
		int[] bomben = new int[10];
		int randInt;
		int[] alreadyUsed = new int[10];
		boolean isUsed = false;
		
		for (int i = 0; i < bomben.length; i++) {
			randInt = random.nextInt(64);
			for (int j = 0; j < alreadyUsed.length; j++) {
				if (randInt == alreadyUsed[j]) {
					isUsed = true;
				}
			}
			if (!isUsed) {
				bomben[i] = randInt;
			} else {
				i -= 1;
				isUsed = false;
			}
		}
		return bomben;
	}
}
