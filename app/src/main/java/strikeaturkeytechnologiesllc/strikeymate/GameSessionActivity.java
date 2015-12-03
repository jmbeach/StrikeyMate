package strikeaturkeytechnologiesllc.strikeymate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import none.strikeymatetemp.R;

public class GameSessionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lvNavBar;
    NavigationView navigationView;
    DrawerLayout drawer;
    Random randObj = new Random();

    HashMap<Integer, List<String>> scoresMap;
    List<String> scoreList;
    ExpandableListView scoreView;
    scoreAdapter scoreAdapterr;
    String[] playerNames;

    int frameNum=0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public enum Turn {
        John,
        Alex,
        Susan
    }
    public Turn turn = Turn.John;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scoreView = (ExpandableListView) findViewById(R.id.gameSessionView);


        turn = Turn.John;
        playerNames = new String[]{"John","Alex","Susan"};

        scoresMap = new HashMap<Integer, List<String>>();
        //scoreAdapterr = new scoreAdapter(this, scoresMap);
        //scoreView.setAdapter(scoreAdapterr);



        //region DRAWER_SETUP
        drawer = (DrawerLayout) findViewById(R.id.gsdrawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        lvNavBar = (ListView) findViewById(R.id.gsNavBar);
        String[] navBarListItems = getResources().getStringArray(R.array.game_nav_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, navBarListItems);
        lvNavBar.setAdapter(adapter);
        navigationView = (NavigationView) findViewById(R.id.gsnav_view);
        navigationView.setNavigationItemSelectedListener(this);
        lvNavBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });


        Button simScoreButton = (Button) findViewById(R.id.simScoreButton);
        simScoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int frameScore = 0;
                int firstBowl = randObj.nextInt(11);
                if (firstBowl == 10) {
                    //Strikey, Mate!
                    CharSequence text = "Strikey, mate!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();


                } else {
                    int secondBowl = randObj.nextInt(11 - firstBowl);
                    if (firstBowl + secondBowl == 10) {
                        //Sparey, Mate! get otta here
                        CharSequence text = "You got a spare!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                    } else {
                        //regular score less than 10
                        frameScore = firstBowl + secondBowl;
                        CharSequence text = "You scored " + frameScore;
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                }

                if(turn == Turn.John){
                    //new frame
                    frameNum++;
                    List<String> frame = new ArrayList<String>();
                    frame.add("John: "+frameScore);
                    scoresMap.put(frameNum,frame);
                } else if (turn == Turn.Alex) {
                    scoresMap.get(frameNum).add("Alex: "+frameScore);
                } else if (turn == Turn.Susan) {
                    scoresMap.get(frameNum).add("Susan: "+frameScore);
                }

                //TODO: Everything having to do with Game sessions. RIP Jared's brain
            }
        });

        Button reqHelpButton = (Button) findViewById(R.id.helpButton);
        reqHelpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CharSequence text = "An employee has been notified";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void selectItemFromDrawer(int position) {
        String[] navItems = getResources().getStringArray(R.array.game_nav_items);
        switch (position) {
            case 0:
                System.out.println(navItems[position] + " was pressed in NavBar. Closing Drawers");
//                Intent mainIntent = new Intent(MainActivity.this,MainActivity.class);
//                startActivity(mainIntent);
                drawer.closeDrawers();
                break;
            case 1:
                System.out.println(navItems[position] + " was pressed in NavBar");
                Intent tipIntent = new Intent(GameSessionActivity.this, SpareTipActivity.class);
                startActivity(tipIntent);
                break;
            case 2:
                System.out.println(navItems[position] + " was pressed in NavBar. Heading home.");
                Intent mainIntent = new Intent(GameSessionActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case 3:
                System.out.println(navItems[position] + " was pressed in NavBar");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView tvHome = (TextView) lvNavBar.getChildAt(0);
        tvHome.setTextColor(getResources().getColor(R.color.selectedPage));
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.gsdrawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GameSession Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://strikeaturkeytechnologiesllc.strikeymate/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GameSession Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://strikeaturkeytechnologiesllc.strikeymate/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


class scoreAdapter extends BaseExpandableListAdapter {

    Context ctx;
    HashMap<Integer, List<String>> scores;

    public scoreAdapter(Context ct, HashMap<Integer,List<String>> map){
        ctx = ct;
        scores = map;
    }


    @Override
    public int getGroupCount() {
        return scores.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return scores.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return scores.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {

        return scores.get(parent).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gs_parent_layout,parent,false);
        }
        TextView parentTextView = (TextView) convertView.findViewById(R.id.parent_txt);
        parentTextView.setTypeface(null, Typeface.BOLD);
        parentTextView.setText(groupTitle);
        return null;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertView, ViewGroup parentView) {
        String childTitle = (String) getChild(parent,child);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gs_child_layout, parentView, false);
        }
        TextView childTextView = (TextView) convertView.findViewById(R.id.child_txt);
        childTextView.setText(childTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}