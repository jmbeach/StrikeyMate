package strikeaturkeytechnologiesllc.strikeymate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
    public String getGameCode() {
        return gameCode;
    }
    //endregion

    //region PRIVATE_METHODS
    private void startFlagPlayerVoteOnServer(Player playerInQuestion) {
        throw new UnsupportedOperationException();
    }

    //launches the game
    private void startGame() {

    }


    //generate a random game ID
    //use a RNG to get a character between 0 and 35
    //0-25 represents a letter, 26-35 represents a digit 0-9
    private String generateGameCode() {
        Random rand = new Random();
        char character;
        int num;
        for (int i = 0; i < 4; i++) {
            num = rand.nextInt(35);
            if (num <= 25) {
                gameCode += Character.toString((char) (num + 97));
            }
            else {
                gameCode += Character.toString((char) (num + 22));
            }
        }

        return gameCode;
    }
    //endregion

}
