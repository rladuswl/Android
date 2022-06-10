package com.example.ch12_6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장 (수정)");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        myHelper = new myDBHelper(this);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        // 처음 실행시에 설정할 내용
        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" + Integer.toString(cDay);
        String str = readDiary(fileName);
        edtDiary.setText(str);

//        sqlDB = myHelper.getWritableDatabase();
//        myHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력하면 됨.
//        sqlDB.close();

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth);
                String str = readDiary(fileName);
                edtDiary.setText(str);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String content = edtDiary.getText().toString();

                if (readDiary(fileName) == null) { // 기존 일기 없는 경우
                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO myDiary VALUES ( '" + fileName + "', '" + content + "');");
                    sqlDB.close();
                    edtDiary.setText(content);

                    if (edtDiary.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), fileName + " 일기 저장", Toast.LENGTH_SHORT).show();
                        btnWrite.setText("새로 저장");
                    } else {
                        Toast.makeText(getApplicationContext(), fileName + " 일기 저장", Toast.LENGTH_SHORT).show();
                        btnWrite.setText("수정 하기");
                    }
                } else { // 기존 일기 있는 경우
                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("UPDATE myDiary SET content = '" + content + "' WHERE diaryData = '" + fileName + "';");
                    sqlDB.close();
                    edtDiary.setText(content);

                    if (edtDiary.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), fileName + " 일기 수정", Toast.LENGTH_SHORT).show();
                        btnWrite.setText("새로 저장");
                    } else {
                        Toast.makeText(getApplicationContext(), fileName + " 일기 수정", Toast.LENGTH_SHORT).show();
                        btnWrite.setText("수정 하기");
                    }
                }
            }
        });

    }

    String readDiary(String fName) {
        String diaryStr = null;

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT content FROM myDiary WHERE diaryData ='" + fName + "';", null);

        while (cursor.moveToNext()) {
            diaryStr = cursor.getString(0);
        }

        if (diaryStr != null) { // 일기 있는 경우
            edtDiary.setText(diaryStr);

            // 일기의 내용이 "" 인 경우
            if (edtDiary.getText().toString().equals("")) {
                edtDiary.setText("");
                edtDiary.setHint("일기 없음");
                btnWrite.setText("새로 저장");
            } else {
                btnWrite.setText("수정 하기");
            }
        } else { // 일기 없는 경우
            edtDiary.setText("");
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }

        cursor.close();
        sqlDB.close();

        return diaryStr;
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE myDiary (diaryData CHAR(10) PRIMARY KEY, content VARCHAR(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS myDiary");
            onCreate(db);
        }
    }

}

