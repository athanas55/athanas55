package basketsim_v2;
//extended version of BasketSim for handling discretely offensive and defensive rebounds

import java.util.Random;

public class Team {
    String name;
    int score;
    Player players[] = new Player[5];
	
    public Team(String n) {
	name = n;
	score = 0;
	// Create the first line-up by position
	players[0] = new Player("Point Guard");
	players[1] = new Player("Shooting Guard");
	players[2] = new Player("Small Forward");
	players[3] = new Player("Power Forward");
	players[4] = new Player("Center");
    }

    public void increaseScore(int s) { score += s;}
    public String getName() { return name; }	
    public int getScore() { return score; }	
	
    public int shoot() {
	// There is a 50% chance to miss, 40% to get 2-points, and 10% for 3-points 
        Random rand = new Random();  
        Random pl_rand = new Random();
        int shot_outcome = rand.nextInt(1000);
        int shooter = pl_rand.nextInt(5);
        
        System.out.println(name + "'s " + players[shooter].getName() + " shoots");
       
        players[shooter].increaseFG_attempted();
        if (shot_outcome < 500) {
            return 0;
        } 
        else if (shot_outcome < 900) {
            players[shooter].increasePoints(2);
            players[shooter].increaseFG_made();
            return 2;
        } 
        else {
            players[shooter].increasePoints(3);
            players[shooter].increaseFG_made();
            return 3;
        }
    }

    public int offensiveRebound() {
        // There is a 20% chance to get an offensive rebound 
        Random rand = new Random();  
        Random pl_rand = new Random();
        int reb_outcome = rand.nextInt(1000);
        int rebounder = pl_rand.nextInt(5);

        if (reb_outcome > 800) {
            System.out.println(name + "'s " + players[rebounder].getName() + " gets the offensive rebound");
            players[rebounder].increaseORebounds(); //Calling correct method for the increse of offensive rebounds
            return 1;
        } 
        else {
            return 0;
        }
    }

    public void defensiveRebound() {
        Random pl_rand = new Random();
        int rebounder = pl_rand.nextInt(5);
        System.out.println(name + "'s " + players[rebounder].getName() + " gets the defensive rebound");
        players[rebounder].increaseDRebounds();	//Calling correct method for the increse of defensive rebounds
    }

    public void showStats() {
        // Show the statistics of each team in following format
        // Name
        // Points:
        // Shoots Attempted:      Shots Made:         Percentage:
        // Rebounds
        int shotsAttempted = 0;     //counter of shots attempted by all team players
        int shotsMade = 0;          //counter of shots made by all team players
        int teamORebounds = 0;      //counter of offensive rebounds won by all team players
        int teamDRebounds = 0;      //counter of total defensive rebounds won by all team players

        for(int i = 0; i < players.length; i++){    //loop to calculate 
            shotsAttempted += players[i].fg_attempted;      //team FG attempted
            shotsMade += players[i].fg_made;                //team FG achieved
            teamORebounds += players[i].offensiveRebounds;  //team offensive rebounds
            teamDRebounds += players[i].defensiveRebounds;  //team defensive rebounds
        }

        float successRate = (float)shotsMade / shotsAttempted; //floating point variable to store FG success rate
        //printing
        System.out.println(name);      //name of the team
        System.out.println("Points: " + score);     //total score of the team
        System.out.print("Shoots Attempted: " + shotsAttempted + "\t" + "  Shots Made: " + shotsMade + "\t"); //FG attempted and FG achieved
        System.out.printf(" Percentage:%.2f%%%n", (successRate * 100));     //FG success rate (Percentage)
        System.out.println("Rebounds: " + (teamDRebounds + teamORebounds) + 
                           "\t(Offensive: "+ teamORebounds + 
                           ",\tDefensive: " + teamDRebounds + ")");     //and finally, team won offensive and defensive rebounds 
    }   //end of method showStats

    public void showPlayersStats() {
        // Show the statistics of each player in following format
        // Position Points (... rebounds, ... / ... shoots, index)
        // index = points + rebounds - missed shots
        System.out.println(name + " players statistics");    //print a stats table header
        System.out.printf("%-27s%s%n", " ", "______Rebounds______");
        System.out.printf("%-16s%-10s%-12s%-12s%s%10s%n",   //formaτted so that statistics can be easily interpreted
                          "Position", 
                          "Points", 
                          "(Offensive", 
                          "Defensive,", 
                          "Shoots,", 
                          "index)");
        System.out.println("--------------------------------------------------------------------"); //a separating line

        for(int i = 0; i < players.length; i++){    //loop for the calculation and printing of team's players stats

            int missedShots = players[i].fg_attempted - players[i].fg_made;  //calculating missed shots for the current player
            int rebounds = players[1].defensiveRebounds = players[1].offensiveRebounds;
            int index = players[i].points + rebounds - missedShots;   //calculating index for the current player

            // print statistics for current player
            System.out.printf("%-16s%-10d%s%-11d%d%-4s%02d%s%02d%-9s%02d%s%n", 
                    players[i].pos, 
                    players[i].points, "(", 
                    players[i].offensiveRebounds,
                    players[i].defensiveRebounds, ",\t",
                    players[i].fg_made, "/", 
                    players[i].fg_attempted, ",",
                    index, ")");
        }   //when loop is completed all team's players stats are printed
    }   //end of method showPlayerStats
}
