package csc4330.mike.dreamlink.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.awt.font.TextAttribute;

import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;

/**
 * Created by Mike on 9/20/15.
 */
public class SignupActivity extends ActionBarActivity {

    @Bind(R.id.user_ET) EditText usernameET;
    @Bind(R.id.password_ET) EditText passwordET;
    @Bind(R.id.email_ET) EditText emailET;
    @Bind(R.id.submit_button) Button submitButton;
    @Bind(R.id.pick_login_TV) TextView pickLoginTV;
    @Bind(R.id.login_button) Button loginButton;
    @Bind(R.id.error_TV)TextView errorResponseTV;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        usernameET.setHint("Username");
        usernameET.setHint("Password");
        usernameET.setHint("Email");



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();

                user.setUsername(usernameET.getText().toString());
                user.setPassword(passwordET.getText().toString());
                user.setEmail(emailET.getText().toString());

                errorResponseTV.setText("");
                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {

                        if (e == null){
                            Intent feedIntent = new Intent(SignupActivity.this, DreamFeed.class);
                            startActivity(feedIntent);
                            finish();

                        }else{
                            switch(e.getCode()){
                                case ParseException.USERNAME_MISSING:
                                    usernameET.setError("You must supply a username");
                                    break;
                                case ParseException.PASSWORD_MISSING:
                                    passwordET.setError("You must supply a password");
                                    break;
                                case ParseException.EMAIL_MISSING:
                                    emailET.setError("You must supply a email address");
                                    break;
                            }

                        }

                    }
                });

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent feedIntent = new Intent(SignupActivity.this, LoginScreen.class);
                startActivity(feedIntent);
                finish();
            }
        });

    }
    public void signup(String username, String pass, String email){

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(pass);;
        user.setEmail(email);



    }

}
