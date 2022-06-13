package com.example.project10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button btnNewActivity;
    RadioButton rdoSecond, rdoThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewActivity = (Button) findViewById(R.id.btnNewActivity);

        rdoSecond = (RadioButton) findViewById(R.id.rdoSecond);
        rdoThird = (RadioButton) findViewById(R.id.rdoThird);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (rdoSecond.isChecked() == true) {
                    intent = new Intent(getApplicationContext(), SecondActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), ThirdActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}