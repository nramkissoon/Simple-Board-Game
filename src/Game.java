import java.util.ArrayList;

// Class for individual Game
public class Game {
	private ArrayList<Player> players;
	private Player winner = null;
	private int numTurns = 0;
	private Board board;
	
	// Constructor
	public Game(ArrayList<Player> p, Board b) {
		players = p;
		board = b;
		board.initializeBoard(players); // Places players at start
		}
	
	// Method to complete 1 turn
	public void doATurn() {
		for (int i = 0; i < players.size(); i++) {
			Player guy = players.get(i);
			board.movePlayer(guy, guy.rollDie());
			checkWinner();
			if (!(winner == null)) {
				break; // need not continue loop if someone has won
			}
		}
	}
	
	// Player take turns until there is a winner
	public void playToCompletion() {
		while (winner == null) {
			doATurn();
			numTurns ++;
		}
		for (Player p: players) {
			p.reset(); // reset scores in case Player will be used for another game
		}
	}
	
	// Method for checking is a winner exists
	private void checkWinner() {
		ArrayList<Player> potentialWinners = board.getEndTile(); // gets players at the end tile
		if (potentialWinners.isEmpty()) {
			return;
		}
		for (int i = 0; i < potentialWinners.size(); i++) {
			Player p = potentialWinners.get(i);
			if (p.above44) { // checks if player score is above 44
				winner = p;
				return;
				}
		}
		return;
	}
	

	
	public Player getWinner() { return winner; }
	public Board getBoard() { return board; }
	public ArrayList<Player> getPlayers() { return players; } 
	public int getNumTurns() { return numTurns; } 
	
	public String printGameStatus() {
		return board.toString();
	}
	
	
}
