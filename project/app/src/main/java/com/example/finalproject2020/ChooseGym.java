package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseGym extends AppCompatActivity {

    //Pokémon

    CountDownTimer popo;

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

    int seeBadgeClicks;

    Button seeBadges;
    ScrollView badgeCollectionImages;


    ImageView pewterBadge;
    ImageView ceruleanBadge;
    ImageView vermillionBadge;
    ImageView celadonBadge;
    ImageView fuchsiaBadge;
    ImageView saffronBadge;
    ImageView cinnabarBadge;
    ImageView viridianBadge;

    Button wilderness;

    TextView buyPoke;

    Button beat;

    OkHttpClient client;

    Integer gymNumber;

    int userMoney;
    int userPokeBalls;

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

        seeBadgeClicks = 1;

        pewterBadge = findViewById(R.id.pewterBadge);
        ceruleanBadge = findViewById(R.id.ceruleanBadge);
        vermillionBadge = findViewById(R.id.vermillionBadge);
        celadonBadge = findViewById(R.id.celadonBadge);
        fuchsiaBadge = findViewById(R.id.fuchsiaBadge);
        saffronBadge = findViewById(R.id.saffronBadge);
        cinnabarBadge = findViewById(R.id.cinnabarBadge);
        viridianBadge = findViewById(R.id.viridianBadge);

        wilderness = findViewById(R.id.wilderness);

        buyPoke = findViewById(R.id.buyPoke);

        money = findViewById(R.id.money);
        pokeBalls = findViewById(R.id.pokeballs);

        pokeImgs = new ImageView[6];

        seeBadges = findViewById(R.id.seeBadges);
        badgeCollectionImages = findViewById(R.id.badgeScroll);


        wilderness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymNumber = 10;
                toBattle();
            }
        });

        buyPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMoney > 50 || userMoney == 50) {
                    sharedPref.subMoney(50);
                    sharedPref.addBalls(1);
                }else {
                    makeToast("Insufficient Funds");
                }
            }
        });
        seeBadges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeBadgeClicks++;
                if ((seeBadgeClicks % 2) == 0) {
                    badgeCollectionImages.setVisibility(View.VISIBLE);
                    seeBadges.setText("Hide Badges");

                    //pewter

                    if (sharedPref.badgeCollection[0] == false) {

                        pewterBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[0] == true) {
                        pewterBadge.setVisibility(View.VISIBLE);
                    }
                    //cerulean

                    if (sharedPref.badgeCollection[1] == false) {

                        ceruleanBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[1] == true) {
                        ceruleanBadge.setVisibility(View.VISIBLE);
                    }

                    //vermillion
                    if (sharedPref.badgeCollection[2] == false) {

                        vermillionBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[2] == true) {
                        vermillionBadge.setVisibility(View.VISIBLE);
                    }

                    //celadon

                    if (sharedPref.badgeCollection[3] == false) {

                        celadonBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[3] == true) {
                        celadonBadge.setVisibility(View.VISIBLE);
                    }

                    //fuchsia

                    if (sharedPref.badgeCollection[4] == false) {

                        fuchsiaBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[4] == true) {
                        fuchsiaBadge.setVisibility(View.VISIBLE);
                    }

                    //saffron

                    if (sharedPref.badgeCollection[5] == false) {

                        saffronBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[5] == true) {
                        saffronBadge.setVisibility(View.VISIBLE);
                    }

                    //cinnabar

                    if (sharedPref.badgeCollection[6] == false) {

                        cinnabarBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[6] == true) {
                        cinnabarBadge.setVisibility(View.VISIBLE);
                    }

                    if (sharedPref.badgeCollection[7] == false) {

                        viridianBadge.setVisibility(View.INVISIBLE);
                    } else if (sharedPref.badgeCollection[7] == true) {
                        viridianBadge.setVisibility(View.VISIBLE);
                    }


                } else {
                    seeBadges.setText("See Badges");


                    badgeCollectionImages.setVisibility(View.INVISIBLE);


                }
            }


        });


        popoHandler();


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

        for (int i = 0; i < 8; i++) {
            System.out.println(i + 1 + ": " + sharedPref.badgeCollection[i]);
        }


        getPokemon(0);
        gymPic();
        getPokemon(1);
        getPokemon(2);

        gymImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBattle();
            }
        });

        frontArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gymNumber != 8 && gymNumber != 10) {
                    gymNumber++;

                } else {
                    gymNumber = 1;
                }

                gymPic();


            }

        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gymNumber != 1 && gymNumber != 10) {
                    gymNumber--;

                } else {
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

    public void toBattle() {
        Intent toBattle = new Intent(this, Battle.class);
        toBattle.putExtra("gymNumber", gymNumber);

        startActivity(toBattle);
    }

    public void pic() {
        Picasso.with(this).load(pokeImageFront).into(pokeImgs[poke]);
    }

    public static void printBadges(ArrayList<Integer> arrayListName) {

        for (int number = 0; number < arrayListName.size(); number++) {
            System.out.println(arrayListName.get(number));
        }
    }

    public void gymPic() {
        if (gymNumber == 1) {
            gymImageView.setImageResource(R.drawable.pewtergym);
            if (sharedPref.badgeCollection[0] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Pewter Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }
        } else if (gymNumber == 2) {
            gymImageView.setImageResource(R.drawable.ceruleangym);
            if (sharedPref.badgeCollection[1] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Cerulean Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }

        } else if (gymNumber == 3) {
            gymImageView.setImageResource(R.drawable.vermilliongym);

            if (sharedPref.badgeCollection[2] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Vermillion Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }

        }
        //yo
        else if (gymNumber == 4) {
            gymImageView.setImageResource(R.drawable.celadongym);
            if (sharedPref.badgeCollection[3] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Celadon Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }

        }

        //x
        else if (gymNumber == 5) {
            gymImageView.setImageResource(R.drawable.fuchsiagym);

            if (sharedPref.badgeCollection[4] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Fuchsia Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }


        } else if (gymNumber == 6) {
            gymImageView.setImageResource(R.drawable.saffrongym);

            if (sharedPref.badgeCollection[5] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Saffron Gym");
            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }

        } else if (gymNumber == 7) {
            gymImageView.setImageResource(R.drawable.cinnabargym);

            if (sharedPref.badgeCollection[6] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Cinnabar Gym");

            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");
            }

        } else {
            gymImageView.setImageResource(R.drawable.viridiangym);
            gymNumber = 8;
            if (sharedPref.badgeCollection[7] == false) {
                beat.setVisibility(View.INVISIBLE);
                gymName.setText("Viridian Gym");

            } else {
                beat.setVisibility(View.VISIBLE);
                gymName.setText("*COLLECTED*");

            }

        }
    }

    private void popoHandler() {
        popo = new CountDownTimer(999999999, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (userMoney != sharedPref.getMoney() || userPokeBalls != sharedPref.getBalls()) {
                    userMoney = sharedPref.getMoney();
                    userPokeBalls = sharedPref.getBalls();

                    money.setText("Money: " + userMoney);
                    pokeBalls.setText("PokeBalls: " + userPokeBalls);
                }

            }

            @Override
            public void onFinish() {

            }

        }.start();
    }

    public void makeToast(String x){
        Toast.makeText(ChooseGym.this, x, Toast.LENGTH_SHORT).show();
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
