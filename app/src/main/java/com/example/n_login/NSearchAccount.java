package com.example.n_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NSearchAccount extends AppCompatActivity{
    EditText id, q_answer;
    Spinner searchAccountSpinner;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_search_account);
        searchAccountSpinner = findViewById(R.id.searchAccountSpinner);
        arrayList.add("가장 기억에 남는 여행지는?");
        arrayList.add("가장 좋아하는 음식은?");
        arrayList.add("어릴 적 가장 좋아했던 장난감은?");
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        searchAccountSpinner.setAdapter(arrayAdapter);


        id = findViewById(R.id.searchAccountEditText1);
        q_answer = findViewById(R.id.searchAccountEditText2);
        Button searchAccountbtn = findViewById(R.id.searchAccountbtn);
        searchAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        NItem item = dataSnapshot.getValue(NItem.class);
                        if(item == null){
                            Toast.makeText(NSearchAccount.this, "아이디가 잘못되었습니다", Toast.LENGTH_SHORT).show();
                        } else if((searchAccountSpinner.getSelectedItemPosition() == item.q_num)&&(q_answer.getText().toString().equals(item.q_answer))){
                            Intent intent = new Intent(getApplicationContext(), NShowPassword.class);
                            intent.putExtra("password",item.pw);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(NSearchAccount.this, "힌트 문항 혹은 답이 잘못되었습니다", Toast.LENGTH_SHORT).show();
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
