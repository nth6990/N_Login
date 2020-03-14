package com.example.n_login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class N_SignUp extends AppCompatActivity{
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    EditText id, pw, name, q_answer;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_signup);

        id = findViewById(R.id.n12); //아이디 중복 확인 구현

        Button checkOverlap = findViewById(R.id.checkOverlap);

        checkOverlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Item item = dataSnapshot.getValue(Item.class);
                        if(item != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(N_SignUp.this);
                            builder.setTitle("알림").setMessage("이미 가입된 아이디입니다");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(N_SignUp.this);
                            builder.setTitle("알림").setMessage("사용할 수 있는 아이디입니다");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        pw = findViewById(R.id.n22); //재확인 구현
        name = findViewById(R.id.n32);
        Button btn = findViewById(R.id.n42);
        //힌트 구현 스피너
        q_answer = findViewById(R.id.n52);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //토스트
            }
        });


        Button nSignUpbtn = findViewById(R.id.nSignUpbtn);
        nSignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Item item = dataSnapshot.getValue(Item.class);
                        if(item != null) {
                            Toast.makeText(N_SignUp.this, "아이디를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            Item nitem = new Item(pw.getText().toString(), name.getText().toString(), 0, q_answer.getText().toString());
                            databaseReference.child(id.getText().toString()).setValue(nitem);
                            Toast.makeText(N_SignUp.this, "정상적으로 회원가입 되었습니다", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }

}

