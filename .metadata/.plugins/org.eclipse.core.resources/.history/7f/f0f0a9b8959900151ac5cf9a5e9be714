package none.strikeymatetemp;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Test;

import java.util.UUID;

import strikeaturkeytechnologiesllc.strikeymate.Server;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends InstrumentationTestCase {
    @Test
    public void addition_isCorrect() throws Exception {
        UUID jaredId = UUID.fromString("2e75c3ac-9788-11e5-91e0-0a8a83fecc17");
        String data = Server.getUserDataById(jaredId, getInstrumentation().getContext());
        Log.i("test", data);
    }
}