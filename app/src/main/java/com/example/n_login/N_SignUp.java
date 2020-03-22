package com.example.n_login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class N_SignUp extends AppCompatActivity{
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    EditText id, pw, name, q_answer, pw_check;

    ArrayList<String> arrayList = new ArrayList<String>();

    Spinner spinner;

    Button nDate;

    SimpleDateFormat format;

    Calendar calendar;

    DatePickerDialog.OnDateSetListener onDateSetListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_signup);

        id = findViewById(R.id.n12); //아이디 중복 확인 구현

        Button checkOverlap = findViewById(R.id.checkOverlap);

        checkOverlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(id.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        pw = findViewById(R.id.n22);
        pw_check = findViewById(R.id.checkPwEditText);

        name = findViewById(R.id.n32);
        arrayList.add("가장 기억에 남는 여행지는?");
        arrayList.add("가장 좋아하는 음식은?");
        arrayList.add("어릴 적 가장 좋아했던 장난감은?");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);

        spinner = findViewById(R.id.n42);
        spinner.setAdapter(arrayAdapter);
        q_answer = findViewById(R.id.n52);

        nDate = findViewById(R.id.nDate);
        format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(System.currentTimeMillis());
        nDate.setText(time);
        calendar = Calendar.getInstance();
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String setTime= format.format(calendar.getTime());
                nDate.setText(setTime);
            }
        };
        nDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(N_SignUp.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
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
                            Toast.makeText(N_SignUp.this, "이미 가입된 아이디입니다", Toast.LENGTH_SHORT).show();
                        } else if (!(pw.getText().toString().equals(pw_check.getText().toString()))){
                            Toast.makeText(N_SignUp.this, "입력한 비밀번호와 재입력한 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Item nitem = new Item(pw.getText().toString(), name.getText().toString(), spinner.getSelectedItemPosition(), q_answer.getText().toString(), nDate.getText().toString());
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

