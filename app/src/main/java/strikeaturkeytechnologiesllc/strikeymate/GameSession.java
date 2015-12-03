package strikeaturkeytechnologiesllc.strikeymate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    public UUID managerId;
    public int currentFrame;
    public Date startTime;
    public Duration sessionDuration;
    public String gameCode;
    public int gameId;
    public GameSessionOptions options;
    //endregion

    public static String strFieldNamePlayers = "players";
    public static String strFieldNameGameId = "gameId";
    public static String strFieldNameGameCode = "gameCode";
    public static String strFieldNameStartTime = "startTime";
    public static String strFieldNameManagerId = "managerId";
    public static String strFieldNameAllowsFlagging = "allowsFlagging";
    public static String strFieldNameGameSize = "gameSize";
    //region PRIVATE_ATTRIBUTES
    //endregion

    //region EVENTS
    //endregion

    //region PUBLIC_METHODS
    public String getGameCode() {
        return gameCode;
    }
    public static GameSession create(GameSessionOptions options) {
        JsonObject jsonData = Server.postCreateGameSession(options);
        GameSession session = new GameSession();
        session.gameCode = jsonData.get(strFieldNameGameCode).isJsonNull() ?
                null : jsonData.get(strFieldNameGameCode).getAsString();
        session.gameId = jsonData.get(strFieldNameGameId).isJsonNull() ?
                null : jsonData.get(strFieldNameGameId).getAsInt();
        session.managerId = options.managerId;
        return session;
    }
    public static GameSession ById(int gameSessionId) {
        JsonObject jsonData = Server.getGameSessionById(gameSessionId);
        GameSession session = new GameSession();
        assignToGameSessionFromJson(session,jsonData);
        return session;
    }

    public static GameSession ByCode(String gameCode) {
        JsonObject jsonData = Server.getGameSessionByCode(gameCode);
        GameSession session = new GameSession();
        assignToGameSessionFromJson(session,jsonData);
        return session;
    }

    //endregion

    //region PRIVATE_METHODS
    private void startFlagPlayerVoteOnServer(Player playerInQuestion) {
        throw new UnsupportedOperationException();
    }

    //launches the game
    private void startGame() {

    }

    private static void assignToGameSessionFromJson(GameSession session, JsonObject jsonData) {
        session.gameCode = jsonData.get(strFieldNameGameCode).isJsonNull() ?
                null : jsonData.get(strFieldNameGameCode).getAsString();
        session.gameId = jsonData.get(strFieldNameGameId).isJsonNull() ?
                null : jsonData.get(strFieldNameGameId).getAsInt();
        JsonElement jsonId = jsonData.get(strFieldNameManagerId);
        session.managerId = jsonId.isJsonNull() ? null :
               UUID.fromString(jsonId.getAsString());
        JsonElement players = jsonData.get(strFieldNamePlayers);
//        session.players = players.isJsonNull() ? null :
//                players.getAsJsonArray();
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
