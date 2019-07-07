import java.util.ArrayList;
import java.util.HashMap;

// Class for grouping a set of games with similar characteristics and generating statistics for those games
public class GameSet {
	private String id; // used for readability in final output
	private int numGames;
	private ArrayList<Player> players;
	private HashMap<String, Float> winPercentage; // Dictionary for storing win percentage for each player
	private HashMap<String, Float> AvgTurnstoWin; // Stores average turns it takes each player to win
	
	// Constructor
	public GameSet(int i, ArrayList<Player> p) {
		id = i + " GAMES WITH " + p.size() + " PLAYERS: " + p.toString();
		numGames = i;
		players = p;
		winPercentage = new HashMap<String, Float>();
		AvgTurnstoWin = new HashMap<String, Float>();
		for (int j = 0; j < players.size(); j++) {
			winPercentage.put(players.get(j).name, (float) 0.0);
			AvgTurnstoWin.put(players.get(j).name, (float) 0.0);
		}
	}
	
	// method to play i number of games specified at instantiation
	public void playThroughSet() {
		for (int i = 0; i < numGames; i++) {
			Board board = new Board();
			Game g = new Game(players, board);
			g.playToCompletion();
			if (i % 100 == 0) { // print every 100th game 
				printBoard(g, i);
				}
			String winner = g.getWinner().name;
			winPercentage.put(winner, (winPercentage.get(winner) + 1)); // total wins 
			AvgTurnstoWin.put(winner, AvgTurnstoWin.get(winner) + g.getNumTurns()); // this will be the TOTAL turns taken by each player every time they won
		}
		
		// Following loops are for creating statistics
		for (int i = 0; i < players.size(); i++) {
			String n = players.get(i).name;
			if (winPercentage.get(n) != 0) {
				AvgTurnstoWin.put(n, AvgTurnstoWin.get(n) / winPercentage.get(n)); // Calculate Average turns
			}
		}
		for (int i = 0; i < players.size(); i++) {
			String n = players.get(i).name;
			winPercentage.put(n, (winPercentage.get(n)*100/numGames)); // calculate win percentages for each player
		}
	}
	
	// return string containing all relevant statistics for each player 
	public String getStats() {
		String stats = "\nGAME SET STATISITICS FOR: " + id + "\n";
		for (int i = 0; i < players.size(); i++) {
			String n = players.get(i).name;
			stats += n + ": Win % = " + winPercentage.get(n) + "%, Average Turns = " + AvgTurnstoWin.get(n) + "\n";
		}
		return stats;
	}
	
	// method for returning statistics in tabular form required by assignment
	public String getStatsRowForm() {
		String stats = "";
		stats += players.toString();
		int offset = 12 - players.size() * 3; //very specific for displaying up to four players
		for (int i = 0; i < offset; i++) {
			stats += " ";
		}
		for (int i = 0; i < players.size(); i++) {
			String n = players.get(i).name;
			Float turns = AvgTurnstoWin.get(n); 
			String s;
			if (turns.toString().length() >= 4) {
				s = turns.toString().substring(0, 4); // "rounding"
			}
			else {
				s = turns.toString();
			}
			stats += "        " + winPercentage.get(n) + "/" + s;
		}
		return stats;
	}
	
	
	// Printing method to be used ever 100 games
	private void printBoard(Game g, int i) {
		System.out.println("_________________________________________________________"
				+ "_______________________________________________________________"); // Python > Java :)
		System.out.println();
		System.out.println("GAME #" + (i+1) + " OF " + id);
		System.out.println();
		System.out.println(g.printGameStatus());
	}
	
	public String getId() { return id; }
	
}

