package com.example.postecenterprisechat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.postecenterprisechat.R;
import com.example.postecenterprisechat.config.ConfigurationFirebase;
import com.example.postecenterprisechat.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {

    private EditText txtRegisterName, txtRegisterEmail, txtPassword;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        txtRegisterName = (EditText) findViewById(R.id.txtRegName);
        txtRegisterEmail = (EditText) findViewById(R.id.txtRegEmail);
        txtPassword = (EditText) findViewById(R.id.txtRegPassword);

    }

    //function validate user
    public void validateRegisterUser(View view) {
        String Name = txtRegisterName.getText().toString();
        String Email = txtRegisterEmail.getText().toString();
        String Password = txtPassword.getText().toString();

        if (!(Name.isEmpty() || Email.isEmpty() || Password.isEmpty())) {
            //use java class in folder model.
            User user = new User();
            user.setName(Name);
            user.setEmail(Email);
            user.setPassword(Password);

            registerUser(user);
        } else {
            Toast.makeText(RegisterUser.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //function register user
    public void registerUser(User user) {
        authentication = ConfigurationFirebase.getFriabaseAuthentication();
        authentication.createUserWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                    finish();
                    openMainActivity();
                } else {
                    String logException = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        logException = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        logException = "Por favor, digite um e-mail válido!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        logException = "Esta conta já foi cadastrada";
                    } catch (Exception e) {
                        logException = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),
                            logException, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openMainActivity() {
        Intent intentOpen = new Intent(RegisterUser.this, ChatActivity.class);
        startActivity(intentOpen);
    }
}
