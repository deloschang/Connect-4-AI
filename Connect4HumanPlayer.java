public class Connect4HumanPlayer extends Player {

	/**
	 * Constructor to create the human player with a given name
	 * @param name name of the human player
	 */
	public Connect4HumanPlayer(String name ) { 
		super(name);
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		return view.getUserMove(state);
	} 
	
}