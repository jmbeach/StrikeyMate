package strikeaturkeytechnologiesllc.strikeymate;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jbeach on 10/27/15.
 */
public class UserAccount {
    public UUID userId;

    //region PUBLIC_METHODS
    public void updateAccount() {
        throw new UnsupportedOperationException();
    }
    public void deleteAccount() {
        throw new UnsupportedOperationException();
    }
    public ArrayList<Game> getRecentScores() {
        throw new UnsupportedOperationException();
    }
    public Date dateLastPlayed() {
        throw new UnsupportedOperationException();
    }
    //endregion

    //region PUBLIC_STATIC_METHODS
    public static UserAccount GetUserAccountByID(UUID id) {

    }
    //endregion
}
