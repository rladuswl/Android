package com.example.project7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    TextView toastTV;
    Button btn;
    EditText dlgEdt1, dlgEdt2;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.emo_im_cool);
                dlg.setView(dialogView);

                dlgEdt1 = (EditText) dialogView.findViewById(R.id.dlgEdt1);
                dlgEdt2 = (EditText) dialogView.findViewById(R.id.dlgEdt2);

                dlgEdt1.setText(et1.getText().toString());
                dlgEdt2.setText(et2.getText().toString());

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et1.setText(dlgEdt1.getText().toString());
                        et2.setText(dlgEdt2.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);
                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                                .getDefaultDisplay();
                        int xOffset = (int) (Math.random() * display.getWidth());
                        int yOffset = (int) (Math.random() * display.getHeight());

                        toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);

                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastTV = (TextView) toastView.findViewById(R.id.toastText1);
                        toastTV.setText("취소했습니다");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }
}