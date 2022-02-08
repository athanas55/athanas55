package basketsim_v2;
//extended version of BasketSim for handling discretely offensive and defensive rebounds

public class Game {
    Team homeTeam;
    Team awayTeam;
	
    public Game(Team hT, Team aT) {
        homeTeam = hT;
        awayTeam = aT;
    }
	
    public void simulateGame() {
	char ball = 'h';
	int score = 0;
	
	// We consider that a basketball game has 120 plays (3 per minute)
	for (int i = 1; i <= 120; i++) {
	//System.out.println(i + ": play");
            if (ball == 'h') {
            // Returns if the team scores a basket (2-point or 3-points or misses) 
		score = homeTeam.shoot();
                if (score > 0) {
                    System.out.println(homeTeam.getName() + " scores " + score + " points");
                } 
                else {
                    System.out.println(homeTeam.getName() + " misses the shot");				
                }
            
                homeTeam.increaseScore(score);
                if (score == 0) {
                    // Check if there is an offensive rebound to continue play
                    int rebound = homeTeam.offensiveRebound();
                    if (rebound == 0) {
                        // Check the player who gets the rebound, and switch possession
                        awayTeam.defensiveRebound();
                        ball = 'a';
                    }
                } 
                else {
                    ball = 'a';					
                }
            } 
            else {
              	// Returns if the team scores a basket (2-point or 3-points or misses) 
		score = awayTeam.shoot();
		if (score > 0) {
                    System.out.println(awayTeam.getName() + " scores " + score + " points");
                } 
                else {
                    System.out.println(awayTeam.getName() + " misses the shot");				
		}
		
                awayTeam.increaseScore(score);
		if (score == 0) {
                    // Check if there is an offensive rebound to continue play
                    int rebound = awayTeam.offensiveRebound();
                    if (rebound == 0) {
                        // Check the player who gets the rebound, and switch possession
			homeTeam.defensiveRebound();
			ball = 'h';
                    }
		} 
                else {
                ball = 'h';					
                }
            }
            
            if (score > 0) {
		System.out.print("\t" + homeTeam.getName() + ":" + homeTeam.getScore() + "  -  ");
		System.out.println(awayTeam.getName() + ":" + awayTeam.getScore() + "\n");
            }
	}
	
        if (score == 0) {
            System.out.print("\t" + homeTeam.getName() + ":" + homeTeam.getScore() + "  -  ");
            System.out.println(awayTeam.getName() + ":" + awayTeam.getScore() + "\n");			
	}
    }

    public void showTeamStats() {
	System.out.println("\n====================================================================");
	homeTeam.showStats();
	System.out.println("\n====================================================================");
	awayTeam.showStats();
    }

    public void showPlayersStats() {
	System.out.println("\n====================================================================");
	homeTeam.showPlayersStats();
	System.out.println("\n====================================================================");
	awayTeam.showPlayersStats();
    }
}
