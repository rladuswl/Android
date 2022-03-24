package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        Button button = (Button) findViewById(R.id.btn1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText heightText = (EditText) findViewById(R.id.editTextHeight);
                String heightStr = heightText.getText().toString();
                double height = Double.parseDouble(heightStr);

                final EditText weightText = (EditText) findViewById(R.id.editTextWeight);
                String weightStr = weightText.getText().toString();
                double weight = Double.parseDouble(weightStr);

                double BMI = (weight) / (height * height);
                DecimalFormat df = new DecimalFormat("#.#");
                double BMI_trimmed = Double.parseDouble(df.format(BMI));
                final TextView BMIResult = (TextView) findViewById(R.id.textView4);
                BMIResult.setText("Your BMI: " + Double.toString(BMI_trimmed));

                String BMI_Cat;
                if (BMI < 15) {
                    BMI_Cat = "Very severely underweight";
                }
                else if (BMI < 16) {
                    BMI_Cat = "Severely underweight";
                }
                else if (BMI < 18.5) {
                    BMI_Cat = "Underweight";
                }
                else if (BMI < 25) {
                    BMI_Cat = "Nomal";
                }
                else if (BMI < 30) {
                    BMI_Cat = "Overweight";
                }
                else if (BMI < 35) {
                    BMI_Cat = "Obese Class 1 - Moderately Obese";
                }
                else if (BMI < 40) {
                    BMI_Cat = "Obese Class 2 - Severely Obese";
                }
                else {
                    BMI_Cat = "Obese Class 3 - Very Severely Obese";
                }

                final TextView BMICategory = (TextView) findViewById(R.id.textView5);
                BMICategory.setText(BMI_Cat);
            }
        });
    }

    // 내가 짠 코드 : 계산 코드가 문제인지 계산버튼 누르면 앱이 자꾸 꺼짐..
    /*
    TextView textView1, textView2, textView3, textView4, textView5;
    Button btn;
    EditText editTextWeight, editTextHeight;
    String wc; // Weight Category 메세지 출력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText height
                editTextHeight = (EditText) findViewById(R.id.editTextHeight);
                String heightStr = editTextHeight.getText().toString(); // getText로 텍스트 얻은 후 toString으로 문자로 변경
                double heightDouble = Double.parseDouble(heightStr); // 문자열로 변경한 heightStr을 Double(실수형)으로 변경

                // EditText weight
                editTextWeight = (EditText) findViewById(R.id.editTextWeight);
                String weightStr = editTextWeight.getText().toString(); // getText로 텍스트 얻은 후 toString으로 문자로 변경
                double weightDouble = Double.parseDouble(weightStr); // 문자열로 변경한 weightStr을 Double(실수형)으로 변경

                // 계산
                double BMI = (weightDouble) / (heightDouble * heightDouble);

                if (BMI < 15) {
                    wc = "Very severely underweight";
                }
                else if (BMI < 16) {
                    wc = "Severely underweight";
                }
                else if (BMI < 18.5) {
                    wc = "Underweight";
                }
                else if (BMI < 25) {
                    wc = "Nomal";
                }
                else if (BMI < 30) {
                    wc = "Overweight";
                }
                else if (BMI < 35) {
                    wc = "Obese Class 1 - Moderately Obese";
                }
                else if (BMI < 40) {
                    wc = "Obese Class 2 - Severely Obese";
                }
                else {
                    wc = "Obese Class 3 - Very Severely Obese";
                }

                // 결과 TextView로 출력
                textView4.setText("Your BMI:" + BMI);
                textView5.setText(wc.toString());
            }
        });
    }
    */

}