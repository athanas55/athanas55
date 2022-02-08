package basketsim_v2;
//extended version of BasketSim for handling discretely offensive and defensive rebounds

public class MainClass {

    public static void main(String[] args) {
	// Create a Game
                
        Team home = new Team("Washington Wizards"); //create a new team for home team
        Team away = new Team("Chicago Bulls");  //create a new team for away team
        Game derby = new Game(home, away);  //create a new game

        // Simulate Game
        derby.simulateGame();
           
        // Show Team Statistics
        derby.showTeamStats();  //call showTeamStats from class Game
            
	// Show Players Statistics
        derby.showPlayersStats();   //call showPlayerStats from class Game
    }

}   //end of Main class
