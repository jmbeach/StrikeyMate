package strikeaturkeytechnologiesllc.strikeymate;

/**
 * Created by jbeach on 10/27/15.
 */
public class Manager extends Player {
    //region PUBLIC_METHODS
    /**
     * Tells the server to create a new game session
     * and treat this player as the game manager.
     */
    public void createGame() {
        throw new UnsupportedOperationException();
    }

    /**
     * Tells the server that the game session can
     * be deleted.
     */
    public void destroyGame() {
        throw new UnsupportedOperationException();
    }

    /**
     * Allows another player to become the manager of the game session.
     * @param player The player to give manager status to.
     */
    public void giveLeadership(Player player) {
        throw new UnsupportedOperationException();
    }
    //endregion
}
