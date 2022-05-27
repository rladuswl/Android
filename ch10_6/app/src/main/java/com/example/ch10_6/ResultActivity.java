package com.example.ch10_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        Integer imageFileId[] = { R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8,
                R.drawable.pic9 };
        Integer ivID[] = {R.id.imageView1, R.id.imageView2,
                R.id.imageView3, R.id.imageView4, R.id.imageView5,
                R.id.imageView6, R.id.imageView7, R.id.imageView8,
                R.id.imageView9};

        ImageView iv[] = new ImageView[9];
        for (int i=0; i<imageName.length; i++) {
            iv[i] = (ImageView) findViewById(ivID[i]);
        }

        int[] resultID = new int[9]; // 순위 정하는 배열
        // 전부 0으로 초기화
        for (int i=0; i< imageName.length; i++) {
            resultID[i] = 0;
        }

        //순서 구하기
        for(int i=0;i<8;i++){
            for(int j=i+1;j<9;j++) {
                if (voteResult[i] < voteResult[j])
                    resultID[i]++;
                else
                    resultID[j]++;
            }
        }

        for(int i=0;i<9;i++){
            iv[resultID[i]].setImageResource(imageFileId[i]);
        }



        Button btnStart, btnStop;
        final ViewFlipper vFlipper;

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        vFlipper.setFlipInterval(1000);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vFlipper.startFlipping();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vFlipper.stopFlipping();
            }
        });
    }
}
