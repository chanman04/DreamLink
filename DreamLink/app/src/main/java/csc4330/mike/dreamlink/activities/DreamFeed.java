package csc4330.mike.dreamlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import csc4330.mike.dreamlink.R;

/**
 * Created by Mike on 9/4/15.
 */
public class DreamFeed extends ActionBarActivity{

    //@Bind(R.id.toolbar) Toolbar mainToolbar;
    //@Bind(R.id.shopping_expandable_list)ExpandableListView shoppingListDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_feed);
        //ButterKnife.bind(this);

        //setSupportActionBar(mainToolbar);
        //getSupportActionBar().setTitle("Dream Feed");

        Button back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(DreamFeed.this, LoginScreen.class);
                startActivity(backIntent);
            }
        });

    }

}
