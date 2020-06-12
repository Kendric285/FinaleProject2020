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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ChooseGym extends AppCompatActivity {

    ImageView frontArrow;
    ImageView backArrow;
    ImageView gymImageView;

    TextView gymName;


    OkHttpClient client;

    Integer gymNumber;

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
        pokeImgs = new ImageView[6];

        Log.d("pokemon", "onCreate: "+sharedPref.getPokemon(0));

        gymNumber = 1;

        pokeImgs[0] = findViewById(R.id.poke1);
        pokeImgs[1] = findViewById(R.id.poke2);
        pokeImgs[2] = findViewById(R.id.poke3);



        frontArrow = findViewById(R.id.frontArrow);
        backArrow = findViewById(R.id.backArrow);
        gymImageView = findViewById(R.id.gymImageView);


        gymImageView.setImageResource(R.drawable.pewtergym);

        getPokemon(sharedPref.pokemons[0],0);

        gymImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                if(gymNumber == 1){
                    gymImageView.setImageResource(R.drawable.pewtergym);
                    gymName.setText("Pewter Gym");
                    //Rock
                }
                else if(gymNumber == 2){
                    gymImageView.setImageResource(R.drawable.ceruleangym);
                    gymName.setText("Cerulean Gym");
                    //Water

                }
                else if(gymNumber == 3){
                    gymImageView.setImageResource(R.drawable.vermilliongym);
                    gymName.setText("Vermillion Gym");
                    //Electric

                }
                else if(gymNumber == 4){
                    gymImageView.setImageResource(R.drawable.celadongym);
                    gymName.setText("Celadon Gym");
                    //grass

                }
                else if(gymNumber == 5){
                    gymImageView.setImageResource(R.drawable.fuchsiagym);
                    gymName.setText("Fuchsia Gym");
                    //Poison

                }
                else if(gymNumber == 6){
                    gymImageView.setImageResource(R.drawable.saffrongym);
                    gymName.setText("Saffron Gym");
                    //Psychic

                }
                else if(gymNumber == 7){
                    gymImageView.setImageResource(R.drawable.cinnabargym);
                    gymName.setText("Cinnabar Gym");
                    //Fire

                }
                else {
                    gymImageView.setImageResource(R.drawable.viridiangym);
                    gymName.setText("Viridian Gym");
                    //Ground

                }
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

                if(gymNumber == 1){
                    gymImageView.setImageResource(R.drawable.pewtergym);
                    gymName.setText("Pewter Gym");
                }
                else if(gymNumber == 2){
                    gymImageView.setImageResource(R.drawable.ceruleangym);
                    gymName.setText("Cerulean Gym");

                }
                else if(gymNumber == 3){
                    gymImageView.setImageResource(R.drawable.vermilliongym);
                    gymName.setText("Vermillion Gym");

                }
                else if(gymNumber == 4){
                    gymImageView.setImageResource(R.drawable.celadongym);
                    gymName.setText("Celadon Gym");

                }
                else if(gymNumber == 5){
                    gymImageView.setImageResource(R.drawable.fuchsiagym);
                    gymName.setText("Fuchsia Gym");

                }
                else if(gymNumber == 6){
                    gymImageView.setImageResource(R.drawable.saffrongym);
                    gymName.setText("Saffron Gym");

                }
                else if(gymNumber == 7){
                    gymImageView.setImageResource(R.drawable.cinnabargym);
                    gymName.setText("Cinnabar Gym");

                }
                else {
                    gymImageView.setImageResource(R.drawable.viridiangym);
                    gymName.setText("Viridian Gym");

                }
            }
        });


    }

    public void getPokemon(int x, int y) {
        poke = y;
        Log.d("mode", "onClick: ");
        String url = "https://pokeapi.co/api/v2/pokemon/" + x;
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("mode", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    // Log.d("mode", "onResponse: " + myResponse);

                    ChooseGym.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                Log.d("pokemon", "run: " +name);
                                JSONObject sprites = obj.getJSONObject("sprites");
                                pokeImageFront = sprites.getString("front_default");
                                pic();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });


    }
    public void toBattle(){
        Intent toBattle = new Intent(this, Battle.class);
        toBattle.putExtra("gymNumber",gymNumber );
        startActivity(toBattle);
    }

    public void pic() {
        Picasso.with(this).load(pokeImageFront).into(pokeImgs[poke]);}

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
