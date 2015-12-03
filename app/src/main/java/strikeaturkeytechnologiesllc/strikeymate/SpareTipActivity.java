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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        lvNavBar = (ListView)findViewById(R.id.csNavBar);
        String[] navBarListItems = getResources().getStringArray(R.array.game_nav_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,navBarListItems);
        lvNavBar.setAdapter(adapter);
        navigationView = (NavigationView) findViewById(R.id.csnav_view);
        navigationView.setNavigationItemSelectedListener(this);
        lvNavBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.csdrawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
