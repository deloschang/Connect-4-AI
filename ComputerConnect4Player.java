public class ComputerConnect4Player extends Player {
	private int depth;  // depth to search at
	
	private static final int[] HOW_GOOD = {0, 2, 10^2, 10^3, 10^6}; // index is # of unblocked four-in-row potentials
	private static final int NEG_BOUND = -99999999;
	private static final int POS_BOUND = 99999999;

	/**
	 * Create a computer player with a given name
	 * @param name name of computer player
	 */
	public ComputerConnect4Player(String name, int depth){
		super(name);
		this.depth = depth;
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		// First copy the game instance
		Connect4Game stateCopy = new Connect4Game(state.getPlayerNum(), state.getPlayers(), state.getBoard(), findUnblocked(state), movesDone(state));
		
		Connect4Move chosenMoveObj = pickMove(stateCopy, depth, NEG_BOUND, POS_BOUND);
		int chosenMove = chosenMoveObj.move;
		
		view.reportMove(chosenMove, state.getPlayerToMove().getName());
		
		return chosenMove;
	} 
	
	/**
	 * Uses game tree search with alpha-beta pruning to pick player's move 
	 * low and high define the current range for the best move
	 * 
	 * @param state the current state of the game
	 * @param depth the number of moves to look ahead in game tree search
	 * @param low a value that the player can achieve by some other move
	 * @param high a value that the opponent can force by a different line of play
	 * @return the move chosen
	 */
	private static Connect4Move pickMove(Connect4Game state, int depth, int low, int high){
		
	}
	
	
	/**
	 * Helper method that counts the moves made
	 * @param state the input state of the board
	 * @return the number of moves already made
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
	 * 
	 * @param state the input state of the board
	 * @return a total int evaluation of unblocked four-in-rows for opp and computer
	 */
	private static int findUnblocked(Connect4State state){
		// grab the checker pieces and board
		char opponentChecker = Connect4State.CHECKERS[1 - state.getPlayerNum()];
		char computerChecker = Connect4State.CHECKERS[state.getPlayerNum()];
		
		char[][] board = state.getBoard();
		
		// value that evaluates the unblocked four-in-rows
		int totalEvaluation = 0;
		
		// Evaluate unblocked verticals
			// all potential ver 4-in-row start from at most from row 2
		for (int column = 0; column < Connect4Game.COLS; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;
				
				for (int checkRow = row; checkRow < row + 4; checkRow++){
					if (board[checkRow][column] == computerChecker){
						compCount++;
					} else if (board[checkRow][column] == opponentChecker){
						oppCount++;
					}
				}
				
				if (compCount == 0){
					// bad for comp
					totalEvaluation += HOW_GOOD[oppCount];
				} else if (oppCount == 0){
					// good for comp
					totalEvaluation -= HOW_GOOD[compCount];
				}
			}
		}
		
		// Evaluate unblocked horizontals
			// all potential hor 4-in-row start from at most from halfway col
		for (int column = 0; column <= 3; column++){
			for(int row = 0; row < Connect4Game.ROWS; row++){
				// counters for computer and opponent
				int compCount = 0;
				int oppCount = 0;
				
				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					// check whose checker it is and increment their counter
					if (board[row][checkColumn] == computerChecker){
						compCount++;
					} else if (board[row][checkColumn] == opponentChecker){
						oppCount++;
					}
				}
				
				if (compCount == 0){
					// bad for comp
					totalEvaluation += HOW_GOOD[oppCount];
				} else if (oppCount == 0){
					// good for comp
					totalEvaluation -= HOW_GOOD[compCount];
				}
				
			}
		}
		
		// Evaluate unblocked diagonals (up to right)
			// up to right diagonal start at most from row 2, column 3
		for (int column = 0; column < 4; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;
				
				int checkRow = 0; // need a checkrow parameter for diag
				for (int checkColumn = column; checkRow < row + 4; checkColumn++){
					if (board[checkRow][checkColumn] == computerChecker){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponentChecker){
						oppCount++;
					}
					
					checkRow++; // adjust for diagonal
				}
				
				if (compCount == 0){
					// bad for comp
					totalEvaluation += HOW_GOOD[oppCount];
				} else if (oppCount == 0){
					// good for comp
					totalEvaluation -= HOW_GOOD[compCount];
				}
				
			}
		}
		
		// Evaluate unblocked diagonals (down to right)
			// down to right diagonal start at most from row 3, column 3
		for (int column = 0; column < 4; column++){
			for (int row = 3; row < 7; row++){
				int compCount = 0;
				int oppCount = 0;
				
				int checkRow = row; // need a checkrow parameter for diag
				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					if (board[checkRow][checkColumn] == computerChecker){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponentChecker){
						oppCount++;
					}
					
					checkRow--; // adjust for diagonal
				}
				
				if (compCount == 0){
					// bad for comp
					totalEvaluation += HOW_GOOD[oppCount];
				} else if (oppCount == 0){
					// good for comp
					totalEvaluation -= HOW_GOOD[compCount];
				}
				
			}
		}
		
		return totalEvaluation;
		
	}
}