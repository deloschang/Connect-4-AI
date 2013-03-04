import java.util.Arrays;


/**
 * Connect4Game.java
 * 
 * Represents the state of the Connect 4 game.
 * 
 * @author Delos Chang
 *
 */

public class Connect4Game implements Connect4State{
	private char[][] board;
	private Player [] players;
	private int playerToMoveNum; // 0 or 1 for which player to go


	/**
	 * Constructs game in initial state
	 * @param playerNum the player whose move it is
	 * @param thePlayers the player objects
	 * @param aView the view in the model-view-controller model
	 */
	public Connect4Game(int playerNum, Player [] thePlayers){
		board = new char[ROWS][COLS];
		
		// fill board with empty slots
		for (char[] row : board){
			Arrays.fill(row, EMPTY);
		}
		
		playerToMoveNum = playerNum;
		players = thePlayers;
	}


	@Override
	/**
	 * Gets a 2-D array representing the board.
	 * The first subscript is the row number and the second the column number.
	 * The bottom of the board is row 0 and the top is row ROWS-1.
	 * The left side of the board is column 0 and the right side is column COLS-1.
	 * 
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}

	@Override
	public Player[] getPlayers() {
		return players;
	}

	@Override
	public int getPlayerNum() {
		return playerToMoveNum;
	}

	@Override
	public Player getPlayerToMove() {
		return players[playerToMoveNum];
	}

	@Override
	public boolean isValidMove(int col) {
		// move is valid if the top column isn't full
		return !isColumnFull(col);
	}

	/**
	 * Make a move, dropping a checker in the given column
	 * @param col the column to get the new checker
	 */
	@Override
	public void makeMove(int col) {
		// first check if the move is valid
		if (isValidMove(col)){
			System.out.println("hello");
			int openRow = findOpenRow(col);
			
			System.out.println(openRow);
			
			// add the checker
			board[openRow][col] = CHECKERS[getPlayerNum()];
			
			System.out.println(board[openRow][col]);
		} else { 
			System.out.println("Not a valid move. Choose another");
		}
	}

	/** 
	 * Find the first empty row in a column
	 * -1 if the column is full (no empty row)
	 * 
	 * @param col the column to check
	 */
	private int findOpenRow(int col){
		// find the first row that isn't filled
		for (int i = 0; i < ROWS - 1; i++){
			if (board[i][col] == EMPTY){
				return col;
			}
		}
		
		return -1;
	}
	
	/**
	 * Is column full?
	 * 
	 * @param col the column to check
	 * @return true if the column is full
	 */
	private boolean isColumnFull(int col) {
		System.out.println("Checking "+col);
		if (board[ROWS - 1][col] == EMPTY){
			System.out.println(ROWS - 1 + "," + col + "is empty");
			return false;
		} else { 
			return true;
		}
	}
	
	/**
	 * Is the board full?
	 * @return true if the board is full
	 */
	@Override
	public boolean isFull() {
		// Game is over when top row of all slots are filled
		for(int i = 0; i < COLS; i++){
			if (!isColumnFull(i)){
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Decides if game is over
	 * @return true iff the game is over
	 */
	@Override
	public boolean gameIsOver() {
		return isFull();
	}

}