package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.content.Intent;
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
public class DreamFeed extends Activity{

    @Bind(R.id.toolbar) Toolbar mainToolbar;
    private DreamAdapter dreamAdapter;
    private ListView listView;
    private Button createDreamButton;
    private String userName = "oLzUtZyxLQ7WoglqGlMk9BRzN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream_view);
        //ButterKnife.bind(this);

        dreamAdapter = new DreamAdapter(this, userName);
        //dreamFragment = new DreamFragment();
        //dreamFragment.setArguments(???);

        listView = (ListView) findViewById(R.id.dream_list);
        listView.setAdapter(dreamAdapter);
        dreamAdapter.loadObjects();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String item = (String) parent.getItemAtPosition(position); this doesnt work
                //String item = dreamAdapter.getItem(position).toString();
                //Toast.makeText(DreamLog.this, "CLICK: "+item, Toast.LENGTH_SHORT).show(); //how to get actual object values and stuff?
                //below change RecordDream.class to whatever Dream Interpretation class that is made

                String parseObjectId = dreamAdapter.getItem(position).getObjectId();

                Intent intent = new Intent(DreamFeed.this, DreamInterpreter.class);
                intent.putExtra("parse_obj_id", parseObjectId);
                startActivity(intent);

                //or just switch adapter to new layout? what do?
            }
        });

    }
}
