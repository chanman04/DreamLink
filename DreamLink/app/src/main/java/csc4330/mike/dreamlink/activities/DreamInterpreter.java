package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import butterknife.Bind;
import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.adapters.CommentAdapter;

/**
 * Created by Mike on 11/18/15.
 */
public class DreamInterpreter extends Activity {

    @Bind(R.id.toolbar)
    Toolbar mainToolbar;

    private TextView dreamTitle;
    private TextView dreamEntry;
    private TextView dreamCommentSubmit;
    private Button commentButton;
    private ParseObject dream;
    private ListView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream_interpret_layout);

        dreamTitle = (TextView) findViewById(R.id.dream_title);
        dreamEntry = (TextView) findViewById(R.id.dream_entry);
        dreamCommentSubmit = (TextView) findViewById(R.id.comment_submit_text);
        commentButton = (Button) findViewById(R.id.comment_submit_button);
        comments = (ListView) findViewById(R.id.comments_View);

        //dreamTitle = (cast as what? TextView?) findViewById(R.id.

        String parseObjId = getIntent().getStringExtra("parse_obj_id");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DREAM"); //which query to pull here?
        try {
            dream = query.get(parseObjId);
            dreamTitle.setText(dream.getString("DREAM_TITLE"));
            dreamEntry.setText(dream.getString("DREAM_ENTRY"));
            comments.setAdapter(new CommentAdapter(this,dream));
        } catch(ParseException e) {
            Toast.makeText(DreamInterpreter.this, "FAILED TO RETRIEVE DREAM", Toast.LENGTH_LONG).show();
        }

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject comment = new ParseObject("Comment");
                comment.put("text",dreamCommentSubmit.getText().toString());
                comment.put("commenter", ParseUser.getCurrentUser());
                comment.put("dream",dream);
                comment.saveInBackground(null);
            }
        });
    }
}
