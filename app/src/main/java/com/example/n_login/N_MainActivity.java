package com.example.n_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class N_MainActivity extends AppCompatActivity {
    ArrayList<Item> list =new ArrayList<>();
    EditText nId, nPw;
    TextView nSignUp, nSearchAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_main);
        Item item = new Item();
        item.setId("n");
        item.setPw("1");
        list.add(item);
        Item item2 = new Item("a", "2");
        list.add(item2);
        nId = findViewById(R.id.nId);
        nPw = findViewById(R.id.nPw);
        Button btn = findViewById(R.id.nLoginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<list.size();i++) {
                    if(nId.getText().toString().equals(list.get(i).id)&&nPw.getText().toString().equals(list.get(i).pw)){
                        //다른 애들 꺼랑 연결, Intent 해야됨
                        //Intent intent = new Intent(getApplicationContext(), 다음에 보여줄 화면.class);
                        //startActivity(intent);
                        break;
                    }
                    else if(i == list.size()-1) {
                        Toast.makeText(N_MainActivity.this, "wrong id or password", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        nSignUp = findViewById(R.id.nSignUp);
        nSearchAccount = findViewById(R.id.nSearchAccount);
        nSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), N_SignUp.class);
                startActivity(intent);
            }
        });

    }
}
