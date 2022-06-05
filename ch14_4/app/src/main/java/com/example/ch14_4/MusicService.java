package com.example.ch14_4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    MediaPlayer mp;

    // SD 카드 절대 경로
    String path = Environment.getExternalStorageDirectory().getPath() + "/";

    ArrayList<String> list = new ArrayList<String>();
    int number = 0; // list 인덱스 번호

    // SD 카드의 모든 파일을 File 변수 배열에 입력
    File[] listFiles = new File(path).listFiles();
    String fileName, extName; // 파일 이름과 확장자 명 변수

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스 테스트", "onCreate()");
        // listFiles에 들어 있는 파일 또는 폴더를 하나씩 file 변수에 넣고 listFiles의 개수만큼 반복
        for (File file : listFiles) {
            // file 변수에서 파일 이름과 확장자 명을 추출
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            // 확장자 명이 mp3일 때만 추가
            if (extName.equals((String) "mp3")) {
                list.add(fileName); // mp3 파일만 저장
            }
        }
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 테스트", "onDestroy()");
        mp.stop();
        mp.reset(); // 초기화되지 않은 처음 상태로 되돌림
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 테스트", "onStartCommand()");
        mp = new MediaPlayer(); // MediaPlayer 선언
        try {
            mp.setDataSource(path + list.get(number)); // 재생할 음악 파일지정
            mp.prepare(); // 음악 준비
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start(); // 음악 시작

        // 음악 재생이 끝날 경우
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.reset();
                if (number >= list.size() - 1) {
                    number = 0;
                    try {
                        mp.setDataSource(path + list.get(number)); // 재생할 음악 파일 지정
                        mp.prepare(); // 음악 준비
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start(); // 음악 시작
                } else {
                    number++;
                    try {
                        mp.setDataSource(path + list.get(number)); // 재생할 음악 파일 지정
                        mp.prepare(); // 음악 준비
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start(); // 음악 시작
                }
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }
}


