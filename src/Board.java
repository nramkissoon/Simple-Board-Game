import java.util.ArrayList;

/* This Board Class is a Doubly Linked List 
 * Methods present in a standard Doubly Linked List such as addLast(), first(), etc. have been removed for the sake 
 * of readability and because the Board is strictly defined in the assignment and there is no need to have
 * these methods given that the board in constructed correctly upon instantiation*/
public class Board {

		// BoardTile Class is "Node" Class----------------------------------------------------------------------------
	  private static class BoardTile { 
	
	    private int value;  // value of tile 
	    private ArrayList<Player> occupants = new ArrayList<Player>(); // we need to know who is on the tile 
	    
	    // previous and next tile pointers
	    private BoardTile prev;            
	    private BoardTile next;           
	
	    // Constructor 
	    public BoardTile(int v, BoardTile p, BoardTile n) {
	      value = v;
	      prev = p;
	      next = n;
	    }
	    
	    // Public methods for accessing private properties, similar to standard Node class
	    public ArrayList<Player> getOccupants() { return occupants; }
	    public int getValue() { 
	    	if (value == 100) { 
	    		return 0;
	    	}
	    	return value; 
	    	}
	
	    public BoardTile getPrev() { return prev; }
	    public BoardTile getNext() { return next; }
	
	    public void setPrev(BoardTile p) { prev = p; }
	    public void setNext(BoardTile n) { next = n; }
	    
	    public void addOccupant(Player n) { occupants.add(n); } // adds player to occupant list when they land on tile
	    public void removeOccupant(Player n) { occupants.remove(n); } // removes player from occupant list when the player moves off tile
	    
	    // A tile can display its value and current occupants
	    public String toString() {
	    	String occ = getOccupants().toString().substring(1, getOccupants().toString().length() - 1); // this is why Python is better :)
	    	if (value == 0) {
	    		return "[ Start: " + occ + " ]";
	    	}
	    	if (value == 100) {
	    		return "[ End: " + occ + " ]";
	    	}
	    	return "[ " + getValue() + ": " + occ + " ]";
	    }
	  }  // End of BoardTile Class ----------------------------------------------------------------------------

  
  private BoardTile startTile;  // "header"                  

  private BoardTile endTile;   // "trailer"              
              

  //Constructor
  public Board() {
    startTile = new BoardTile(0, null, null);      // value of 0 to indicate start
    endTile = new BoardTile(100, startTile, null);   // arbitrary value of 100 to indicate end
    startTile.setNext(endTile); 
    
    // List of integers that are given in assignment description
    int[] values = {5, 10, 8, 10, 7, 5, 9, 10, 6, 7, 10, 6, 5, 8, 9, 5, 10, 5 ,9, 6, 8, 7, 10, 6, 8};
    for (int i = 0; i < values.length; i++) {
    	addLast(values[i]); // construct board 
    }
  }
  
  // Method for placing all players on start tile at the beginning of a game
  public void initializeBoard(ArrayList<Player> p ) {  
	  for (int i = 0; i < p.size(); i++) {
		  startTile.addOccupant(p.get(i));
	  }
  }

  // Method for moving a player a set number of tiles
  /*The board moving the players makes more sense in this game because:
   * 1. game is chance based and players have no autonomy anyways
   * 2. if the Player class contained the move method then each player would have to keep
   * 	track of their own Board property, this is inefficient because all of these Boards will have to be in sync
   * 	for each move for each player.  */
  public void movePlayer(Player p, int roll) {
	  BoardTile walk = startTile;
	  while (!walk.occupants.contains(p)) { // loop to find player p
		  walk = walk.getNext(); 
	  }
	  walk.removeOccupant(p); // remove player p from occupant list of tile since it will now be moved
	  for (int i = 0; i < roll; i++) { // walk through board to find where player will go
		  if (walk == endTile && !p.above44) { 
			  walk = startTile;
			  break; 
			  }
		  else if (walk == endTile && p.above44){
			  walk = endTile;
			  break;
		  }
		  walk = walk.getNext();
	  }
	  
	  // if a player lands on an unoccupied tile
	  if (walk.getOccupants().isEmpty()) { 
		  walk.addOccupant(p);
		  p.addScore(walk.getValue());
	  }
	  
	  // if a player lands on an occupied tile, follow rules given in assignment description
	  else if (!walk.getOccupants().isEmpty()) {
		  for (int i = 0; i < walk.getOccupants().size(); i++) { 
			  Player j = walk.getOccupants().get(i);
			  movePlayerBack7(j, walk); // get all current occupants and move them back 7 tiles
		  }
		  walk.addOccupant(p); 
		  p.addScore(walk.getValue());
	  }

  }
  
  // method for moving a player back 7 tiles
  private void movePlayerBack7(Player p, BoardTile a) {
	  a.removeOccupant(p);
	  BoardTile walk = a;
	  for (int i = 0; i < 7; i++) {
		  if (walk == startTile) {
			 break;
		  }
		  walk = walk.prev;
	  }
	  walk.addOccupant(p);
  }
  
  private void addLast(int e) {
    addBetween(e, endTile.getPrev(), endTile);  
  }

  
  private void addBetween(int e, BoardTile predecessor, BoardTile successor) {
    BoardTile newest = new BoardTile(e, predecessor, successor);
    predecessor.setNext(newest);
    successor.setPrev(newest);
  }
  
  // this method is needed to check for potential winners of the game 
  public ArrayList<Player> getEndTile(){
	  return endTile.getOccupants();
  }

  public String toString() { 
    StringBuilder sb = new StringBuilder("");
    BoardTile walk = startTile;
    int printCount = 1; // keeps track of line break
    while (walk != endTile) {
      sb.append(walk.toString());
      walk = walk.getNext();
      if (walk.next != null)
        sb.append(" -> ");
     if (printCount == 10 || printCount == 17) {
  		sb.append("\n\n");
  	} 
      printCount++;
    }
    sb.append(" --> ");
    sb.append(walk.toString());
    sb.append("");
    return sb.toString();
  }
} 