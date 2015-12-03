package strikeaturkeytechnologiesllc.strikeymate;

/**
 * Created by jbeach on 10/27/15.
 */
public class Vote extends GameSessionActivity {
    public int yeses;
    public int noes;

    /**
     * If user said yes on the vote, it will increment the agreement side, and if user said,
     * no, then it will increase the opposite side.
     */

    public void voting() {

    }

    /**
     * *Determines the the vote by the number and decide to modify player's score
     * @return boolean
     */
    public boolean result() {
        //if yeses are greater than noes then, it will return true.
        if (yeses > noes) {
            return true;
            //if noes is greater than yeses then it will return false
        } else if (noes > yeses) {
            return false;
            //otherwise, it will always return false
        } else {
            return false;
        }
        //calling this from modifyScore method
    }
}
