package com.example.ch10_5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {
    Button btnNew2, btnReturn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        btnNew2 = (Button) findViewById(R.id.btnNew2);
        btnReturn1 = (Button) findViewById(R.id.btnReturn1);

        btnNew2.setBackgroundColor(Color.GREEN);
        btnReturn1.setBackgroundColor(Color.GREEN);

        btnNew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);
            }
        });

        btnReturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
