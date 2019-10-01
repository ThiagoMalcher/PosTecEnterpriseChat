package com.example.postecenterprisechat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.postecenterprisechat.MainActivity;
import com.example.postecenterprisechat.R;
import com.example.postecenterprisechat.config.ConfigurationFirebase;
import com.example.postecenterprisechat.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText txtLoginEmail, txtLoginPassword;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLoginEmail = (EditText) findViewById(R.id.txtLogEmail);
        txtLoginPassword = (EditText) findViewById(R.id.txtLogPassword);

        authentication = ConfigurationFirebase.getFriabaseAuthentication();

    }

    public void loginUser(User user) {
        authentication.signInWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,
                            "Conectado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                    openMainActivity();
                } else {
                    String logException = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        logException = "Usuário não está cadastrado!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        logException = "E-mail e senha não correspondem";
                    } catch (Exception e) {
                        logException = "Erro ao logar com esse usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),
                            logException, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validateUser(View view) {
        String Email = txtLoginEmail.getText().toString();
        String Password = txtLoginPassword.getText().toString();

        if (!(Email.isEmpty() || Password.isEmpty())) {
            User user = new User();
            user.setEmail(Email);
            user.setPassword(Password);
            loginUser(user);
        } else {
            Toast.makeText(LoginActivity.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void openMainActivity() {
        Intent intentOpen = new Intent(LoginActivity.this, ChatActivity.class);
        startActivity(intentOpen);
    }
}
