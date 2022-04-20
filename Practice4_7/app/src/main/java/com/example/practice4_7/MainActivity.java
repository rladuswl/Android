package com.example.practice4_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    CheckBox chk1, chk2, chk3;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 앱 아이콘 출력
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        setTitle("연습문제 4-7");

        // 기능 시작
        chk1 = (CheckBox) findViewById(R.id.enabled);
        chk2 = (CheckBox) findViewById(R.id.clickable);
        chk3 = (CheckBox) findViewById(R.id.rotation);
        button1 = (Button) findViewById(R.id.btn1);

        // chk1 체크박스를 체크
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk1.isChecked() == true) {
                    button1.setEnabled(true);
                } else {
                    button1.setEnabled(false);
                }
            }
        });

        // chk2 체크박스를 체크
        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk2.isChecked() == true) {
                    button1.setClickable(true);
                } else {
                    button1.setClickable(false);
                }
            }
        });

        // chk3 체크박스를 체크
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk3.isChecked() == true) {
                    button1.setRotation(45);
                } else {
                    button1.setRotation(0);
                }
            }
        });
    }
}