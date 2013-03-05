import java.util.Scanner;

/**
 * Textual view of the Connect 4 board with interaction from keyboard
 * @author Delos Chang
 *
 */

public class Connect4Text implements Connect4View{
	private Scanner input; 
	
	public Connect4Text(){
		input = new Scanner(System.in);
	}
	
	/**  
	 * Displays the current board
	 * @param state current state of the game
	 */
	public void display(Connect4State state){
		char [][] board = state.getBoard();
		
		for (int row = state.ROWS - 1; row > -1; row--){
			System.out.print(row + " |");
			for (int column = 0; column < state.COLS; column++){
				System.out.print(board[row][column]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}
		
		System.out.print("  ");
		for (int column = 0; column < state.COLS; column++){
			System.out.print(" "+column);
		}
		
		System.out.println("\n");
		
	}

	/**
	 * Asks the user for a move
	 * The move will be in the range 0 to Connect4State.COLS-1.
	 * @param state current state of the game
	 * @return the number of the move that player chose
	 */
	public int getUserMove(Connect4State state){
		int column;
		
		System.out.println();
		System.out.println("Please pick a column");
		
		column = input.nextInt();
		
		// check boundaries
		while ((column < 0) || (column > state.COLS - 1) || !state.isValidMove(column)){
			System.out.println("Illegal column. Please try again");
			System.out.println("Please pick a column");
			column = input.nextInt();
		}
		
		return column;
	}

	/**
	 * Reports the move that a player has made.
	 * The move should be in the range 0 to Connect4State.COLS-1.
	 * @param chosenMove the move to be reported
	 * @param name the player's name
	 */
	public void reportMove (int chosenMove, String name){
		System.out.println("\n" + name + " chooses the column " + chosenMove);
	}

	/**
	 * Ask the user the question and return the answer as an int
	 * @param question the question to ask
	 * @return The depth the player chose
	 */
	public int getIntAnswer (String question){
		int answer = 0;
		boolean valid = false;
		
		// Ask question
		System.out.println(question + " ");
		
		while (!valid){
			try { 
				answer = input.nextInt();
				valid = true; 
			} catch (NumberFormatException ex) {
				reportToUser("Error: "+ ex + " Please enter an integer");
				valid = false;
			}
		}
		
		return answer;
		
	}

	/**
	 * Convey a message to user
	 * @param message the message to be reported
	 */
	public void reportToUser(String message){
		System.out.println(message);
	}

	/**
	 * Ask the question and return the answer
	 * @param question the question to ask
	 * @return the answer to the question
	 */
	public String getAnswer(String question){
		System.out.println(question + " ");
		
		return input.nextLine();
		
	}
	
}