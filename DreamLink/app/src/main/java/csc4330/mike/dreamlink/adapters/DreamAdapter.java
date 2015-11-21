package csc4330.mike.dreamlink.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import csc4330.mike.dreamlink.R;

/**
 * Created by Mike on 10/20/15.
 */
public class DreamAdapter extends ParseQueryAdapter<ParseObject>{
    public DreamAdapter(Context context, final String username){
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("DREAM");
                //query.whereEqualTo("username", username); //username not yet linked to dreams on parse?
                query.whereEqualTo("DREAMER", ParseUser.getCurrentUser()); //just testing
                //need to link dreams to users
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent){
        //View row = convertView;
        //DreamHolder holder = null;
        if(v == null) {
            v = View.inflate(getContext(), R.layout.dream_layout, null);
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.dreamTitle);
        titleTextView.setText(object.getString("DREAM_TITLE"));

        //Add the entry view
        TextView entryTextView = (TextView) v.findViewById(R.id.dreamEntry);
        entryTextView.setText(object.getString("DREAM_ENTRY"));

        return v;
    }
}
