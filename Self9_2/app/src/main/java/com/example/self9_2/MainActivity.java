package com.example.self9_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3;
    static int curShape = LINE;
    static int curColor = Color.DKGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단 그림판 (개선)");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "사각형 그리기");
        SubMenu sMenu = menu.addSubMenu("색상 변경 >>");
        sMenu.add(0, 4, 0, "빨강");
        sMenu.add(0, 5, 0, "초록");
        sMenu.add(0, 6, 0, "파랑");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curShape = LINE; // 선
                return true;
            case 2:
                curShape = CIRCLE; // 원
                return true;
            case 3:
                curShape = RECTANGLE; // 사각형
                return true;
            case 4:
                curColor = Color.RED;
                return true;
            case 5:
                curColor = Color.GREEN;
                return true;
            case 6:
                curColor = Color.BLUE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        MyShape currentShape = null;
        static List<MyShape> myshapeList = new ArrayList<>();

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    currentShape = new MyShape(curShape);
                    currentShape.color = curColor;
                    currentShape.startX = (int) event.getX();
                    currentShape.startY = (int) event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    currentShape.stopX = (int) event.getX();
                    currentShape.stopY = (int) event.getY();
                    this.invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    currentShape.stopX = (int) event.getX();
                    currentShape.stopY = (int) event.getY();

                    myshapeList.add(currentShape);
                    currentShape = null;
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            for (MyShape currentShape : myshapeList) {
                paint.setColor(currentShape.color);

                switch (currentShape.shapeType) {
                    case LINE:
                        canvas.drawLine(currentShape.startX, currentShape.startY, currentShape.stopX, currentShape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(currentShape.stopX - currentShape.startX, 2)
                                + Math.pow(currentShape.stopY - currentShape.startY, 2));
                        canvas.drawCircle(currentShape.startX, currentShape.startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(currentShape.startX, currentShape.startY, currentShape.stopX, currentShape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

            if (currentShape != null) {
                switch (currentShape.shapeType) {
                    case LINE:
                        canvas.drawLine(currentShape.startX, currentShape.startY, currentShape.stopX, currentShape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(currentShape.stopX - currentShape.startX, 2)
                                + Math.pow(currentShape.stopY - currentShape.startY, 2));
                        canvas.drawCircle(currentShape.startX, currentShape.startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(currentShape.startX, currentShape.startY, currentShape.stopX, currentShape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }
        }
    }

    private static class MyShape {
        int shapeType; // 도형 종류
        int startX, startY, stopX, stopY; // 도형의 2점
        int color; // 도형의 색상

        public MyShape(int shapeType) {
            this.shapeType = shapeType;
        }
    }
}