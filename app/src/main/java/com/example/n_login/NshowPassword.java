package com.example.n_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NshowPassword extends AppCompatActivity {
    TextView showPasswordTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nshowpassword);

        Intent intent = getIntent();
        String password = intent.getExtras().getString("password");
        showPasswordTextView = findViewById(R.id.showPasswordTextView);
        showPasswordTextView.setText(password);
    }
}
