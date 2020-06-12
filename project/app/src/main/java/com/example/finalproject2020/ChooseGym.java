package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseGym extends AppCompatActivity {

    ImageView frontArrow;
    ImageView backArrow;
    ImageView gymImageView;

    Integer gymNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        gymImageView = findViewById(R.id.gymImageView);

        gymNumber = 1;





        frontArrow = findViewById(R.id.frontArrow);
        backArrow = findViewById(R.id.backArrow);
        gymImageView = findViewById(R.id.gymImageView);


        gymImageView.setImageResource(R.drawable.pewtergym);

        frontArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gymNumber != 8){
                    gymNumber++;

                }
                else{
                    gymNumber = 1;
                }

                if(gymNumber == 1){
                    gymImageView.setImageResource(R.drawable.pewtergym);
                }
                else if(gymNumber == 2){
                    gymImageView.setImageResource(R.drawable.ceruleangym);
                }
                else if(gymNumber == 3){
                    gymImageView.setImageResource(R.drawable.vermilliongym);
                }
                else if(gymNumber == 4){
                    gymImageView.setImageResource(R.drawable.celadongym);
                }
                else if(gymNumber == 5){
                    gymImageView.setImageResource(R.drawable.fuchsiagym);
                }
                else if(gymNumber == 6){
                    gymImageView.setImageResource(R.drawable.saffrongym);
                }
                else if(gymNumber == 7){
                    gymImageView.setImageResource(R.drawable.cinnabargym);
                }
                else if(gymNumber == 8){
                    gymImageView.setImageResource(R.drawable.viridiangym);

                }else{

                }
            }

        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gymNumber != 1){
                    gymNumber--;

                }
                else{
                    gymNumber = 8;
                }

                if(gymNumber == 1){
                    gymImageView.setImageResource(R.drawable.pewtergym);
                }
                else if(gymNumber == 2){
                    gymImageView.setImageResource(R.drawable.ceruleangym);
                }
                else if(gymNumber == 3){
                    gymImageView.setImageResource(R.drawable.vermilliongym);
                }
                else if(gymNumber == 4){
                    gymImageView.setImageResource(R.drawable.celadongym);
                }
                else if(gymNumber == 5){
                    gymImageView.setImageResource(R.drawable.fuchsiagym);
                }
                else if(gymNumber == 6){
                    gymImageView.setImageResource(R.drawable.saffrongym);
                }
                else if(gymNumber == 7){
                    gymImageView.setImageResource(R.drawable.cinnabargym);
                }
                else if(gymNumber == 8){
                    gymImageView.setImageResource(R.drawable.viridiangym);

                }else{

                }
            }
        });




    }
}
