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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeMove(int col) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameIsOver() {
		// TODO Auto-generated method stub
		return false;
	}

}