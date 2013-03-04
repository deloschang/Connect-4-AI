public class ComputerConnect4Player extends Player {
	private int depth;  // depth to search at

	/**
	 * Create a computer player with a given name
	 * @param name name of computer player
	 */
	public ComputerConnect4Player(String name, int maxDepth){
		super(name);
		this.depth = maxDepth;
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		// First copy the game instance
		Connect4Game stateCopy = new Connect4Game(state.getPlayerNum(),
				state.getPlayers(), state.getBoard(), findUnblocked(state), movesDone(state));
		
		view.reportMove(1, state.getPlayerToMove().getName());
		
		return 1;
	} 
	
	/**
	 * Helper method that counts the moves made
	 * @param state
	 * @return
	 */
	private static int movesDone(Connect4State state){
		int counter = 0;
		for (int row = 0; row < Connect4Game.ROWS; row++){
			for (int column = 0; column < Connect4Game.COLS; column++){
				if (state.getBoard()[row][column] != Connect4Game.EMPTY) counter++;
			}
		}
		
		return counter;
	}
	
	/**
	 * Find unblocked 4 in a rows
	 */
	private static int findUnblocked(Connect4State state){
		
	}
	
}