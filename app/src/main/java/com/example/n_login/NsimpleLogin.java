package com.example.n_login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NsimpleLogin extends AppCompatActivity {
    TextView simpleLoginPw;
    Button[] buttons = new Button[9];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_simplelogin);

        simpleLoginPw = findViewById(R.id.simpleLoginPw);

        buttons[0] = (Button) findViewById(R.id.simpleLoginNum1);
        buttons[1] = (Button) findViewById(R.id.simpleLoginNum2);
        buttons[2] = (Button) findViewById(R.id.simpleLoginNum3);
        buttons[3] = (Button) findViewById(R.id.simpleLoginNum4);
        buttons[4] = (Button) findViewById(R.id.simpleLoginNum5);
        buttons[5] = (Button) findViewById(R.id.simpleLoginNum6);
        buttons[6] = (Button) findViewById(R.id.simpleLoginNum7);
        buttons[7] = (Button) findViewById(R.id.simpleLoginNum8);
        buttons[8] = (Button) findViewById(R.id.simpleLoginNum9);

        for(int i=0;i<9;i++){
            final String temp = Integer.toString(i+1);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    simpleLoginPw.append(temp);
                    if(simpleLoginPw.getText().length() == 4){
                        finish(); //비밀번호 맞으면 넘어가는 걸로 변경
                    }
                }
            });
        }


    }

}
