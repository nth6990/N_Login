package com.example.n_login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class N_SignUp extends AppCompatActivity{

    EditText id, pw, name, q_answer;
    String indentifier = "developer";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_signup);

        id = findViewById(R.id.n12); //아이디 중복 확인 구현
        pw = findViewById(R.id.n22); //재확인 구현
        name = findViewById(R.id.n32);
        Button btn = findViewById(R.id.n42);
        q_answer = findViewById(R.id.n52);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference(indentifier);

        Button nSignUpbtn = findViewById(R.id.nSignUpbtn);
        nSignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item(id.getText().toString(), pw.getText().toString(), name.getText().toString(), 0, q_answer.getText().toString());
                myRef.setValue(item);
            }
        });


    }
}
