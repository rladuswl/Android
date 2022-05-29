package com.example.ch11_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    // 전역 변수
    static float angle = 0; // 각도
    static float scaleX = 1, scaleY = 1; // 확대/축소 비율
    static float gradient = 0; // 기울기

    MyGraphicView myGraphicView;

//        final Integer[] posterID = { R.drawable.mov21, R.drawable.mov22,
//                R.drawable.mov23, R.drawable.mov24, R.drawable.mov25,
//                R.drawable.mov26, R.drawable.mov27, R.drawable.mov28,
//                R.drawable.mov29, R.drawable.mov30 };

    final static String[] posterID = {"mov21", "mov22",
            "mov23", "mov24", "mov25", "mov26",
            "mov27", "mov28", "mov29", "mov30"};

    final static String[] movie = { "아바타", "힘을내요 미스터리", "포드vs페라리","쥬만지", "대부",
            "국가대표", "토이스토리3", "마당을 나온 암탉", "죽은 시인의 사회", "서유기" };

    static int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        //System.out.println("***************************" + Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/"+posterID[number]);
        //System.out.println("*************현재 number" + number);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        myGraphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(myGraphicView);

        registerForContextMenu(myGraphicView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                number = i;
                myGraphicView.invalidate();
                System.out.println("*************현재 number" + number);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == myGraphicView) {
            menu.add(0, 1, 0, "회전"); // 주 메뉴 항목 1
            menu.add(0, 2, 0, "확대"); // 주 메뉴 항목 2
            menu.add(0, 3, 0, "축소"); // 주 메뉴 항목 3
            menu.add(0, 4, 0, "기울기 증가"); // 주 메뉴 항목 4
            menu.add(0, 5, 0, "기울기 감소"); // 주 메뉴 항목 5
        } else {
            menu.add(0, 1, 0, "회전"); // 주 메뉴 항목 1
            menu.add(0, 2, 0, "확대"); // 주 메뉴 항목 2
            menu.add(0, 3, 0, "축소"); // 주 메뉴 항목 3
            menu.add(0, 4, 0, "기울기 증가"); // 주 메뉴 항목 4
            menu.add(0, 5, 0, "기울기 감소"); // 주 메뉴 항목 5
        }

    }

    // 메뉴를 선택했을때 동작
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                angle = angle + 45; // 각도 45 증가
                myGraphicView.invalidate(); // 화면이 무효화된 후 onDraw() 자동 실행
                return true;
            case 2:
                // x비율, y비율 모두 0.2 증가
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                myGraphicView.invalidate(); // 화면이 무효화된 후 onDraw() 자동 실행
                return true;
            case 3:
                // x비율, y비율 모두 0.2 감소
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                myGraphicView.invalidate(); // 화면이 무효화된 후 onDraw() 자동 실행
                return true;
            case 4:
                gradient = gradient + 0.2f; // 기울기 0.2 증가
                myGraphicView.invalidate(); // 화면이 무효화된 후 onDraw() 자동 실행
                return true;
            case 5:
                gradient = gradient - 0.2f; // 기울기 0.2 감소
                myGraphicView.invalidate(); // 화면이 무효화된 후 onDraw() 자동 실행
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private static class MyGraphicView extends View {

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 중심 좌표
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            // scale() : 확대/축소(x비율, y비율, 중심 x좌표, 중심 y좌표)
            canvas.scale(scaleX, scaleY, cenX, cenY);
            // rotate() : 회전(각도, 중심 x좌표, 중심 y좌표)
            canvas.rotate(angle, cenX, cenY);
            // skew() : 기울이기(x비율, y비율)
            canvas.skew (gradient, gradient);

            System.out.println("***number 확인" + number);
            Bitmap picture = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/"+posterID[number]+".jpg");
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, null);
            picture.recycle();

        }
    }
}