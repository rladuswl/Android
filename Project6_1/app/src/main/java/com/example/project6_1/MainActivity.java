package com.example.project6_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chr;
    RadioGroup rGroup;
    RadioButton rdoDate, rdoTime;
    DatePicker datePic;
    TimePicker timePic;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        chr = (Chronometer) findViewById(R.id.chr);

        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        rdoDate = (RadioButton) findViewById(R.id.rdoDate);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);

        datePic = (DatePicker) findViewById(R.id.datePic);
        timePic = (TimePicker) findViewById(R.id.timePic);

        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);

        timePic.setVisibility(View.INVISIBLE);
        datePic.setVisibility(View.INVISIBLE);
        rGroup.setVisibility(View.INVISIBLE);


        rdoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePic.setVisibility(View.VISIBLE);
                timePic.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePic.setVisibility(View.VISIBLE);
                datePic.setVisibility(View.INVISIBLE);
            }
        });

        chr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rGroup.setVisibility(View.VISIBLE);
                chr.setBase(SystemClock.elapsedRealtime());
                chr.start();
                chr.setTextColor(Color.RED);
            }
        });

        tvYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chr.stop();
                chr.setTextColor(Color.BLUE);

                tvYear.setText(Integer.toString(datePic.getYear()));
                tvMonth.setText(Integer.toString(datePic.getMonth()+1));
                tvDay.setText(Integer.toString(datePic.getDayOfMonth()));

                tvHour.setText(Integer.toString(timePic.getCurrentHour()));
                tvMinute.setText(Integer.toString(timePic.getCurrentMinute()));

                rGroup.setVisibility(View.INVISIBLE);
                datePic.setVisibility(View.INVISIBLE);
                timePic.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }
}