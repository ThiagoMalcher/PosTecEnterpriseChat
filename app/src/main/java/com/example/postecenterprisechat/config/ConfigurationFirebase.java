package com.example.postecenterprisechat.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigurationFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth authentication;

    //return instance firebase firebasedatabse.
    public static DatabaseReference getFirebaseDatabase()
    {
        if(database == null)
        {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

    //return instance firebase firebaseauth.
    public static FirebaseAuth getFriabaseAuthentication()
    {
        if(authentication == null)
        {
            authentication =FirebaseAuth.getInstance();
        }
        return authentication;
    }
}
