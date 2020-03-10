package com.example.n_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class N_MainActivity extends AppCompatActivity {
    EditText nId, nPw;
    TextView nSignUp, nSearchAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_main);
        nId = findViewById(R.id.nId);
        nPw = findViewById(R.id.nPw);
        Button btn = findViewById(R.id.nLoginBtn);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference("information"); //핸드폰 번호로 할 것

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isAccountInvalid = true;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if(dataSnapshot.getValue() == null){
                                Toast.makeText(N_MainActivity.this, "nono", Toast.LENGTH_SHORT).show();
                            }
                            else if (nId.getText().toString().equals(dataSnapshot.getValue(Item.class).id) && nPw.getText().toString().equals(dataSnapshot.getValue(Item.class).pw)) {
                                //Intent intent = new Intent(getApplicationContext(), P_MainActivity.class);
                                //startActivity(intent);
                                Toast.makeText(N_MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                                isAccountInvalid = false;
                                break;
                            }
                        }
                        if (isAccountInvalid) {
                            Toast.makeText(N_MainActivity.this, "wrong ID or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


//                for(int i=0;i<list.size();i++) {
//                    if(nId.getText().toString().equals(list.get(i).id)&&nPw.getText().toString().equals(list.get(i).pw)){
//                        //Intent intent = new Intent(getApplicationContext(), P_MainActivity.class);
//                        //startActivity(intent);
//                        break;
//                    }
//                    else if(i == list.size()-1) {
//                        Toast.makeText(N_MainActivity.this, "wrong id or password", Toast.LENGTH_SHORT).show();
//                    }
//                }




        nSignUp = findViewById(R.id.nSignUp);
        nSearchAccount = findViewById(R.id.nSearchAccount);
        nSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), N_SignUp.class);
                startActivity(intent);
            }
        });
        nSearchAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), N_SearchAccount.class);
                startActivity(intent);
            }
        });

    }
}
