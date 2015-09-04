package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;

/**
 * Created by Mike on 9/4/15.
 */
public class LoginScreen extends Activity {

    @Bind(R.id.user_ET) EditText userEditText;
    @Bind(R.id.password_ET) EditText passwordEditText;
    @Bind(R.id.email_ET) EditText emailEditText;
    @Bind(R.id.submit_button) Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//    ParseUser user = new ParseUser();
//    user.setUsername("my name");
//    user.setPassword("my pass");
//    user.setEmail("email@example.com");
//
//// other fields can be set just like with ParseObject
//    user.put("phone", "650-555-0000");
//
//    user.signUpInBackground(new SignUpCallback() {
//        public void done(ParseException e) {
//            if (e == null) {
//                // Hooray! Let them use the app now.
//            } else {
//                // Sign up didn't succeed. Look at the ParseException
//                // to figure out what went wrong
//            }
//        }
//    });

    }
}
