package csc4330.mike.dreamlink.activities;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

import csc4330.mike.dreamlink.components.Dream;

/**
 * This application class initializes the app and performs intensive data processes before we display
 * the first activity --> Dream Feed activity.
 */
public class DreamLinkApplication extends Application {

    private String username;

    private static DreamLinkApplication singleInstance = null;

    public String getUsername() {
        return username;
    }

    public void setWagerLogLV(String user) {
        this.username = user;
    }

    public static DreamLinkApplication getInstance()
    {
        return singleInstance;
    }

    public void onCreate() {

        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Dream.class);
        ParseObject.registerSubclass(Hashtag.class);
        Parse.initialize(this, "2BpAZP02XxiKszInLiS1ZTdGRf83pCfGSFhDCFX2", "2C8XUbmEYZLKoLejypoMKivrhVdVqWciE82PVHOA");
        FacebookSdk.sdkInitialize(this);
        ParseFacebookUtils.initialize(this);

        final Intent intent = new Intent();
        intent.setClass(DreamLinkApplication.this, DreamFeed.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);

        //Checks if user has already signed in previously
        //If true: Bypass LoginScreen
        //If false: go to LoginScreen
//        if(ParseUser.getCurrentUser() != null){
//            if(ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())) {
//                Log.d("Log", "Auto logged in through FB");
//                startActivity(intent);
//            }
//        }
    }
}
