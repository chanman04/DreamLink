package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import butterknife.Bind;
import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.adapters.CommentAdapter;

/*
* This class handles the functionality for the activity created after a user clicks on a specific dream
* in our ListView. This class sets the Dream objects title and entry and also handles querying for
* the comments within the database related to a specific Dream object.
 */

public class DreamView extends Activity {

    @Bind(R.id.toolbar) Toolbar mainToolbar;

    private TextView dreamTitle;
    private TextView dreamEntry;
    private TextView dreamCommentSubmit;
    private Button commentButton;
    private ParseObject dream;
    private ListView comments;
    private String parseObjId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_view);

        dreamTitle = (TextView) findViewById(R.id.dream_title);
        dreamEntry = (TextView) findViewById(R.id.dream_entry);
        dreamCommentSubmit = (TextView) findViewById(R.id.comment_submit_text);
        commentButton = (Button) findViewById(R.id.comment_submit_button);
        comments = (ListView) findViewById(R.id.comments_View);

        //Populates the layout with the correct parse dream object passed from dreamFeed
        parseObjId = getIntent().getStringExtra("parse_obj_id");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DREAM"); //which query to pull here?
        try {
            dream = query.get(parseObjId);
            dreamTitle.setText(dream.getString("DREAM_TITLE"));
            dreamEntry.setText(dream.getString("DREAM_ENTRY"));
            comments.setAdapter(new CommentAdapter(this,dream));
        } catch(ParseException e) {
            Toast.makeText(DreamView.this, "FAILED TO RETRIEVE DREAM", Toast.LENGTH_LONG).show();
        }

        //Creates a comment object, connects it to the dream and submits it to parse
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject comment = new ParseObject("Comment");
                comment.put("text", dreamCommentSubmit.getText().toString());
                comment.put("commenter", ParseUser.getCurrentUser());
                comment.put("dream", dream);
                comment.saveInBackground(null);

                Toast.makeText(DreamView.this, "Your comment was submitted", Toast.LENGTH_LONG).show();

                //parseObjId = getIntent().getStringExtra("parse_obj_id");

                Intent intent = new Intent(DreamView.this, DreamView.class);
                intent.putExtra("parse_obj_id", parseObjId);
                startActivity(intent);
            }
        });
    }
    
    public void onBackPressed()
    {
        Intent intent = new Intent(DreamView.this, DreamFeed.class);
        startActivity(intent);
    }
}
