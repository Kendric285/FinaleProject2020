package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    String pokeImageFront;
    String pokeImageBack;

    int poke;
    String type;

    SharedPref sharedPref;

    JSONObject usingPokemon;

    ImageView myPokemon;
    TextView pokeName;
    ProgressBar myHP;
    String pokeMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        client = new OkHttpClient();
        sharedPref = new SharedPref(this);
        fight = false;
        pokeName = findViewById(R.id.pokeName);
        myHP = findViewById(R.id.hp);
        myPokemon = findViewById(R.id.myPokemon);

        Random r = new Random();

        tLeft = findViewById(R.id.tLeft);
        bLeft = findViewById(R.id.bLeft);
        tRight = findViewById(R.id.tRight);
        bRight = findViewById(R.id.bRight);


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
                               // pokeMoves =

                                pokeName.setText(name);
                                myHP.setMax(100);
                                myHP.setProgress(100);
                                pic(pokeImageBack,myPokemon);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


    }

    public void getOpponentPokemon(int x, String y) {
        type = y;
        Log.d("mode", "onClick: ");

                            try {
                                JSONObject obj = new JSONObject("shanana");
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                Log.d("pokemon", "run: " + name);
                                JSONObject sprites = obj.getJSONObject("sprites");
                                pokeImageFront = sprites.getString("front_default");

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

    public void pic(String x, ImageView y) {
        Picasso.with(Battle.this).load(x).into(y);
    }

}