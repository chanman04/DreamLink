package csc4330.mike.dreamlink.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.Stack;

import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.components.Dream;

/**
 * Created by Mike on 10/20/15.
 */
public class DreamAdapter extends ParseQueryAdapter<ParseObject>{
    public DreamAdapter(Context context, final String username){
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("DREAM");
                //query.whereEqualTo("username", username); //username not yet linked to dreams on parse?
                query.whereEqualTo("test", true); //just testing
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
            v = View.inflate(getContext(), R.layout.row_layout_dreamlv, null);
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.dream_title);
        titleTextView.setText(object.getString("DREAM_TITLE"));

        //Add the entry view
        TextView entryTextView = (TextView) v.findViewById(R.id.dream_entry);
        entryTextView.setText(object.getString("DREAM_ENTRY"));

        return v;
    }
}
