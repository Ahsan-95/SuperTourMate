package com.example.supertourmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView statusTV;
    private EditText emailET,passwordET;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTV = findViewById(R.id.statusTV);
        emailET = findViewById(R.id.emailET);
        passwordET =findViewById(R.id.passwordET);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){

        }

    }

    public void SignIn(View view) {
        String email = emailET.getText().toString();
        String pass = passwordET.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, pass)
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



    public void SignUp(View view) {
        String email = emailET.getText().toString();
        String pass = passwordET.getText().toString();
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
