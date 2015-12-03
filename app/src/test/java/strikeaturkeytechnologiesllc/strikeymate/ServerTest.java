package strikeaturkeytechnologiesllc.strikeymate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by jbeach on 12/3/15.
 */
public class ServerTest extends TestCase {

    @Test
    public void testGetUserDataById() throws Exception {
        String id = "2e75c3ac-9788-11e5-91e0-0a8a83fecc17";
        JsonObject data = Server.getUserDataById(UUID.fromString(id));
        assertEquals(data.get("email").getAsString(),"jaredbeachdesign@gmail.com");
    }
}