package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;


import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.adapters.DreamAdapter;

/**
 *This class is for handling the functionalty of our opening page after the login screen. The DreamFeed
 * activity is the main page for navigating around the app. It also displays dreams from the user's social circle.
 */
public class DreamFeed extends Activity{

    @Bind(R.id.toolbar) Toolbar mainToolbar;
    private DreamAdapter dreamAdapter;
    private ListView listView;
    private Button createDreamButton;
    private String userName = "oLzUtZyxLQ7WoglqGlMk9BRzN";

    //NavDrawer Initialization
    @Bind (R.id.nav_drawer_layout) DrawerLayout navDrawerLayout;
    private ActionBarDrawerToggle navDrawerToggle;
    @Bind (R.id.nav_drawer_list) ListView navDrawerList;
    private ArrayAdapter<String> navDrawerAdapter;
    private String [] navTitlesAdapter = {"Record Your Dream","Personal Dream Feed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_feed);
        ButterKnife.bind(this);

        //NavDrawer setup the views
        navDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        navDrawerLayout.setDrawerListener(navDrawerToggle);

        navDrawerAdapter = new ArrayAdapter<String>( DreamFeed.this, android.R.layout.simple_list_item_1, navTitlesAdapter);
        navDrawerList.setAdapter(navDrawerAdapter);

        navDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, mainToolbar, R.string.drawer_open, R.string.drawer_close){

            //Method that handles open/close of drawer
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        navDrawerLayout.setDrawerListener(navDrawerToggle);
        navDrawerList.setOnItemClickListener(new NavDrawerListener());



        dreamAdapter = new DreamAdapter(this, userName);
        listView = (ListView) findViewById(R.id.dream_list);
        listView.setAdapter(dreamAdapter);
        dreamAdapter.loadObjects();

        //if item in list view is clicked, it brings user to dreamView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String parseObjectId = dreamAdapter.getItem(position).getObjectId();

                Intent intent = new Intent(DreamFeed.this, DreamView.class);
                intent.putExtra("parse_obj_id", parseObjectId);
                startActivity(intent);
            }
        });

        //End of onCreate
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class NavDrawerListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }
        //Method detailing functionality for click
        private void selectItem(int position){

            switch (position) {
                case 0:
                    Intent menuNavIntent = new Intent(DreamFeed.this, RecordDream.class);
                    startActivity(menuNavIntent);
                    break;

                case 1:
                    Intent locationNavIntent = new Intent(DreamFeed.this, UserDreamFeed.class);
                    startActivity(locationNavIntent);
                    break;

                default:
            }

        }
    }
}
