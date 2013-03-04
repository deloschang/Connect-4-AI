


/** 
 * View for Connect 4 game (in model-view-controller pattern)
 * Displays the game and interacts with the user
 * @author Scot Drysdale
 */
public interface Connect4View {

  /**  
   * Displays the current board
   * @param state current state of the game
   */
	public void display (Connect4State state);
	
	/**
	 * Asks the user for a move
	 * The move will be in the range 0 to Connect4State.COLS-1.
	 * @param state current state of the game
	 * @return the number of the move that player chose
	 */
	public int getUserMove(Connect4State state);

	/**
	 * Reports the move that a player has made.
	 * The move should be in the range 0 to Connect4State.COLS-1.
	 * @param chosenMove the move to be reported
	 * @param name the player's name
	 */
	public void reportMove (int chosenMove, String name);
  
	/**
	 * Ask the user the question and return the answer as an int
	 * @param question the question to ask
	 * @return The depth the player chose
	 */
  public int getIntAnswer (String question);
  
  /**
   * Convey a message to user
   * @param message the message to be reported
   */
  public void reportToUser(String message);
  
  /**
   * Ask the question and return the answer
   * @param question the question to ask
   * @return the answer to the question
   */
  public String getAnswer(String question);
}
