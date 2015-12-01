package csc4330.mike.dreamlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;

/**
 * This Java class sets the functionality for allowing the user to record their dreams and send them
 * to our cloud database Parse.
 */
public class RecordDream extends ActionBarActivity {

    @Bind(R.id.dream_title_ET) EditText dreamTitleET;
    @Bind(R.id.dream_ET) EditText recordDreamET;
    @Bind(R.id.submit_dream_button) Button submitDreamButton;

    private String dreamTitle = "";
    private String dreamEntry = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_dream);
        ButterKnife.bind(this);

        dreamTitleET.setHint("Give your dream a title");
        recordDreamET.setHint("Record your dream");

        //Button that takes all info from EditText fields in layout and creates a dream object
        submitDreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if(recordDreamET.getText().toString().isEmpty()){
                        recordDreamET.setError("You forgot to enter your dream!");

                    }else{
                        dreamTitle = (dreamTitleET.getText().toString());
                        dreamEntry = (recordDreamET.getText().toString());

                        ParseObject userDream = new ParseObject("DREAM");
                        userDream.put("DREAM_TITLE",dreamTitle);
                        userDream.put("DREAM_ENTRY",dreamEntry);
                        userDream.put("DREAMER", ParseUser.getCurrentUser());
                        userDream.saveInBackground();
                        Toast.makeText(RecordDream.this, "Thanks for sharing your dream", Toast.LENGTH_SHORT).show();
                        Intent feedIntent = new Intent(RecordDream.this, DreamFeed.class);
                        startActivity(feedIntent);
                    }

                }catch(Exception e){

                    e.printStackTrace();
                    Toast.makeText(RecordDream.this, "There was a error recording your dream",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        });
    }
}