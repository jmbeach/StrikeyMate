package strikeaturkeytechnologiesllc.strikeymate;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by jbeach on 10/27/15.
 */
public class Player {
    //region PUBLIC_ATTRIBUTES
    public ArrayList<Frame> frames;
    public UUID userId;
    public String userName;
    //endregion

    public Player(UserAccount account){
        userName = account.username;
        userId = account.userId;
    }
    public Player(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }


    //region PUBLIC_METHODS
    /**
     * Allows player to join game by the
     * game's unique code.
     */
    public void joinGame(String gameCode) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the player's total score over a frame by
     * the frame number (1 - 10)
     * @param frameNumber A number 1 - 10 representing which
     *                    frame to get the player's score for.
     * @return An int representing the player's score
     */
    public int score(int frameNumber)
    {
        return frames.get(frameNumber).score();
    }

    /**
     * If the player is currently in a game,
     * calling this removes them from the game session.
     */
    public void leaveGame() {
        throw new UnsupportedOperationException();
    }

    /**
     * Tells the server to send another player's device an invitation
     * to join the current player's game.
     * @param player The player to send the invitation to.
     */
    public void invitePlayer(Player player) {
        throw new UnsupportedOperationException();
    }

    /**
     * Tells the server to start a vote to debate another
     * player's score on a specific frame.
     * @param player The player who is accused.
     * @param frame The frame on which the player is accused.
     */
    public void flagScore(Player player, Frame frame) {
        throw new UnsupportedOperationException();
    }


    public Frame getFrame(int frameNum) {
        return frames.get(frameNum);
    }
    //endregion
}
