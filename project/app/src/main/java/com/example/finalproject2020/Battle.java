package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class Battle extends AppCompatActivity {

    OkHttpClient client;

    boolean fight;
    Button tLeft;
    Button bLeft;
    Button tRight;
    Button bRight;

    String move1;
    String move2;
    String move3;
    String move4;

    int[] opponentPokemon;

    String pokeImageFront;
    String pokeImageBack;

    int poke;
    String type;

    SharedPref sharedPref;

    JSONObject usingPokemon;

    ImageView myPokemon;
    ImageView opponentPokemonImage;
    String opponentPokemonImageURL;

    TextView pokeName;

    int gymNum;

    Random r = new Random();

    ProgressBar myHP;
    String pokeMoves;

    @Override///////807 POKEMONNNNS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        client = new OkHttpClient();
        sharedPref = new SharedPref(this);
        fight = false;
        pokeName = findViewById(R.id.pokeName);
        myHP = findViewById(R.id.hp);
        myPokemon = findViewById(R.id.myPokemon);
        opponentPokemonImage = findViewById(R.id.opponentPokemon);
        Intent intent = getIntent();
        gymNum = intent.getIntExtra("gymNumber",0);

        tLeft = findViewById(R.id.tLeft);
        bLeft = findViewById(R.id.bLeft);
        tRight = findViewById(R.id.tRight);
        bRight = findViewById(R.id.bRight);

        getOpponentPokemon(gymNum);

        if (fight == false) {

            tLeft.setText("Fight");
            bLeft.setText("Backpack");
            tRight.setText("Pokemon");
            bRight.setText("Run");

            tLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fight = true;

                    tLeft.setText("" + move1);
                    bLeft.setText("" + move2);
                    tRight.setText("" + move3);
                    bRight.setText("" + move4);


                }
            });

        } else {

            tLeft.setText("" + move1);
            bLeft.setText("" + move2);
            tRight.setText("" + move3);
            bRight.setText("" + move4);

        }

        getMyPokemon(0);

    }

    public void pokemon(){

    }

    public void getMyPokemon(int x) {
        Log.d("mode", "onClick: ");
        poke = x;
                            try {
                                JSONObject obj = new JSONObject(sharedPref.pokemons[poke]);
                                //JSONArray info = obj.getJSONArray("sprites");

                                String name = obj.getString("name");
                                Log.d("pokemon", "run: " + name);
                                JSONObject sprites = obj.getJSONObject("sprites");
                                pokeImageBack = sprites.getString("back_default");
                                JSONArray moves = obj.getJSONArray("moves");
                                int papa = moves.length();
                                int laka = r.nextInt(papa);
                                int laka2 = r.nextInt(papa);
                                int laka3 = r.nextInt(papa);
                                int laka4 = r.nextInt(papa);

                                JSONObject move01 = moves.getJSONObject(laka);
                                JSONObject move02 = moves.getJSONObject(laka2);
                                JSONObject move03 = moves.getJSONObject(laka3);
                                JSONObject move04 = moves.getJSONObject(laka4);
                                JSONObject m1 = move01.getJSONObject("move");
                                JSONObject m2 = move01.getJSONObject("move");
                                JSONObject m3 = move01.getJSONObject("move");
                                JSONObject m4 = move01.getJSONObject("move");

                                // pokeMoves =

                                move1 = m1.getString("name");
                                move2 = m2.getString("name");
                                move3 = m3.getString("name");
                                move4 = m4.getString("name");

                                //Other Shit

                                pokeName.setText(name);
                                myHP.setMax(100);
                                myHP.setProgress(100);
                                pic(pokeImageBack,myPokemon);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


    }

    public void getOpponentPokemon(int x) {
        if (x == 1){
            //
            opponentPokemon = new int[2];
            opponentPokemon[0] = 95;
            opponentPokemon[1] = 74;
        }
        else if(x == 2){
            opponentPokemon = new int[4];
            opponentPokemon[0] = 55;
            opponentPokemon[1] = 121;
            opponentPokemon[2] = 131;
            opponentPokemon[3] = 119;
        }
        else if(x == 3){
            opponentPokemon = new int[3];
            opponentPokemon[0] = 100;
            opponentPokemon[1] = 26;
            opponentPokemon[2] = 25;

        }
        else if(x == 4){
            opponentPokemon = new int[3];
            opponentPokemon[0] = 71;
            opponentPokemon[1] = 114;
            opponentPokemon[2] = 45;
        }
        else if(x == 5){
            opponentPokemon = new int[3];
            opponentPokemon[0] = 109;
            opponentPokemon[1] = 89;
            opponentPokemon[2] = 110;
        }
        else if(x == 6){
            opponentPokemon = new int[4];
            opponentPokemon[0] = 64;
            opponentPokemon[1] = 122;
            opponentPokemon[2] = 65;
            opponentPokemon[3] = 196;
        }
        else if(x == 7){
            opponentPokemon = new int[7];
            opponentPokemon[0] = 58;
            opponentPokemon[1] = 77;
            opponentPokemon[2] = 78;
            opponentPokemon[3] = 59;
            opponentPokemon[4] = 38;
            opponentPokemon[5] = 126;
            opponentPokemon[6] = 467;
        }
        else if(x == 8){
            opponentPokemon = new int[3];
            opponentPokemon[0] = 111;
            opponentPokemon[1] = 115;
            opponentPokemon[2] = 51;
        }

        else{
            System.out.println("toobad");
        }
        Random rand = new Random();
        int pokeNum = rand.nextInt(opponentPokemon.length);

        final String type;
        Log.d("mode", "onClick: ");
        final Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/" + opponentPokemon[pokeNum])
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

                    Battle.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                JSONObject sprites = obj.getJSONObject("sprites");
                                opponentPokemonImageURL = sprites.getString("front_default");
                                pic(opponentPokemonImageURL, opponentPokemonImage);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
                           /* try {
                                JSONObject obj = new JSONObject("shanana");
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                Log.d("pokemon", "run: " + name);
                                JSONObject sprites = obj.getJSONObject("sprites");
                                pokeImageFront = sprites.getString("front_default");

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }*/
                            Log.d("pokenum", "getOpponentPokemon: " + opponentPokemon[1]);
                            Log.d("pokenum", "getOpponentPokemon: " + opponentPokemonImageURL);
                            Log.d("pokelength", "" + opponentPokemon.length);

                            System.out.println(opponentPokemon.length);







                        }






    public void pic(String x, ImageView y) {
        Picasso.with(Battle.this).load(x).into(y);
    }

}