package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseGym extends AppCompatActivity {

    ImageView frontArrow;
    ImageView backArrow;
    ImageView gymImageView;

    TextView gymName;

    Boolean pewterCityBadge;
    Boolean ceruleanCityBadge;
    Boolean vermillionCityBadge;
    Boolean celadonCityBadge;
    Boolean fuchsiaCityBadge;
    Boolean safronCityBadge;
    Boolean cinnabarCityBadge;
    Boolean viridianCityBadge;


    Button beat;

    OkHttpClient client;

    Integer gymNumber;

    Integer userMoney;
    Integer userPokeBalls;

    TextView money;
    TextView pokeBalls;
    String pokeImageFront;


    ImageView[] pokeImgs;
    String pokeFront1;
    int poke;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        sharedPref = new SharedPref(this);
        client = new OkHttpClient();
        gymImageView = findViewById(R.id.gymImageView);
        gymName = findViewById(R.id.gymName);

        beat = findViewById(R.id.beat);





        money = findViewById(R.id.money);
        pokeBalls = findViewById(R.id.pokeballs);

        pokeImgs = new ImageView[6];
        userMoney = 100;
        userPokeBalls = 3;

        money.setText("Money:" + userMoney);
        pokeBalls.setText("PokeBalls: "+ userPokeBalls);

















//        gymPic();

        //Log.d("pokemon", "onCreate: "+sharedPref.getPokemon(0));

        gymNumber = 1;

        pokeImgs[0] = findViewById(R.id.poke1);
        pokeImgs[1] = findViewById(R.id.poke2);
        pokeImgs[2] = findViewById(R.id.poke3);


        Intent intent = getIntent();



        frontArrow = findViewById(R.id.frontArrow);
        backArrow = findViewById(R.id.backArrow);
        gymImageView = findViewById(R.id.gymImageView);


        gymImageView.setImageResource(R.drawable.pewtergym);

        for(int i = 0; i < 8; i ++){
            System.out.println(i + 1 +  ": " + sharedPref.badgeCollection[i]);
        }




        getPokemon(0);
        gymPic();
//        getPokemon(1);
  //      getPokemon(2);

        gymImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBattle();
            }
        });

        frontArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gymNumber != 8){
                    gymNumber++;

                }
                else {
                    gymNumber = 1;
                }

                gymPic();


            }

        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gymNumber != 1){
                    gymNumber--;

                }
                else {
                    gymNumber = 8;
                }


                gymPic();
            }
        });


    }

    public void getPokemon(final int x) {
        //poke = y;
        poke = x;
        Log.d("mode", "onClick: ");

                            try {
                                JSONObject obj = new JSONObject(sharedPref.pokemons[x]);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                Log.d("pokemon", "run: " + name);
                                JSONObject sprites = obj.getJSONObject("sprites");
                                pokeImageFront = sprites.getString("front_default");
                                pic();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



    }
    public void toBattle(){
        Intent toBattle = new Intent(this, Battle.class);
        toBattle.putExtra("gymNumber",gymNumber);

        startActivity(toBattle);
    }

    public void pic() {
        Picasso.with(this).load(pokeImageFront).into(pokeImgs[poke]);
    }

    public static void printBadges(ArrayList<Integer> arrayListName){

        for (int number = 0; number < arrayListName.size(); number++){
            System.out.println(arrayListName.get(number));
        }
    }

    public void gymPic(){
        if(gymNumber == 1){
            gymImageView.setImageResource(R.drawable.pewtergym);
            if (sharedPref.badgeCollection[0] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Pewter Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }
        }
        else if(gymNumber == 2){
            gymImageView.setImageResource(R.drawable.ceruleangym);
            if (sharedPref.badgeCollection[1]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Cerulean Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }

        }
        else if(gymNumber == 3){
            gymImageView.setImageResource(R.drawable.vermilliongym);

            if (sharedPref.badgeCollection[2]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Vermillion Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }

        }
        else if(gymNumber == 4){
            gymImageView.setImageResource(R.drawable.celadongym);
            if (sharedPref.badgeCollection[3]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Celadon Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }

        }
        else if(gymNumber == 5){
            gymImageView.setImageResource(R.drawable.fuchsiagym);

            if (sharedPref.badgeCollection[4]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Fuchsia Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }


        }
        else if(gymNumber == 6){
            gymImageView.setImageResource(R.drawable.saffrongym);

            if (sharedPref.badgeCollection[5]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Saffron Gym");
            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }

        }
        else if(gymNumber == 7){
            gymImageView.setImageResource(R.drawable.cinnabargym);

            if (sharedPref.badgeCollection[6]==false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Cinnabar Gym");

            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");
            }

        }
        else {
            gymImageView.setImageResource(R.drawable.viridiangym);

            if (sharedPref.badgeCollection[7] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Viridian Gym");

            }else{
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*BADGE COLLECTED*");

            }

        }
    }

    /*private void pokeIdle(){
        pokeFront1 = new CountDownTimer(happyTime,1) {
            @Override
            public void onTick (long millisUntilFinished){


                happyTime = (int) millisUntilFinished;


                happiness.setProgress((int) millisUntilFinished);

            }

            @Override
            public void onFinish () {

                happiness.setProgress(0);

            }

        }.start();
    }*/

}
