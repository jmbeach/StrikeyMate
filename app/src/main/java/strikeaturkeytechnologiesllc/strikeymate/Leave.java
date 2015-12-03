package strikeaturkeytechnologiesllc.strikeymate;

/**
 * Represents pins left standing during a frame
 */
public class Leave {
    //region PUBLIC_ATTRIBUTES
    public int pinCount;
    public int headPin;
    public boolean multihead = false;
    public boolean[] standing;
    //endregion

    //region PUBLIC_METHODS

    /**
     * Generates help information based on
     * the pins left standing
     *
     * "sugg" == "suggestion"
     */
    public void generateHelp() {

        //      the pins
        //    6   7   8   9
        //      3   4   5
        //        1   2
        //          0

        headPin = getHeadPin(standing);
        if (pinCount == 10) {
            //return strike sugg
        }
        else if (pinCount == 1) {
            //return single pin sugg
        }
        else if (pinCount == 2 && standing[6] && standing[9]) {
            //return 7-10 sugg
        }
        else if (pinCount == 4 && (standing[0] && standing[1] && standing[3] && standing[6])) {
            //return left4 sugg
        }
        else if (pinCount == 4 && (standing[0] && standing[2] && standing[5] && standing[9])) {
            //return right4 sugg
        }
        if (isBalancedLeave(standing))


            throw new UnsupportedOperationException();
    }

    public int getPinCount() {
        return pinCount;
    }
    //endregion

    //region PRIVATE_METHODS

    /**
     * Returns a boolean indicating whether or not the leave is symmetric about the 1 pin
     */
    private boolean isBalancedLeave(boolean[] standingPins) {

        //temporary
        return false;
    }

    /**
     * Returns the pin number of the pin furthest forward pin
     */
    private int getHeadPin(boolean[] standingPins) {
        for(int i = 0; i < 10; i++) {
            if (standingPins[i]) {
                return i;
            }
        }

        return -1;
    }
}
