package com.example.nogit;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(baseLayout, params);

        LinearLayout layout1 = new LinearLayout(this);
        layout1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout layout1_1 = new LinearLayout(this);
        layout1_1.setBackgroundColor(Color.rgb(255, 0, 0));
        layout1.addView(layout1_1);

        LinearLayout layout1_2 = new LinearLayout(this);
        layout1_2.setOrientation(LinearLayout.VERTICAL);
        layout1.addView(layout1_1);

        LinearLayout layout1_3 = new LinearLayout(this);
        layout1_3.setBackgroundColor(Color.rgb(255, 255, 0));
        layout1_2.addView(layout1_3);

        LinearLayout layout1_4 = new LinearLayout(this);
        layout1_4.setBackgroundColor(Color.rgb(255, 255, 255));
        layout1_2.addView(layout1_4);

        LinearLayout layout2 = new LinearLayout(this);
        layout2.setBackgroundColor(Color.rgb(255, 0, 255));
    }


}
