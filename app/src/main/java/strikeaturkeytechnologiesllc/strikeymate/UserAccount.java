package strikeaturkeytechnologiesllc.strikeymate;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jbeach on 10/27/15.
 */
public class UserAccount {
    @SerializedName("id")
    public UUID userId;
    public String email;
    public String firstName;
    public String lastName;
    public Date joinDate;
    public String username;

    public static String strColNameEmail = "email";
    public static String strColNameFirstName = "firstName";
    public static String strColNameLastName = "lastName";
    public static String strColNameJoinDate = "joinDate";
    public static String strColNameUserName = "username";

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
    public static UserAccount getUserAccountByID(UUID id) {
        JsonObject jsonAccount = Server.getUserDataById(id);
        UserAccount account = new UserAccount();
        account.userId = id;
        account.email = jsonAccount.get(strColNameEmail).getAsString();
        account.firstName = jsonAccount.get(strColNameFirstName).isJsonNull() ? null:
                jsonAccount.get(strColNameFirstName).getAsString();
        account.lastName = jsonAccount.get(strColNameLastName).isJsonNull() ? null :
            jsonAccount.get(strColNameLastName).getAsString();
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        try {
            account.joinDate = format.parse(jsonAccount.get(strColNameJoinDate).getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        account.username = jsonAccount.get(strColNameUserName).getAsString();
        return account;
    }
    //endregion
}
