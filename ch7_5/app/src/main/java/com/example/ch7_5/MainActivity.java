package com.example.ch7_5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-5");

        final Button btn = (Button) findViewById(R.id.btn);
        btn.setBackgroundColor(Color.GRAY);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(MainActivity.this);

                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                        .getDefaultDisplay();
                int xOffset = (int) (Math.random() * display.getWidth());
                int yOffset = (int) (Math.random() * display.getHeight());

                toast.setGravity(Gravity.TOP | Gravity.LEFT,
                        xOffset, yOffset);

                toastView = (View) View.inflate(
                        MainActivity.this, R.layout.toast1, null);
                toast.setView(toastView);

                toast.show();
            }
        });

        /*
        토스트 xml 따로 만들지 않고 설정하는 법 (toast1.xml 사용 X)
        public class MainActivity extends AppCompatActivity {
            Button btn; @Override protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
                btn = (Button)findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Toast msg = new Toast(getApplicationContext());
                        ImageView img = new ImageView(getApplicationContext());
                        img.setImageResource(R.drawable.ic_launcher_background);
                        msg.setView(img);
                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset = (int) (Math.random()*display.getWidth());
                        int yOffset = (int) (Math.random()*display.getHeight());
                        msg.setGravity(Gravity.TOP|Gravity.LEFT,xOffset,yOffset);
                        msg.show();
                    }
                });
            }
            }
         */
    }
}