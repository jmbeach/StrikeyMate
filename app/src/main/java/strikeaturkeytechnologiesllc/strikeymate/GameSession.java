package strikeaturkeytechnologiesllc.strikeymate;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.xml.datatype.Duration;

/**
 * Created by jbeach on 11/3/15.
 */
public class GameSession {

    //region PUBLIC_ATTRIBUTES
    public ArrayList<Player> players;
    public Manager manager;
    public int currentFrame;
    public Date startTime;
    public Duration sessionDuration;
    public String gameCode;
    public UUID gameId;
    public GameSessionOptions options;
    //endregion

    //region PRIVATE_ATTRIBUTES
    //endregion

    //region EVENTS
    //endregion

    //region PUBLIC_METHODS
    //endregion

    //region PRIVATE_METHODS
    private void startFlagPlayerVoteOnServer(Player playerInQuestion) {
        throw new UnsupportedOperationException();
    }
    //endregion

}
