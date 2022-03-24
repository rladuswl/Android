package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int randomRollResult = random.nextInt(6) + 1; // 1~6까지 랜덤으로 숫자 나옴

                TextView diceResult = (TextView) findViewById(R.id.diceresult);
                //diceResult.setText(randomRollResult); // 결과에 숫자 나타나기 -> 앱 꺼짐 (잘못된 코드 예상)
                diceResult.setText(Integer.toString(randomRollResult)); // int형을 toString 한 후 Integer 형태로 나타내기

                ImageView diceImage = (ImageView) findViewById(R.id.diceimage);

                switch (randomRollResult) {
                    case 1:
                        diceImage.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        diceImage.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        diceImage.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        diceImage.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        diceImage.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        diceImage.setImageResource(R.drawable.dice6);
                        break;
                }
            }
        });
    }
}