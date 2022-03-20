package com.example.project4_2;

// 애완동물 사진 보기 앱 만들기

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoDog, rdoCat, rdoAlpaca;
    Button btnOK;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 앱 아이콘 출력
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        // 앱 기능 시작
        setTitle("애완동물 사진 보기");

        // 위젯을 변수에 대입
        chkAgree = (CheckBox) findViewById(R.id.ChkAgree);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog = (RadioButton) findViewById(R.id.RdoDog);
        rdoCat = (RadioButton) findViewById(R.id.RdoCat);
        rdoAlpaca = (RadioButton) findViewById(R.id.RdoAlpaca);

        btnOK = (Button) findViewById(R.id.BtnOK);
        imgPet = (ImageView) findViewById(R.id.ImgPet);

        // '시작함' 체크박스를 체크/언체크
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 체크박스가 체크되면 모두 보이도록 설정
                if (chkAgree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                } else {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnOK.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 동물 선택 후, 선택 완료 버튼을 클릭하면
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (rGroup1.getCheckedRadioButtonId()) {
                    case R.id.RdoDog:
                        // 아래 코드를 chkAgree 내의 코드가 아닌 btnOK 내에 넣으면 '시작함' 체크박스를 체크 해제 하여도 이미지가 계속 VISIBLE 상태
                        //imgPet.setVisibility(View.VISIBLE);
                        imgPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.RdoCat:
                        //imgPet.setVisibility(View.VISIBLE);
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.RdoAlpaca:
                        //imgPet.setVisibility(View.VISIBLE);
                        imgPet.setImageResource(R.drawable.alpaca);
                        break;
                    default: // 아무것도 선택되지 않았다면 토스트 메시지 출력
                        Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요.", Toast.LENGTH_SHORT)
                                .show();
                }
            }
        });
    }
}