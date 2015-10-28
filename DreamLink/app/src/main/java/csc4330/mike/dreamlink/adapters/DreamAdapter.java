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
public class DreamAdapter extends ParseQueryAdapter<Dream> {

    public DreamAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public class WagerLogAdapter extends ParseQueryAdapter<ParseObject> {

        public WagerLogAdapter(Context context) {
            // Use the QueryFactory to construct a PQA that will only show
            // Todos marked as high-pri
            super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
                public ParseQuery create() {
                    ParseQuery query = new ParseQuery("DREAM");
                    return query;
                }
            });
        }

        // Customize the layout by overriding getItemView
        @Override
        public View getItemView(ParseObject object, View v, ViewGroup parent) {
            if (v == null) {
                v = View.inflate(getContext(), R.layout.row_layout_dreamlv, null);
            }

            super.getItemView(object, v, parent);

            // Add the title view
            TextView titleTextView = (TextView) v.findViewById(R.id.dream_title);
            titleTextView.setText(object.getString("title"));

            // Add a reminder of how long this item has been outstanding
            TextView timestampView = (TextView) v.findViewById(R.id.dream_entry);
            timestampView.setText(object.getCreatedAt().toString());
            return v;
        }

    }
}
