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

import com.squareup.picasso.Picasso;

public class ChooseGym extends AppCompatActivity {

    ImageView frontArrow;
    ImageView backArrow;
    ImageView gymImageView;
    ImageView userStarterPokemon;

    OkHttpClient client;

    Integer gymNumber;

    String userStarterPokemonImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        sharedPref = new SharedPref(this);
        client = new OkHttpClient();
        gymImageView = findViewById(R.id.gymImageView);
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
                else {
                    gymImageView.setImageResource(R.drawable.viridiangym);
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
                else {
                    gymImageView.setImageResource(R.drawable.viridiangym);
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

    public void pic() {Picasso.with(this).load(pokeImageFront).into(pokeImgs[poke]);}

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
