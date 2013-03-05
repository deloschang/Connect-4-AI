import java.util.Scanner;

/**
 * Connect4.java
 * 
 * Controller that plays the connect 4 game. 
 * @author Delos Chang
 *
 */

public class Connect4 {
	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		String answer = "";
		Connect4View view;
		
		// Ask for either text or view 
		while (!(answer.contains("Text") || answer.contains("Graphic"))){
			System.out.println("Text or Graphical View?");
			answer = input.nextLine();
		}
		
		if (answer.contains("Text")){
			view = new Connect4Text();
		} else {
			view = new Connect4ViewGraphical();
		}
		
		Player [] players = new Player[2];

		// Initialize the game
			// Computer - for computer
			// Professor - Prof. Drysdale's AI
		
		String playerName = view.getAnswer("Enter the name of the first player." +
		"\n(Include 'Computer' if you want a computer player) ");
		
		if (playerName.contains("Computer")){
			int askDepth = view.getIntAnswer("Please enter depth of computer");
			players[0] = new ComputerConnect4Player(playerName, askDepth);
		} else if (playerName.contains("Professor")){ 
			players[0] = new ComputerPlayerABPlus(playerName, 16);
		} else { 
			players[0] = new Connect4HumanPlayer(playerName);
		}
		
		playerName = view.getAnswer("Enter the name of the second player." +
		"\n(Include 'Computer' if you want a computer player) ");
		
		if (playerName.contains("Computer")){
			int askDepth = view.getIntAnswer("Please enter depth of computer");
			players[1] = new ComputerConnect4Player(playerName, askDepth);
		} else if (playerName.contains("Professor")){ 
			players[1] = new ComputerPlayerABPlus(playerName, 16);
		} else { 
			players[1] = new Connect4HumanPlayer(playerName);
		}
		
		Connect4Game state = new Connect4Game(0, players); 
		
		view.display(state);
		
		// Hold current game state
		while (!state.gameIsOver()){
			int move = state.getPlayerToMove().getMove(state, view);
			
			state.makeMove(move);
			view.display(state);
		}
		
		// The game is over
			// declare the winner!
		view.reportToUser(state.getPlayers()[1 - state.getPlayerNum()].getName() + " won!");
		
		
	}
}
