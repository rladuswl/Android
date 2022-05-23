package com.example.project8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

// 실습 8_2 + 직접 풀어보기 8_2
public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    TextView tvNumber;
    myPictureView myPictureView;
    int curNum = 1; // 이미지 파일 순번 (숨김 파일 1개가 기본적으로 존재하기 때문에 1부터 시작)
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        myPictureView = (com.example.project8_2.myPictureView) findViewById(R.id.myPictureView);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname = imageFiles[curNum].toString();
        myPictureView.imagePath = imageFname;
        tvNumber.setText("1" + "/" + (imageFiles.length-1));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum <= 1) {
                    //Toast.makeText(getApplicationContext(), "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                    curNum = imageFiles.length - 1;
                }
                else {
                    curNum--;
                }
                imageFname = imageFiles[curNum].toString();
                myPictureView.imagePath = imageFname;
                myPictureView.invalidate();
                tvNumber.setText(curNum + "/" + (imageFiles.length-1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum >= imageFiles.length-1) {
                    //Toast.makeText(getApplicationContext(), "마지막 그림입니다.", Toast.LENGTH_SHORT).show();
                    curNum=1;
                }
                else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                myPictureView.imagePath = imageFname;
                myPictureView.invalidate();
                tvNumber.setText(curNum + "/" + (imageFiles.length-1));
            }
        });
    }
}