package csc4330.mike.dreamlink.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Stack;

import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.components.Dream;

/**
 * Created by Mike on 10/20/15.
 */
public class DreamAdapter extends ArrayAdapter<Dream> {

    static Context context;
    static int layoutResourceId;
    Stack<Dream> data = new Stack<>();

    public DreamAdapter(Context context, int layoutResourceId, Stack<Dream> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        /*for (Dream d : this.data) {
            data.push(d);
        }
        ;*/
        this.data = data;
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DreamHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            //row.setMinimumHeight(200);
            holder = new DreamHolder();
            holder.dreamTitle = (TextView)row.findViewById(R.id.dreamTitle);
            holder.dreamEntry = (TextView)row.findViewById(R.id.dreamEntry);

            row.setTag(holder);
        }
        else
        {
            holder = (DreamHolder)row.getTag();
        }

        Dream dream = data.elementAt(position);
        holder.dreamTitle.setText(dream.getTitle());
        holder.dreamEntry.setText(dream.getDream());

        return row;
    }

    static class DreamHolder
    {
        TextView dreamTitle;
        TextView dreamEntry;
    }
}

