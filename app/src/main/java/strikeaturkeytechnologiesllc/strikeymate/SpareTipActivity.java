package strikeaturkeytechnologiesllc.strikeymate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import none.strikeymatetemp.R;

public class SpareTipActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private ListView lvNavBar;
    NavigationView navigationView;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_tip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.csdrawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        lvNavBar = (ListView) findViewById(R.id.csNavBar);
        String[] navBarListItems = getResources().getStringArray(R.array.game_nav_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, navBarListItems);
        lvNavBar.setAdapter(adapter);
        navigationView = (NavigationView) findViewById(R.id.csnav_view);
        navigationView.setNavigationItemSelectedListener(this);
        lvNavBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });


        final TextView textBox = (TextView) findViewById(R.id.textBox);
        final Button tipButton = (Button) findViewById(R.id.tipButton);


        tipButton.setOnClickListener(new View.OnClickListener() {
            int buttonCount = 1;

            @Override
            public void onClick(View v) {

                //reset things for the getTip view
                if (buttonCount == 0) {
                    buttonCount = 1;
                    String sugg = "";
                    textBox.setText(sugg);
                    tipButton.setText("Get tip");
                } else {
                    buttonCount = 0;

                    //get the current pin setup from the checkboxes
                    int pinCount = 0;
                    boolean[] pins = new boolean[10];
                    for(int i = 0; i < 10; i++) {
                        pins[i] = false;
                    }

                    CheckBox cb = (CheckBox) findViewById(R.id.radioButton);
                    if (cb.isChecked()) {
                        pins[9] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton2);
                    if (cb.isChecked()) {
                        pins[8] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton3);
                    if (cb.isChecked()) {
                        pins[7] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton4);
                    if (cb.isChecked()) {
                        pins[6] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton5);
                    if (cb.isChecked()) {
                        pins[5] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton6);
                    if (cb.isChecked()) {
                        pins[4] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton7);
                    if (cb.isChecked()) {
                        pins[3] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton8);
                    if (cb.isChecked()) {
                        pins[2] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton9);
                    if (cb.isChecked()) {
                        pins[1] = true;
                        pinCount++;
                    }
                    cb = (CheckBox) findViewById(R.id.radioButton10);
                    if (cb.isChecked()) {
                        pins[0] = true;
                        pinCount++;
                    }

                    int headPin = getHeadPin(pins);

                    String sugg = "Gee, this one is tough!";
                    if (pinCount == 10) {
                        //return strike sugg
                        sugg = "Righties: Throw the ball between the 1 and 3 pins\nLefties: Throw the ball between the 1 and 2 pins";
                    } else if (pinCount == 1) {
                        //return single pin sugg
                        sugg = "Stick it to the pin!";
                    }else if (pinCount == 0) {
                        sugg = "There aren't any pins there to hit!";
                    }
                    else if (pinCount == 2 && (pins[6] && pins[9])) {
                        //return 7-10 sugg
                        sugg = "Hit the 7 on the left side or the 10 on the right side and hope for the best either way";
                    } else if (pinCount == 4 && (pins[0] && pins[1] && pins[3] && pins[6])) {
                        //return left4 sugg
                        sugg = "Righties: Hit the left side of all of them\nLefties: Throw the ball between the 1 and 2 pins";
                    } else if (pinCount == 4 && (pins[0] && pins[2] && pins[5] && pins[9])) {
                        //return right4 sugg
                        sugg = "Righties: Throw the ball between the 1 and 3 pins\nLefties: Hit the right side of all of them";
                    } else if (pinCount == 4 && (pins[3] && pins[5] && pins[6] && pins[9])) {
                        //return 4-7 6-10 split sugg
                        sugg = "Righties: Hit the 6 on the far right and 10 right after\nLefties: Hit the 4 on the far left and the 7 right after";
                    } else if (pinCount == 3 && (pins[4] && pins[6] && pins[9])) {
                        //return trident sugg
                        sugg = "I've got nothing. How did you even do that?!";
                    }

                    textBox.setText(sugg);
                    tipButton.setText("Got it!");
                }
            }
        });
    }

    private int getHeadPin(boolean[] standingPins) {
        for(int i = 0; i < 10; i++) {
            if (standingPins[i]) {
                return i;
            }
        }

        return -1;
    }



    private void selectItemFromDrawer(int position) {
        String [] navItems = getResources().getStringArray(R.array.game_nav_items);
        switch(position){
            case 0:
                System.out.println(navItems[position] + " was pressed in NavBar. Closing Drawers");
                Intent mainIntent = new Intent(SpareTipActivity.this,GameSessionActivity.class);
                startActivity(mainIntent);
                break;
            case 1:
                System.out.println(navItems[position] + " was pressed in NavBar");
                drawer.closeDrawers();
                break;
            case 2:
                System.out.println(navItems[position] + " was pressed in NavBar. Heading home.");
                Intent tipIntent = new Intent(SpareTipActivity.this,MainActivity.class);
                startActivity(tipIntent);
                break;
            case 3:
                System.out.println(navItems[position] + " was pressed in NavBar");
                break;
            default:
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView tvHome = (TextView)lvNavBar.getChildAt(1);
        tvHome.setTextColor(getResources().getColor(R.color.selectedPage));
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.csdrawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
