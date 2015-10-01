package csc4330.mike.dreamlink.activities;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ParseUser;

/**
 * Created by Mike on 9/4/15.
 */
public class DreamLinkApplication extends Application {

//    public void onCreate() {
//        Parse.initialize(this, "2BpAZP02XxiKszInLiS1ZTdGRf83pCfGSFhDCFX2", "2C8XUbmEYZLKoLejypoMKivrhVdVqWciE82PVHOA");
//    }

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

        if(ParseUser.getCurrentUser() != null){
            if(ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())) {
                Log.d("Log","Auto logged in through FB");
                startActivity(intent);
            }
        }

    }
}
