package com.example.duathuapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    ImageButton imgButton;
    CheckBox cb1, cb2, cb3;
    SeekBar sk1,sk2,sk3;
    int score = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //goi ham anh xa
        AnhXa();
        //goi ham de chong nguoi dung tuong tac voi seekbar
        NoTouchSeekbar();

        txtDiem.setText(score+ "");
        //lam cho may con vat chay ngau nhien
        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                int number =5;
                //random toc do bang cach dung ham random
                Random random = new Random();
                int number1 = random.nextInt(number);
                int number2 = random.nextInt(number);
                int number3 = random.nextInt(number);
                //kiểm tra win one,two,three
                if(sk1.getProgress() >=sk1.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    imgButton.setVisibility(View.VISIBLE);
                    //kiem tra dat cuoc
                    if (cb1.isChecked()){
                        score +=10;
                        Toast.makeText(MainActivity.this, "ban doan chinh xac +10d", Toast.LENGTH_SHORT).show();
                    }else {
                        score -=5;
                        Toast.makeText(MainActivity.this, "ban doan sai roi -5d", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(score+"");
                    EnnableCheck();
                }
                if(sk2.getProgress() >=sk2.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    imgButton.setVisibility(View.VISIBLE);
                    //kiem tra dat cuoc
                    if (cb2.isChecked()){
                        score +=10;
                        Toast.makeText(MainActivity.this, "ban doan chinh xac +10d", Toast.LENGTH_SHORT).show();
                    }else {
                        score -=5;
                        Toast.makeText(MainActivity.this, "ban doan sai roi -5d", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(score+"");
                    EnnableCheck();
                }
                if(sk3.getProgress() >=sk3.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    imgButton.setVisibility(View.VISIBLE);
                    //kiem tra dat cuoc
                    if (cb3.isChecked()){
                        score +=10;
                        Toast.makeText(MainActivity.this, "ban doan chinh xac +10d", Toast.LENGTH_SHORT).show();
                    }else {
                        score -=5;
                        Toast.makeText(MainActivity.this, "ban doan sai roi -5d", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(score+"");
                    EnnableCheck();
                }


                //set vi tri hien tai thanh seekbar sau do dung number da duoc random de tao ra toc do ngau nhien
                sk1.setProgress(sk1.getProgress()+number1);
                sk2.setProgress(sk2.getProgress()+number2);
                sk3.setProgress(sk3.getProgress()+number3);
            }

            @Override
            public void onFinish() {

            }
        };

        //set nut start de chay
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiem tra nguoi dung da tick vao 1 trong 3 checkbox chua neu chua thi ngan ko cho nguoi dung bam start de choi
                if (cb1.isChecked()||cb2.isChecked()||cb3.isChecked()){
                    //truoc khi chay lai thi set lai vi tri cac con thu ve 0 neu ko set lai vi tri thi se bug
                    sk1.setProgress(0);
                    sk2.setProgress(0);
                    sk3.setProgress(0);
                    //dang chay thi cho no an nut start di
                    imgButton.setVisibility(View.INVISIBLE);
                    //ham start de chay ham countimer
                    countDownTimer.start();
                    //dang chay khong cho nguoi dung click vao checkbox
                    DisEnnable();
                }else {
                    Toast.makeText(MainActivity.this, "Vui long chon con vat truoc khi choi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //lam su kien checkbox 1
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //neu tick vao box 1 thi box 2 va 3 se ko tick dc
                if (b){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }

            }
        });
        //lam su kien checkbox 2
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //neu tick vao box 2 thi box 1 va 3 se ko tick dc
                if (b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }

            }
        });
        //lam su kien checkbox 3
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //neu tick vao box 3 thi box 1 va 2 se ko tick dc
                if (b){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }

            }
        });


    }
    //cho phep click vao checkbox
    private void EnnableCheck(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    //khong cho phep click vao checkbox
    private void DisEnnable(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }
    //khong cho nguoi dung tuong tac voi seekBar de lam bug
    private void NoTouchSeekbar(){
        sk1.setEnabled(false);
        sk2.setEnabled(false);
        sk3.setEnabled(false);
    }







    private void AnhXa(){
        txtDiem = (TextView) findViewById(R.id.textScore);

        imgButton = (ImageButton) findViewById(R.id.startbutton);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);

        sk1 = (SeekBar) findViewById(R.id.seekBar1);
        sk2 = (SeekBar) findViewById(R.id.seekBar2);
        sk3 = (SeekBar) findViewById(R.id.seekBar3);
    }
}