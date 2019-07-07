
import java.util.Random;

// Class to keep track of individual player data
public class Player {
	private int score = 0; 
	public boolean above44 = false;
	public String name;

	
	public Player(String n) {
		name = n;
		
	}
	
	public void addScore(int i) {
		score += i;
		if (score >= 44) { above44 = true; }
	}
	

	public int rollDie() { 
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
	public void reset() { score = 0; above44 = false; } // needed if we are reusing a Player Object for another game 
	public int getScore() { return score; }
	public String toString() { return name; }
	
}
