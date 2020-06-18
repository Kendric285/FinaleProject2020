package com.example.realfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Integer i = null;

    TextView finalP;
    TextView crash;

    Button button;

    Random r = new Random();

    int colorrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalP = findViewById(R.id.text1);
        crash = findViewById(R.id.crash);
        button = findViewById(R.id.button1);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorrand = r.nextInt(3);

                if(colorrand == 0){
                    finalP.setTextColor(Color.parseColor("#4287f5"));
                }
                else if(colorrand == 1){
                    finalP.setTextColor(Color.parseColor("#f3ff14"));
                }
                else if(colorrand == 2){
                    finalP.setTextColor(Color.parseColor("#e8102d"));

                }else{
                    finalP.setTextColor(Color.parseColor("#32a852"));

                }
            }
        });

        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.byteValue();
            }
        });






    }
}