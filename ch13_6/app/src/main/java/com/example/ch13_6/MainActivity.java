package com.example.ch13_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnStop, btnPause;
    TextView tvMP3, tvTime;
    SeekBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3path = Environment.getExternalStorageDirectory().getPath() + "/";
    MediaPlayer mPlayer;
    boolean PAUSED = false;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if (extName.equals((String) "mp3")) // 확장명이 mp3일 때만 추가함.
                mp3List.add(fileName);
        }

        ListView listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                selectedMP3 = mp3List.get(arg2);
            }
        });

        selectedMP3 = mp3List.get(0);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPause = (Button) findViewById(R.id.btnPause);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        pbMP3 = (SeekBar) findViewById(R.id.pbMP3);
        tvTime = (TextView) findViewById(R.id.tvTime);

        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PAUSED == false) {
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;
                    //pbMP3.setVisibility(View.INVISIBLE);
                    tvTime.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));

                } else {
                    mPlayer.start();
                    PAUSED = false;
                    btnPause.setText("일시정지");
                    //pbMP3.setVisibility(View.VISIBLE);
                    pbMP3.setProgress(mPlayer.getCurrentPosition());
                    tvTime.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                }

            }
        });
        //btnPause.setClickable(false);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnStop.setClickable(true);
                    //pbMP3.setVisibility(View.VISIBLE);
                    tvMP3.setText("실행중인 음악 :  " + selectedMP3);

                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 :  ");
                //pbMP3.setVisibility(View.INVISIBLE);
                pbMP3.setProgress(0);
                tvTime.setText("진행 시간 : ");
            }
        });
        btnStop.setClickable(false);

        pbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (mPlayer == null)
                    return;
                pbMP3.setMax(mPlayer.getDuration());

                if (mPlayer.isPlaying()) {
                    pbMP3.setProgress(mPlayer.getCurrentPosition());
                    tvTime.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                }
                handler.sendEmptyMessageDelayed(0, 200);
            }
        };
    }
}