package com.example.loginsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView goToLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBarSignup;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        findViews();
        listener();
    }

    private void listener() {
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRegMethod(View view) {
        final String myEmail = email.getText().toString();
        final String myPassword = password.getText().toString();
        btnSignup.setEnabled(false);
        progressBarSignup.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.putExtra("set_email",myEmail);
                            startActivity(intent);

                        } else {
                            Toasty.error(SignUpActivity.this, "Please try again.", Toast.LENGTH_SHORT, true).show();

                        }

                    }
                });

    }

    private void findViews() {
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        goToLogin = findViewById(R.id.go_to_login);
        progressBarSignup = findViewById(R.id.progress_signup);
        btnSignup = findViewById(R.id.buttonSignup);

    }
}