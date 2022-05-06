package com.example.self9_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(60);
            canvas.drawLine(60, 60, 600, 60, paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(60, 160, 600, 160, paint);

            RectF rectF = new RectF();

            rectF.set(120, 240, 120 + 400, 240 + 200);
            canvas.drawOval(rectF, paint);

            rectF.set(120, 340, 120 + 200, 340 + 200);
            canvas.drawArc(rectF, 40, 110, true, paint);

            paint.setColor(Color.BLUE);
            rectF.set(120, 560, 120 + 200, 560 + 200);
            canvas.drawRect(rectF, paint);

            paint.setColor(Color.argb(0x88, 0xff, 0x00, 0x00));
            rectF.set(180, 620, 180 + 200, 620 + 200);
            canvas.drawRect(rectF, paint);
        }
    }
}