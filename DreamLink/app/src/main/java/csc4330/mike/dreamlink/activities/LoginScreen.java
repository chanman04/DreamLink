package csc4330.mike.dreamlink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;

/**
 * Created by Mike on 9/4/15.
 */
public class LoginScreen extends ActionBarActivity {

    @Bind(R.id.user_ET) EditText userEditText;
    @Bind(R.id.password_ET) EditText passwordEditText;
    @Bind(R.id.email_ET) EditText emailEditText;
    @Bind(R.id.submit_button) Button submitButton;

    private String userField ="";
    private String passwordField ="";
    private String emailField ="";

    private LoginButton facebookLoginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_login);

        facebookLoginButton = (LoginButton)findViewById(R.id.fb_button);

        //User clicks Facebook button and attempts to sign in with facebook
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginScreen.this,
                        Arrays.asList("email", "user_friends", "public_profile"), new LogInCallback() {
                    @Override
                    public void done(final ParseUser user, com.parse.ParseException e) {
                        //checks if there if login to facebook was a failure
                        if (user == null) {
                            Log.d("MyApp", "The user cancelled the Facebook login.");

                            Toast.makeText(getApplicationContext(), "Log-out from Facebook and try again please!", Toast.LENGTH_SHORT).show();

                            ParseUser.logOut();

                        //checks if facebook user is new
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");

                            //checks if facebook user is not linked to user in parse
                            //if not, logs in with facebook user and creates user in parse
                            if (!ParseFacebookUtils.isLinked(user)) {
                                ParseFacebookUtils.linkWithReadPermissionsInBackground(user, LoginScreen.this,
                                        Arrays.asList("email", "user_friends", "public_profile"), new SaveCallback() {
                                    @Override
                                    public void done(com.parse.ParseException e) {

                                        if (ParseFacebookUtils.isLinked(user)) {
                                            Log.d("MyApp", "User logged in with Facebook!");
                                            startActivity(new Intent(LoginScreen.this, DreamFeed.class));
                                        }
                                    }
                                });

                            //if new facebook user exists in parse, proceed to dreamfeed
                            } else {
                                startActivity(new Intent(LoginScreen.this, DreamFeed.class));
                            }

                        //facebook user is not new
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");

                            //checks if facebook user is linked to user in parse
                            if (!ParseFacebookUtils.isLinked(user)) {
                                ParseFacebookUtils.linkWithReadPermissionsInBackground(user, LoginScreen.this,
                                        Arrays.asList("email", "user_friends", "public_profile"), new SaveCallback() {
                                    @Override
                                    public void done(com.parse.ParseException e) {
                                        if (ParseFacebookUtils.isLinked(user)) {
                                            Log.d("MyApp", "User logged in with Facebook!");
                                            startActivity(new Intent(LoginScreen.this, DreamFeed.class));
                                        }
                                    }
                                });

                            //Facebook user is not new and user exists in parse
                            //proceed to dreamFeed
                            }startActivity(new Intent(LoginScreen.this, DreamFeed.class));
                        }
                    }
                });
            }
        });

        ButterKnife.bind(this);

        userEditText.setHint("username");
        passwordEditText.setHint("password");
        emailEditText.setHint("email");

        //creates parse user after entering in the fields
        //User signs in and is redirected to DreamFeed

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();
                userField = userEditText.getText().toString();
                passwordField = passwordEditText.getText().toString();
                emailField = emailEditText.getText().toString();

                createParseUser(userField, passwordField, emailField);
            }
        });
    }

    //Creates parse user with information filled out by the user in the EditTexts
    public void createParseUser(String username, String password, String email) {

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {

                if (e == null) {
                    Toast.makeText(LoginScreen.this, "Your account was created successfully!", Toast.LENGTH_SHORT).show();
                    Intent feedIntent = new Intent(LoginScreen.this, DreamFeed.class);
                    startActivity(feedIntent);
                } else {
                    Toast.makeText(LoginScreen.this, "Parse didn't create your account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Checks whether email entered is a valid mailing address
    public static boolean emailCheck(String email) {

        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}

