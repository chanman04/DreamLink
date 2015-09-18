package csc4330.mike.dreamlink.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import csc4330.mike.dreamlink.R;
import csc4330.mike.dreamlink.components.Contact;

/**
 * Created by Mike on 9/4/15.
 */
public class LoginScreen extends ActionBarActivity {

    @Bind(R.id.user_ET)
    EditText userEditText;
    @Bind(R.id.password_ET)
    EditText passwordEditText;
    @Bind(R.id.email_ET)
    EditText emailEditText;
    @Bind(R.id.submit_button)
    Button submitButton;
    @Bind(R.id.fb_button) LoginButton facebookButton;

    private String userField ="";
    private String passwordField ="";
    private String emailField ="";

    Context context;

    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        //facebookButton.setReadPermissions("user_friends");
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);

        userEditText.setHint("username");
        passwordEditText.setHint("password");
        emailEditText.setHint("email");



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Contact contact = new Contact();

                    //Check for a username
                    if (userEditText.getText().toString().isEmpty()) {
                        userEditText.setError("UserName cannot be blank");
                        //Check for password
                    } else if (passwordEditText.getText().toString().isEmpty()) {
                        passwordEditText.setError("Password field cannot not be blank");
                        //Check for email
                    } else if (emailEditText.getText().toString().isEmpty()) {
                        emailEditText.setError("Email field cannot be blank");
                        //Create the contact and make it into ParseUser
                    } else if (emailCheck(emailEditText.getText().toString()) == false) {
                        emailEditText.setError("Your entry is not a valid email address");

                    }else {

                        userField = userEditText.getText().toString();
                        passwordField = userEditText.getText().toString();
                        emailField = userEditText.getText().toString();


//                        userEditText.setText(userField);
//                        passwordEditText.setText(passwordField);
//                        emailEditText.setText(emailField);

                        contact.setUserName(userField);
                        contact.setUserPassword(passwordField);
                        contact.setUserEmail(emailField);


                        Toast.makeText(LoginScreen.this, "Your account was created successfully", Toast.LENGTH_SHORT).show();
                        //createParseUser(contact);

                    }
                } catch (Exception e) {

                    Toast.makeText(LoginScreen.this, "Please correct your entries and resubmit", Toast.LENGTH_SHORT).show();
                     return;
                }

            }

        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Intent feedIntent = new Intent(LoginScreen.this, DreamFeed.class);
                                startActivity(feedIntent);
                            }

                            @Override
                            public void onCancel() {
                                Intent mainIntent = new Intent(LoginScreen.this, LoginScreen.class);
                                startActivity(mainIntent);
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                Intent mainIntent = new Intent(LoginScreen.this, LoginScreen.class);
                                startActivity(mainIntent);
                                Toast.makeText(context, "An error has occured", Toast.LENGTH_SHORT);
                            }
                        });
            }
        });
    }

    public static ParseUser createParseUser(Contact contact) {

        ParseUser user = new ParseUser();
        user.setUsername(contact.getUserName());
        user.setPassword(contact.getUserPassword());
        user.setEmail(contact.getUserEmail());


        // other fields can be set just like with ParseObject
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {

            }

            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });

        return user;
    }

    public static boolean emailCheck(String email) {

        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        ;
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}

