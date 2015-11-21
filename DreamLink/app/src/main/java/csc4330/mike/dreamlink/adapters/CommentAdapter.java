package csc4330.mike.dreamlink.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import csc4330.mike.dreamlink.R;

/**
 * Created by Matthew Wolff on 11/20/2015.
 */
public class CommentAdapter extends ParseQueryAdapter<ParseObject> {
    public CommentAdapter(Context context, final ParseObject dream){
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("Comment");
                query.whereEqualTo("dream", dream);
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent){
        //TODO: Do actual views
        //View row = convertView;
        //DreamHolder holder = null;
        if(v == null) {
            v = View.inflate(getContext(), R.layout.dream_layout, null);
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.dreamTitle);
        titleTextView.setText(object.getParseUser("dreamer").getString("username"));

        //Add the entry view
        TextView entryTextView = (TextView) v.findViewById(R.id.dreamEntry);
        entryTextView.setText(object.getString("text"));

        return v;
    }
}
