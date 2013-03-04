/**
 * Connect4.java
 * 
 * Controller that plays the connect 4 game. 
 * @author Delos Chang
 *
 */


public class Connect4 {
	public static void main(String [] args){
		int depth;                        // Number of moves to look ahead

		// Hold the view methods. 
		Connect4View view = new Connect4ViewGraphical();

		Player [] players = new Player[2];

		// Initialize the game
		
		String playerName = view.getAnswer("Enter the name of the first player." +
		"\n(Include 'Computer' if you want a computer player) ");
		
		if (playerName.contains("Computer")){
			players[0] = new ComputerConnect4Player(playerName);
		} else { 
			players[0] = new HumanConnect4Player(playerName);
		}
		
		playerName = view.getAnswer("Enter the name of the second player." +
		"\n(Include 'Computer' if you want a computer player) ");
		
		if (playerName.contains("Computer")){
			players[1] = new ComputerConnect4Player(playerName);
		} else { 
			players[1] = new HumanConnect4Player(playerName);
		}
		
		Connect4Game state = new Connect4Game(0, players, view); 
		
		view.display(state);
		
		// Hold current game state
		
		
		
		
	}
}
