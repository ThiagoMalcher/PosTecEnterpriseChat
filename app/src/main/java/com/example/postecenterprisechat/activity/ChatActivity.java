package com.example.postecenterprisechat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.postecenterprisechat.R;
import com.example.postecenterprisechat.config.ConfigurationFirebase;
import com.example.postecenterprisechat.fragement.ChatFragment;
import com.example.postecenterprisechat.fragement.ContactsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class ChatActivity extends AppCompatActivity {

    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        authentication = ConfigurationFirebase.getFriabaseAuthentication();

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("ChatApp");
        setSupportActionBar(toolbar);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.fragmentchat, ChatFragment.class)
                        .add(R.string.fragmentcontacts, ContactsFragment.class)
                        .create()
        );

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuExit:
                turnOffUser();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void turnOffUser() {
        try {
            authentication.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
