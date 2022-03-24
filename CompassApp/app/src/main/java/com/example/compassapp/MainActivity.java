package com.example.compassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

// AndroidManifest에 설정 추가
// implements SensorEventListener

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Float azimuth_angle;
    private SensorManager compassSensorManager; // 모든 타입의 센서들을 매니징
    Sensor accelerometer;
    Sensor magnetometer;

    TextView tv_degrees;
    ImageView iv_compass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compassSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // 연동
        accelerometer = compassSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // 가속도 센서 연동
        magnetometer = compassSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // 자기장 센서 연동
    }

    protected void onResume() {
        super.onResume(); // 등록 재개
        compassSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        compassSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause(); // 등록 해제
        compassSensorManager.unregisterListener(this);
    }

    float[] accel_read;
    float[] magnetic_read;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tv_degrees = (TextView) findViewById(R.id.tv_degrees);
        iv_compass = (ImageView) findViewById(R.id.iv_compass);

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) // 가속도 이면
            accel_read = sensorEvent.values;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) // 마그네틱 이면
            magnetic_read = sensorEvent.values;

        if (accel_read != null && magnetic_read != null) { // 둘 다 값을 읽었다면
            float R[] = new float[9];
            float I[] = new float[9];

            boolean successful_read = SensorManager.getRotationMatrix(R, I, accel_read, magnetic_read); // 각도 가져오기
            if (successful_read) { // 각도 가져왔으면
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth_angle = orientation[0];
                float degrees = ((azimuth_angle * 180f) / 3.14f); // 라디안으로 바꾸기
                int degreesInt = Math.round(degrees);
                tv_degrees.setText(Integer.toString(degreesInt) + (char) 0x00B0 + " to absolute north.");
                float current_degree = 0f;
                RotateAnimation rotate = new RotateAnimation(current_degree, -degreesInt, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(100);
                rotate.setFillAfter(true);

                iv_compass.startAnimation(rotate); // 이미지 움직이게!
                current_degree = -degreesInt;
            }
        }
    }

    // 사용 X
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}