package strikeaturkeytechnologiesllc.strikeymate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.sf.corn.httpclient.HttpForm;
import net.sf.corn.httpclient.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import none.strikeymatetemp.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    private String strUrlLogin;
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;
    private View mControlsView;
    private Button btnRegister;
    private Button btnSkip;
    private Button btnLogin;
    private EditText tbLoginUsername;
    private EditText tbLoginPassword;
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If user session already exists
        if (loggedInUser() != null) {
            // go straight to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        strUrlLogin = getResources().getString(R.string.backend_url) + "login";        setContentView(R.layout.activity_login);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);



        tbLoginUsername = (EditText)findViewById(R.id.tbLoginUsername);
        tbLoginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    String input = s.toString();
                    // if the user types a space
                    if (input.contains(" ")) {
                        // remove it
                        String newText = input.replaceAll(" ","");
                        tbLoginUsername.setText(newText);
                    }
                }
            }
        });
        tbLoginPassword = (EditText) findViewById(R.id.tbLoginPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pull up registration activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnSkip = (Button) findViewById(R.id.btnLoginSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pull up main activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestLogin(tbLoginUsername.getText().toString(),tbLoginPassword.getText().toString());
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void requestLogin(String username, String pass) {
        URI url = null;
        try {
            url = new URI(strUrlLogin);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpForm form = new HttpForm(url);
        form.putFieldValue("identity",username);
        form.putFieldValue("pass", pass);
        class RunnableLogin implements Runnable {
            HttpForm form;
            Context context;
            String data;
            public RunnableLogin(HttpForm _form, Context c) {
                form = _form;
                context = c;
            }
            @Override
            public void run() {
                HttpResponse res = null;
                try {
                    res = form.doPost();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // if the result is still null
                if (res == null) {
                    // couldn't communicate with server
                    data = "Couldn't communicate with server.";
                    return;
                }
                String data = res.getData();
                System.out.println(data);
                this.data = data;
            }
        }
        RunnableLogin task;
        task = new RunnableLogin(form,getApplicationContext());
        Thread t = new Thread(task);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // if server returns a UUID
        if (isUuid(task.data)){
            // then the operation was successful
            // Save the id of the logged in user
            setLoggedInUser(task.data);
            // redirect user to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        CharSequence text = task.data;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
    private UUID loggedInUser() {
        String prefGroup = getResources().getString(R.string.pref_group_main);
        String prefName = getResources().getString(R.string.pref_logged_in_user);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(prefGroup, 0);
        String strPref = pref.getString(prefName,null);
        // if the preference hasn't been set
        if (strPref == null) {
            // return null
            return null;
        }
        // otherwise try to make an id with what is stored
        UUID id;
        try {
            id = UUID.fromString(strPref);
        } catch(Exception e) {
            // if not successful return null
            return null;
        }
        // otherwise return uuid
        return id;
    }
    private void setLoggedInUser(String id) {
        String prefGroup = getResources().getString(R.string.pref_group_main);
        String prefName = getResources().getString(R.string.pref_logged_in_user);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(prefGroup,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(prefName,id);
        editor.commit();
    }
    private static boolean isUuid(String uuid) {
        try {
            // if this does not throw exeption
            UUID.fromString(uuid);
            // then it is a UUID
            return true;
        }
        catch(Exception e) {
            // otherwise it is not a UUID
            return false;
        }
    }
}
