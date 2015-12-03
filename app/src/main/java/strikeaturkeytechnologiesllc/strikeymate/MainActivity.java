package strikeaturkeytechnologiesllc.strikeymate;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.w3c.dom.Text;

import java.net.URISyntaxException;

import none.strikeymatetemp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //region CONSTANTS
    private static final String strUrlSocketServer = "<our-url>";
    private static final String strEventOnFlagPlayerUpdate = "flag-player-update";
    private static final String strEventOnScoreUpdate = "on-score-update";
    //endregion

    //region PUBLIC_ATTRIBUTES
    //endregion

    //region PRIVATE_ATTRIBUTES
    private Socket serverSocket;{
        try {
            serverSocket = IO.socket(strUrlSocketServer);
        }catch (URISyntaxException e){}
    }
    private GameSession activeSession;
    private ListView lvNavBar;
    //endregion

    //region PUBLIC_METHODS
    /*
    Calls server verify credentials and returns a UserAccount object
     */
    public UserAccount login() {
        throw new UnsupportedOperationException();
    }
    /*
    Calls createGame() method on server
     */
    public void requestGameCreation() {
        throw new UnsupportedOperationException();
    }
    //endregion

    //region PRIVATE_METHODS
    private void handleLoginSuccess() {
        throw new UnsupportedOperationException();
    }
    private void handleLoginFailure() {
        throw new UnsupportedOperationException();
    }
    private void notifyUser(String message) {
        throw new UnsupportedOperationException();
    }
    /*
    Notifies the user via a toast message
     */
    private void notify(String message) {

    }
    //endregion

    //region EVENTS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //region DRAWER_SETUP
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        lvNavBar = (ListView)findViewById(R.id.lvNavBar);
        String[] navBarListItems = getResources().getStringArray(R.array.nav_bar_list_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,navBarListItems);
        lvNavBar.setAdapter(adapter);
        lvNavBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
        //endregion

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //region SOCKET.IO_SETUP
//        serverSocket.on(strEventOnFlagPlayerUpdate, onFlagPlayerUpdate);
//        serverSocket.on(strEventOnScoreUpdate,onScoreUpdate);
//        serverSocket.connect();
        //endregion
    }

    private void selectItemFromDrawer(int position) {
        String [] navItems = getResources().getStringArray(R.array.nav_bar_list_items);
        switch(position){
            case 0:
                System.out.println(navItems[position]+" was pressed in NavBar");
                Intent mainIntent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(mainIntent);
                break;
            case 1:
                System.out.println(navItems[position]+" was pressed in NavBar");
                Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView tvHome = (TextView)lvNavBar.getChildAt(0);
        tvHome.setTextColor(getResources().getColor(R.color.selectedPage));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        serverSocket.disconnect();
//        serverSocket.off(strEventOnFlagPlayerUpdate,onFlagPlayerUpdate);
    }

    /*
    Handle new information from the server about an on-going vote
    on a player's score
     */
    private Emitter.Listener onFlagPlayerUpdate = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            throw new UnsupportedOperationException();
        }
    };

    private Emitter.Listener onScoreUpdate = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            throw new UnsupportedOperationException();
        }
    };

    //endregion

}
