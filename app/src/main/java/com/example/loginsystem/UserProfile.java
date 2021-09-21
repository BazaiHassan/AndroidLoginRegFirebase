package com.example.loginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {

    private TextView getEmail;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        findViews();
    }

    private void findViews() {
        userEmail=getIntent().getStringExtra("user_email");
        getEmail = findViewById(R.id.get_email);
        getEmail.setText(userEmail);
    }

    public void onSignOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(UserProfile.this,LoginActivity.class);
        startActivity(intent);
    }
}