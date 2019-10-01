package com.example.postecenterprisechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.postecenterprisechat.activity.ChatActivity;
import com.example.postecenterprisechat.activity.LoginActivity;
import com.example.postecenterprisechat.activity.RegisterUser;
import com.example.postecenterprisechat.config.ConfigurationFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLoginUser;
    private Button btnSignUser;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authentication = ConfigurationFirebase.getFriabaseAuthentication();

        //if button login pressed start activity login.
        btnLoginUser = (Button) findViewById(R.id.btnLoginUser);
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                openLoginActivity();
            }
        });
        //if button signup pressed start activity register.
        btnSignUser = (Button) findViewById(R.id.btnSiginUser);
        btnSignUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                openSignupActivity();
            }
        });
    }

    public void openLoginActivity() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }

    public void openSignupActivity() {
        Intent signup = new Intent(MainActivity.this, RegisterUser.class);
        startActivity(signup);
    }

    public void openChatActivity() {
        Intent signup = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(signup);
    }

    //get user actual and send user for Chat Activity
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser actualUser = authentication.getCurrentUser();
        if (actualUser != null) {
            openChatActivity();
        }
    }
}
