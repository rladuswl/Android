package com.example.ch7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup rGroup;
    RadioButton dog, cat, rabbit, horse;
    Button btn;
    View dlgImg;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-6");

        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        btn = (Button) findViewById(R.id.btn);
        dog = (RadioButton) findViewById(R.id.dog);
        cat = (RadioButton) findViewById(R.id.cat);
        rabbit = (RadioButton) findViewById(R.id.rabbit);
        horse = (RadioButton) findViewById(R.id.horse);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dlgImg = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                img = (ImageView) dlgImg.findViewById(R.id.img);
                dlg.setView(dlgImg);

                switch (rGroup.getCheckedRadioButtonId()) {
                    case R.id.dog:
                        dlg.setTitle("강아지");
                        img.setImageResource(R.mipmap.dog);
                        break;
                    case R.id.cat:
                        dlg.setTitle("고양이");
                        img.setImageResource(R.mipmap.cat);
                        break;
                    case R.id.rabbit:
                        dlg.setTitle("토끼");
                        img.setImageResource(R.mipmap.rabbit);
                        break;
                    case R.id.horse:
                        dlg.setTitle("말");
                        img.setImageResource(R.mipmap.horse);
                        break;
                }
                dlg.setPositiveButton("닫기", null);
                dlg.show();
            }
        });


    }
}