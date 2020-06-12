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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button add;

    ImageView bulbasaurImageView;
    ImageView squirtleImageView;
    ImageView charmanderImageView;

    Random r = new Random();
    int digiman = (r.nextInt(19) + 0);

    String bulbasaurImageViewURL;
    String squirtleImageViewURL;
    String charmanderImageViewURL;

    String userStarterPokemonImage;

    TextView dad;


    OkHttpClient client;

    String bulbasaurApiInfoURL;
    String squirtleApiInfoURL;
    String charmanderApiInfoURL;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = new SharedPref(this);
        client = new OkHttpClient();

        add = findViewById(R.id.add);

        bulbasaurImageView = findViewById(R.id.bulbasaurImageView);
        squirtleImageView = findViewById(R.id.squirtleImageView);
        charmanderImageView = findViewById(R.id.charmanderImageView);

        if (sharedPref.pokemons[0] == 0) {
            bulbasaurImageView.setVisibility(View.VISIBLE);
            squirtleImageView.setVisibility(View.VISIBLE);
            charmanderImageView.setVisibility(View.VISIBLE);
        } else {
            bulbasaurImageView.setVisibility(View.INVISIBLE);
            squirtleImageView.setVisibility(View.INVISIBLE);
            charmanderImageView.setVisibility(View.INVISIBLE);
        }

        bulbasaurApiInfoURL = "https://pokeapi.co/api/v2/pokemon/1";
        squirtleApiInfoURL = "https://pokeapi.co/api/v2/pokemon/7";
        charmanderApiInfoURL = "https://pokeapi.co/api/v2/pokemon/4";

        add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                starterPokemon();



            }
        });

        bulbasaurImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStarterPokemonImage = bulbasaurImageViewURL;
                gyms();
                sharedPref.addPokemon(1);
            }
        });
        squirtleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStarterPokemonImage = squirtleImageViewURL;
                gyms();
                sharedPref.addPokemon(7);
            }
        });
        charmanderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStarterPokemonImage = charmanderImageViewURL;
                gyms();
                sharedPref.addPokemon(4);
            }
        });

    }

    public void pic() {
        Picasso.with(this).load(bulbasaurImageViewURL).into(bulbasaurImageView);
        Picasso.with(this).load(squirtleImageViewURL).into(squirtleImageView);
        Picasso.with(this).load(charmanderImageViewURL).into(charmanderImageView);

    }
    public void starterPokemon(){
        getSquirtle();
        getCharmander();
        getBulbasaur();
    }
    public void getSquirtle(){
        final Request request = new Request.Builder()
                .url(squirtleApiInfoURL)
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

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                JSONObject sprites = obj.getJSONObject("sprites");
                                squirtleImageViewURL = sprites.getString("front_default");
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
    public void getCharmander(){
        final Request request = new Request.Builder()
                .url(charmanderApiInfoURL)
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

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                JSONObject sprites = obj.getJSONObject("sprites");
                                charmanderImageViewURL = sprites.getString("front_default");
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

    public void getBulbasaur(){
        Log.d("mode", "onClick: ");
        final Request request = new Request.Builder()
                .url(bulbasaurApiInfoURL)
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

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                //JSONArray info = obj.getJSONArray("sprites");
                                String name = obj.getString("name");
                                JSONObject sprites = obj.getJSONObject("sprites");
                                bulbasaurImageViewURL = sprites.getString("front_default");
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
    public void gyms(){
        Intent toGyms = new Intent(this, ChooseGym.class);
        toGyms.putExtra("starter",userStarterPokemonImage );
        startActivity(toGyms);
    }


}