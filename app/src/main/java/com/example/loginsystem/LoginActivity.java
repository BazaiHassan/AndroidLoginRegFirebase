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
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail,edtPassword;
    private FirebaseAuth mAuth;
    private TextView goToSignup;
    private ProgressBar progressBarLogin;
    private String setEmail;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        findViews();
        listener();
    }

    private void listener() {
        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        btnLogin = findViewById(R.id.buttonLogin);
        edtEmail = findViewById(R.id.editTextTextEmailAddress);
        edtPassword = findViewById(R.id.editTextTextPassword);
        goToSignup = findViewById(R.id.go_to_signup);
        progressBarLogin = findViewById(R.id.progress_login);
        setEmail = getIntent().getStringExtra("set_email");
        edtEmail.setText(setEmail);

    }

    public void onSignIn(View view){
        String myEmail = edtEmail.getText().toString();
        String myPassword = edtPassword.getText().toString();
        btnLogin.setEnabled(false);
        progressBarLogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(myEmail,myPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this,UserProfile.class);
                            intent.putExtra("user_email",myEmail);
                            startActivity(intent);
                        }else {
                            Toasty.error(LoginActivity.this, "User or Password is wrong!", Toast.LENGTH_SHORT, true).show();
                            progressBarLogin.setVisibility(View.GONE);
                            btnLogin.setEnabled(true);
                        }

                    }
                });
    }
}