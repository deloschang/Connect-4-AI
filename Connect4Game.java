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
	
	private int latestRow = -1; // latest row added to by makeMove method
								// initialize to -1
	private int latestCol = -1; // latest column added to by makeMove method
								// initialize to -1
	
	private int movesDone; // number of moves made
	
	private int unblockedValue; // int evaluation of unblocked four-in-row for both players


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
		
		movesDone = 0;
		unblockedValue = 0;
	}
	
	/**
	 * Construct the game with input states
	 * 
	 * @param playerNum the player whose move it is
	 * @param thePlayers the player objects
	 * @param initialBoard the board input with requisite pieces
	 * @param movesMade the number of moves already made
	 * @param unblockedTotal a total int evaluation of unblocked four-in-row for opp and computer
	 */
	public Connect4Game(int playerNum, Player[] thePlayers, char[][] initialBoard, int movesMade, int unblockedTotal){
		// Initialize board with rows and columns
		board = new char[ROWS][COLS];
		
		for (int row = 0; row < ROWS; row++){
			for (int column = 0; column < COLS; column++){
				board[row][column] = initialBoard[row][column];
			}
		}
		
		playerToMoveNum = playerNum;
		players = thePlayers;
		
		movesDone = movesMade;
		unblockedValue = unblockedTotal;
	
		
		
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

	public int getMovesPlayed(){
		return movesDone;
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
			int openRow = findOpenRow(col);
			
			System.out.println(openRow + " is the first open row");
			
			// add the checker
			board[openRow][col] = CHECKERS[getPlayerNum()];
			
			System.out.println(board[openRow][col]);
			
			// Switch player
			playerToMoveNum = 1 - playerToMoveNum;
			
			// Increment moves done
			movesDone++;
			
			// Update latest row/cols
			latestRow = openRow;
			latestCol = col;
		} else { 
			throw new IllegalStateException("Column is full!");
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
		for (int i = 0; i < ROWS; i++){
			System.out.println("Checking " + i + " for open row");
			if (board[i][col] == EMPTY){
				return i;
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
			System.out.println(ROWS - 1 + "," + col + " is empty");
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
		return (movesDone == ROWS * COLS);
	}
	
	
	/**
	 * Checks for four-in-row
	 * @param row latest row that a move was made on
	 * @param column latest column that a move was made on
	 * @param rowOffset a row offset to calculate different connect 4 possibilities
	 * @param colOffset a row offset to calculate different connect 4 possibilities
	 * @return true iff there is a connect 4
	 */
	private boolean checkForFour(int row, int column,
			int rowOffset, int colOffset){
		
		int winCounter = 0; // counts to 4 for win
		
		// Find opp ends for the possible Connect 4
		int oppRow = 3 * rowOffset + row; 
		int oppColumn = 3 * colOffset + column;
		
		// conditions where Connect 4 is impossible
			// less than 7 moves (counting both players)
			// adjusted offset for row/col is < 0 or > maximum
		if ( (movesDone < 7 ) || (oppRow >= ROWS) || (oppColumn >= COLS) ||
				(oppRow < 0) || (oppColumn < 0) ||
				(row < 0) || (column < 0) || 
				(row >= ROWS) || (column >= COLS)){
			return false;
		}
		
		for (int i = 1; i < 5; i++){
			System.out.println("latestRow is "+row);
			System.out.println("latestCol is "+column);
			
			System.out.println("Piece here is: "+board[row][column]);
			System.out.println("Checking for winning "+CHECKERS[1 - playerToMoveNum]);
			if (board[row][column] == CHECKERS[1 - playerToMoveNum]){
				
				winCounter++;
			}
			
			System.out.println("wincounter = "+winCounter);
			
			// Adjust offsets and look for the next piece 
			// that would lead to a four-in-row.
			row += rowOffset;
			column += colOffset;
		}
		
		return (winCounter == 4);
	}
	
	/**
	 * Decides if game is over
	 * @return true iff the game is over
	 */
	@Override
	public boolean gameIsOver() {
		// Check if game is complete
		if ( isFull() ){
			return true;
		}
		
		// Check vertical four-in-row
		System.out.println("Checking vertical conditions...\n");
		if ( checkForFour(latestRow, latestCol, -1, 0)){
			return true;
		} 
		
		for (int offset = 0; offset < 4; offset++){
			// Check horizontal four-in-row
			System.out.println("Checking horizontal conditions...\n");
			if ( checkForFour(latestRow, latestCol - offset, 0, 1)) return true;
			
			System.out.println("Checking diag lower right conditions...\n");
			// Check diagonal via lower right
			if ( checkForFour(latestRow - offset, latestCol + offset, 1, -1)) return true;
			
			System.out.println("Checking diag upper right conditions...\n");
			// Check diagonal via upper right
			if ( checkForFour(latestRow - offset, latestCol - offset, 1, 1)) return true;
		}
		
		return false;
	}

}