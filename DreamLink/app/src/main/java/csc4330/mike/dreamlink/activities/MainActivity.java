package csc4330.mike.dreamlink.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import csc4330.mike.dreamlink.R;

public class MainActivity extends TabActivity {

    TabHost tabHost;
    protected void onCreate(Bundle savedInstanceState) {

        // TabHost Stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();
        setTabs();
    }

    /*
    Create tabs and link them to their activities.
     */
    private void setTabs() {
        addTab("DreamFeed", R.drawable.home, DreamFeed.class);
        addTab("Search", R.drawable.search, SearchActivity.class);
        addTab("Upload", R.drawable.blue_cloud, SearchActivity.class);
        addTab("Profile", R.drawable.user, ProfileActivity.class);
        addTab("More", R.drawable.more, SearchActivity.class);
    }

    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }

    public void openDreamUploadActivity(View b) {
        Intent intent = new Intent(this, RecordDream.class);
        startActivity(intent);
    }
}
