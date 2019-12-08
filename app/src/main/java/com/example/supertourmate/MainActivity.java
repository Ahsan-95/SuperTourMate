package com.example.supertourmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView statusTV;
    private TextInputEditText emailIET,passwordIET;
    private MaterialButton signInB;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTV = findViewById(R.id.statusTV);
        emailIET = findViewById(R.id.emailET);
        passwordIET = findViewById(R.id.passwordET);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }


    }

    public void SignIn(View view) {
        String email = emailIET.getText().toString();
        String pass = passwordIET.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = firebaseAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            updateUI();

                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        setAuthError(e.getLocalizedMessage());
                    }

                });

    }



    public void SignUp(View view) {
        String email = emailIET.getText().toString();
        String pass = passwordIET.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = firebaseAuth.getCurrentUser();
                            updateUI();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        setAuthError(e.getLocalizedMessage());
                    }
                });

    }


    private void setAuthError(String localizedMessage) {
        statusTV.setText(localizedMessage);
    }

    private void updateUI() {
        String email = currentUser.getEmail();
        statusTV.setText("Logged in as"+email);
    }
}
