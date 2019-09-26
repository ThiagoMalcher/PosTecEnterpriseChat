package com.example.postecenterprisechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.postecenterprisechat.activity.LoginActivity;
import com.example.postecenterprisechat.activity.RegisterUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLoginUser;
    private Button btnSignUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if button login pressed start activity login.
        btnLoginUser = (Button) findViewById(R.id.btnLoginUser);
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        //if button signup pressed start activity register.
        btnSignUser = (Button) findViewById(R.id.btnSiginUser);
        btnSignUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent signup = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(signup);
            }
        });

    }
}
