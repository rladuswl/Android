package com.example.example9_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 블러링 효과
            /*
            String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/lena256.jpg";
            Bitmap picture = BitmapFactory.decodeFile(imagePath);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            Paint paint = new Paint();
            BlurMaskFilter bMask;

            bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID);
            paint.setMaskFilter(bMask);
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();
            */

            // 엠보싱 효과
            /*
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            EmbossMaskFilter eMask;

            eMask = new EmbossMaskFilter(new float[]{3, 10, 3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, cenY, 150, paint);
            */

            // 컬러매트릭스
            String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/lena256.jpg";
            Bitmap picture = BitmapFactory.decodeFile(imagePath);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            canvas.scale(4, 4, cenX, cenY);

            Paint paint = new Paint();
            float[] array = {1, 0, 0, 0, -100,
                            0, 1, 0, 0, -100,
                            0, 0, 1, 0, -100,
                            0, 0, 0, 1, 0};
            ColorMatrix cm = new ColorMatrix(array);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();

        }
    }
}