package csc4330.mike.dreamlink.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;
import java.util.Stack;

import butterknife.Bind;
import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.adapters.DreamAdapter;
import csc4330.mike.dreamlink.components.Dream;

/**
 * Created by Mike on 9/4/15.
 */
public class DreamFeed extends ActionBarActivity{

    @Bind(R.id.toolbar) Toolbar mainToolbar;
    private ListView dreamLogLV;
    private ParseQueryAdapter<ParseObject> mainAdapter;
    private DreamAdapter dreamAdapter;


    private Stack<Dream> dreamLog = new Stack<>();
    public DreamAdapter dreamAdapter;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_feed);


            //Get a instance off the app to pull the global username we are storing for this app user
            DreamLinkApplication dreamLink = DreamLinkApplication.getInstance();
            userName = dreamLink.getUsername();

        // Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Todo");
        mainAdapter.setTextKey("title");
        mainAdapter.setImageKey("image");

        // Initialize the subclass of ParseQueryAdapter
        dreamAdapter = new DreamAdapter(this);

        // Initialize ListView and set initial view to mainAdapter
        dreamLogLV = (ListView) findViewById(R.id.dream_log_LV);
        dreamLogLV.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        dreamLogLV.setAdapter(dreamAdapter);
        dreamAdapter.loadObjects();

            




        }


    }
