import java.util.ArrayList;

public class CallingClass {
	public static void main(String[] args) {
		
		// Instantiate Players and Player lists ------------------------------------------------------------------------------
		
		Player a = new Player("A");
		Player b = new Player("B");
		Player c = new Player("C");
		Player d = new Player("D");
		

		ArrayList<Player> onePlayer = new ArrayList<Player>();
		onePlayer.add(a);
		
		ArrayList<Player> twoPlayers = new ArrayList<Player>();
		twoPlayers.add(a); twoPlayers.add(b);
		
		ArrayList<Player> threePlayers = new ArrayList<Player>();
		threePlayers.add(a); threePlayers.add(b); threePlayers.add(c);

		ArrayList<Player> fourPlayers = new ArrayList<Player>();
		fourPlayers.add(a); fourPlayers.add(b); fourPlayers.add(c); fourPlayers.add(d);

		
		//----------------------------------------------------------------------------------------------------------------------

		// Instantiate 4 Game Sets ---------------------------------------------------------------------------------------------
		
		int num = 1000;
		
		GameSet one = new GameSet(num, onePlayer);
		GameSet two = new GameSet(num, twoPlayers);
		GameSet three = new GameSet(num, threePlayers);
		GameSet four = new GameSet(num, fourPlayers);
		
		
		//----------------------------------------------------------------------------------------------------------------------
		
		// Play Through each set, printing the required boards and data at the end of each set----------------------------------
		
		// Set one
		System.out.println("\n" + one.getId() + "\n");
		one.playThroughSet();
		System.out.println(one.getStats());
		printADivider1(); printADivider2(); 
		
		// Set two
		System.out.println("\n" + two.getId() + "\n");
		two.playThroughSet();
		System.out.println(two.getStats());
		printADivider1(); printADivider2();  
		
		// Set three
		System.out.println("\n" + three.getId() + "\n");
		three.playThroughSet();
		System.out.println(three.getStats());
		printADivider1(); printADivider2(); 
		
		// Set four
		System.out.println("\n" + four.getId() + "\n");
		four.playThroughSet();
		System.out.println(four.getStats());
		printADivider1(); printADivider2(); 
		

		//----------------------------------------------------------------------------------------------------------------------
		
		// Print the statistics from each set-----------------------------------------------------------------------------------
		
		System.out.println("\n");
		System.out.println(one.getStats());
		System.out.println(two.getStats());
		System.out.println(three.getStats());
		System.out.println(four.getStats());

		printStatTableHeaders();
		System.out.println(one.getStatsRowForm());
		System.out.println(two.getStatsRowForm());
		System.out.println(three.getStatsRowForm());
		System.out.println(four.getStatsRowForm());
		
		
		//----------------------------------------------------------------------------------------------------------------------
		
		// Testing code (Uncomment to test)-------------------------------------------------------------------------------------
		/* 
		printADivider1(); printADivider1();
		System.out.println("\nTESTS\n");
		
		Board board = new Board();
		Game testGame = new Game(fourPlayers, board);
		System.out.println(testGame.printGameStatus()); //Board and players are setup correctly
		
		testGame.getBoard().movePlayer(a, 4); //Place Player A on a tile with number 10
		System.out.println("\n" + "A's score should be 10: " + a.getScore()); // Verify that Score is 10
		
		testGame.getBoard().movePlayer(c, 4); //Place Player C where A is, move A back 7 (to Start), C and A score should be 10
		System.out.println("\n" + testGame.printGameStatus()); 
		System.out.println("\n" + "A's score should be 10: " + a.getScore()); 
		System.out.println("\n" + "C's score should be 10: " + c.getScore()); 
		
		testGame.getBoard().movePlayer(a, 6);  // Place Player A on tile with number 5
		System.out.println("\n" + testGame.printGameStatus()); 
		System.out.println("\n" + "A's score should be 15: " + a.getScore()); 
		
		testGame.getBoard().movePlayer(a, 1000);  // Place Player A on tile beyond endTile so should be at start
		System.out.println("\n" + testGame.printGameStatus()); 
		System.out.println("\n" + "A's score should be 15: " + a.getScore()); 
		
		testGame.getBoard().movePlayer(a, 2); 
		testGame.getBoard().movePlayer(a, 6); 
		testGame.getBoard().movePlayer(a, 6); 
		System.out.println("\n" + testGame.printGameStatus()); 
		System.out.println("\n" + "A's score should be 43: " + a.getScore()); // A is in position to win
		testGame.getBoard().movePlayer(a, 2); // A is in position to win
		System.out.println("\n" + "A's score should be 48: " + a.getScore());
		System.out.println("Is A's score > 44: " + a.above44);
		
		testGame.getBoard().movePlayer(a, 1000); // move A beyond end, should be placed at end now that A has won
		*/
	}
	
	private static void printADivider1() {
		System.out.println("_________________________________________________________"
				+ "_______________________________________________________________");
	}
	
	private static void printADivider2() {
		System.out.println("#########################################################"
				+ "###############################################################");
		System.out.println("#########################################################"
				+ "###############################################################");
	}
	
	private static void printStatTableHeaders() {
		System.out.println();
		System.out.println("STATISTICS IN TABLE FORM:");
		System.out.println("                               Win Rate (%) / Average Turns to Win");
		System.out.println("Players                 A                B               C              D");
	}
	
}
