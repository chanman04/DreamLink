package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import butterknife.Bind;
import csc4330.mike.dreamlink.R;

public class DreamView extends Activity {

    @Bind(R.id.toolbar) Toolbar mainToolbar;

    private TextView dreamTitle;
    private TextView dreamEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_view);

        dreamTitle = (TextView) findViewById(R.id.dream_title);
        dreamEntry = (TextView) findViewById(R.id.dream_entry);

        //Populates the layout with the correct parse dream object passed from dreamFeed
        String parseObjId = getIntent().getStringExtra("parse_obj_id");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DREAM"); //which query to pull here?
        query.getInBackground(parseObjId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject item, ParseException e) {
                if (e == null) {
                    // item was found
                    dreamTitle.setText(item.getString("DREAM_TITLE"));
                    dreamEntry.setText(item.getString("DREAM_ENTRY"));
                } else {
                    Toast.makeText(DreamView.this, "FAILED TO RETRIEVE DREAM", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
