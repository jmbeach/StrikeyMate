package strikeaturkeytechnologiesllc.strikeymate;

import java.util.ArrayList;

/**
 * Created by jbeach on 10/27/15.
 */
public class Frame {
    //region PUBLIC_ATTRIBUTES
    public Leave firstAttempt;
    public Leave secondAttempt;
    public ArrayList<Leave> additionalAttempts;
    public int frameNumber;
    //endregion

    //region PUBLIC_METHODS

    /**
     * Returns true if the Player bowled a strike in the frame
     * @return
     */
    public boolean isStrike() {
        throw new UnsupportedOperationException();
    }

    /**
     * Return true if the Player bowled a spare in the frame
     * @return
     */
    public boolean isSpare() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the player's total score during the frame
     * @return
     */
    public int score() {
        throw new UnsupportedOperationException();
    }
    //endregion
}
