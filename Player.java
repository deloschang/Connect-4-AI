/**
 * A player class to represent a Connect 4 player and its strategy.
 * @author Scot Drysdale
 */

public abstract class Player {
	private String playerName;
	
	/**
	 * @param name player's name
	 */
	public Player (String name) {
		playerName = name;
	}
	
	/** 
	 * @return the player's name
	 */
	public String getName() {
		return playerName;
	}
	
	/**
	 * Gets and returns the player's choice of move
	 * @param state current game state
	 * @param view the object that displays the game
	 * @return move chosen by the player, in the range
	 *   0 to Connect4State-1
	 */
	public abstract int getMove(Connect4State state, Connect4View view);
}
