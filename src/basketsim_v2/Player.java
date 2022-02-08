package basketsim_v2;
//extended version of BasketSim for handling discretely offensive and defensive rebounds

public class Player {
    String pos;
    int points;
    int offensiveRebounds;  //integer to separately count offensive rebounds
    int defensiveRebounds;  //integer to separately count defensive rebounds
    int fg_made;            //integer to vount field goals attempted
    int fg_attempted;       //integer to count field golas made
	
    public Player(String p) {   //constructor
        pos = p;
        points = 0;
        offensiveRebounds = 0;  //initialization of offensive rebounds counter
        defensiveRebounds = 0;  //initialization of defensive rebounds counter
        fg_made = 0;
        fg_attempted = 0;
    }

    public void increasePoints(int p) {points += p;}
    public void increaseORebounds() {offensiveRebounds++;}  //increase offensive rebound counter for each successful attempt
    public void increaseDRebounds() {defensiveRebounds++;}  //increase defensive rebound counter for each successful attempt
    public void increaseFG_made() {fg_made++;}              //count field goal attempts
    public void increaseFG_attempted() {fg_attempted++;}    //count field goals

    public String getName() { return pos; }
    public int getFGattempted() { return fg_attempted; }
    public int getFGmade() { return fg_made; }
    public int getOReb() { return offensiveRebounds; }      //getter for offensive rebounds
    public int getDReb() { return defensiveRebounds; }      //getter for defensive rebounds

}
