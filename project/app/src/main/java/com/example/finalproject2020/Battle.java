package com.example.finalproject2020;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static android.view.View.VISIBLE;


public class Battle extends AppCompatActivity {

    OkHttpClient client;

    Button tLeft;
    Button bLeft;
    Button tRight;
    Button bRight;

    String move1;
    String move2;
    String move3;
    String move4;

    String move1URL;
    String move2URL;
    String move3URL;
    String move4URL;

    int move1Acc;
    int move2Acc;
    int move3Acc;
    int move4Acc;

    int move1Str;
    int move2Str;
    int move3Str;
    int move4Str;

    String move1i;
    String move2i;
    String move3i;
    String move4i;

    String opponentMovei;

    ArrayList<Integer> badgeCollection = new ArrayList<Integer>();

    Button hide;

    Integer textLengthTime;

    int[] opponentPokemon;

    int noAttackDamageMoveNum;

    String[] noAttackDamage;

    String pokeImageFront;
    String pokeImageBack;

    int poke;
    String type;

    String myPokeName;
    String opponentPokeName;

    TextView myHealthNum;

    TextView opponentPokemonNameText;

    Integer textClicks;

    Handler setDelay;
    Runnable startDelay;




    TextView battleNarration2;


    Boolean opponentIsAttacking;

    Boolean typing = false;

    Boolean first;

    SharedPref sharedPref;

    JSONObject usingPokemon;

    ImageView myPokemon;
    ImageView opponentPokemonImage;
    String opponentPokemonImageURL;

    TextView pokeName;
    TextView battleNarration;

    Integer accCalculate;

    Boolean printing;

    int gymNum;

    boolean battleStarted;



    Random r = new Random();

    ProgressBar myHealth;
    ProgressBar opponentHealth;

    TextView opponentHealthNum;

    TextView opponentPokeMonName;

    int myHP;
    int opponentHP;

    CountDownTimer healthPlr;
    CountDownTimer winnerPlr;

    Integer fighterTurn;

    boolean textPrinting = false;

    Boolean gameOver = false;

    ImageView pok1;
    ImageView pok2;
    ImageView pok3;
    ImageView pok4;
    ImageView pok5;
    ImageView pok6;

    //Opponent Pokemon
    String opponentMove;
    int opponentMoveStr = 30;
    int opponentMoveAcc;
    String opponentMoveName;
    String opponentMoveUrl;
    JSONObject opponentObject;

    int rnPoke = 0;



    RelativeLayout pokemans;
    Boolean choosing = false;

    private String pokeImageFront1;
    private String pokeImageFront2;
    private String pokeImageFront3;
    private String pokeImageFront4;
    private String pokeImageFront5;
    private String pokeImageFront6;
    private Boolean fight;
    @Override///////807 POKEMONNNNS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        client = new OkHttpClient();
        sharedPref = new SharedPref(this);
        pokeName = findViewById(R.id.pokeName);
        myHealthNum = findViewById(R.id.health);
        myHealth = findViewById(R.id.hp);
        opponentHealth = findViewById(R.id.hp2);
        myPokemon = findViewById(R.id.myPokemon);
        opponentPokemonImage = findViewById(R.id.opponentPokemon);
        pok1 = findViewById(R.id.pok1);
        pok2 = findViewById(R.id.pok2);
        pok3 = findViewById(R.id.pok3);
        pok4 = findViewById(R.id.pok4);
        pok5 = findViewById(R.id.pok5);
        pok6 = findViewById(R.id.pok6);
        fight = false;

        pokemans = findViewById(R.id.pokemans);

        getPokemon();
        getMyPokemon(0);



        Intent intent = getIntent();


        gymNum = intent.getIntExtra("gymNumber",0);
        badgeCollection = intent.getIntegerArrayListExtra("badgeCollection");

        battleNarration = findViewById(R.id.battleNarration);

        hide = findViewById(R.id.button);

        myHP = 300;
        opponentHP = 5;

        opponentHealth.setMax(opponentHP);
        opponentHealth.setProgress(opponentHP);

        opponentPokemonNameText = findViewById(R.id.pokeName2);

        opponentHealthNum = findViewById(R.id.health2);

        battleNarration2 = findViewById(R.id.battleNarration2);

        setDelay = new Handler();

        opponentIsAttacking = false;

        printing = true;

        textClicks = 0;

        tLeft = findViewById(R.id.tLeft);
        bLeft = findViewById(R.id.bLeft);
        tRight = findViewById(R.id.tRight);
        bRight = findViewById(R.id.bRight);

        /*first = true;

        if (first == true){
            tLeft.setText("Fight");
            bLeft.setText("Backpack");
            tRight.setText("Pokemon");
            bRight.setText("Run");

            first = false;

        }
        */

        tLeft.setText("Fight");
        bLeft.setText("Backpack");
        tRight.setText("Pokemon");
        bRight.setText("Run");






        getOpponentPokemon(gymNum);

        battleNarration2.setVisibility(View.INVISIBLE);


        tLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move1

                if (move1Str == 0 && move2Str == 0 && move3Str == 0 && move4Str == 0){
                    getMyPokemon(rnPoke);
                }

                if (gameOver == false) {
                    if (textClicks > 0) {

                        if (fight == true && opponentIsAttacking == false && choosing == false) {

                            fight = false;

                            Log.d("poke", "onClick: " + opponentHP);

                            accCalculate = r.nextInt(110);

                            if (move1Str != 0) {


                                // if(move1Acc > accCalculate) {
                                opponentHP = opponentHP - move1Str;

                                battlefood(move1.toUpperCase() + " did " + move1Str + " damage very effective!");
                                Log.d("poke", "onClick: " + myPokeName.toUpperCase() + " used " + move1.toUpperCase());

                                textLengthTime = battleNarration.length() * 100;
                                //  }else{
                                // battlefood( move1.toUpperCase() + " missed and was not effective");
                                //  }


                            } else if (move1Str == 0) {

                                noAttackDamage = new String[3];
                                noAttackDamageMoveNum = r.nextInt(3);


                                noAttackDamage[0] = opponentPokemonNameText.getText() + "'s defense fell!";
                                noAttackDamage[1] = opponentPokemonNameText.getText() + "'s attack fell!";
                                noAttackDamage[2] = opponentPokemonNameText.getText() + "'s accuracy fell!";



                                battlefood((myPokeName + " used " + move1 + ", " + noAttackDamage[noAttackDamageMoveNum]).toUpperCase());
                            }


                            tLeft.setText("Fight");
                            bLeft.setText("Backpack");
                            tRight.setText("Pokemon");
                            bRight.setText("Run");

                            fight = false;
                            opponentIsAttacking = true;

                            System.out.println(move1Str);

                            Log.d("poke", "onClick: " + opponentHP);
                        } else {
                            if (fight == false) {
                                opponentAttacks();
                                fight = true;


                                tLeft.setText("" + move1);
                                bLeft.setText("" + move2);
                                tRight.setText("" + move3);
                                bRight.setText("" + move4);

                                Log.d("poke", "POKE: " + move4Acc + move4Str + move3Acc + move3Str + move2Acc + move2Str + move1Acc + move1Str);
                            }

                        }
                    }
                    if (myHP == 0 || myHP < 0) {
                        myHP = 0;
                        battlefood(opponentPokemonNameText.getText() + " wins !");

                    } else if (opponentHP == 0 || opponentHP < 0) {
                        opponentHP = 0;
                        battlefood(myPokeName.toUpperCase() + " wins !");

                    }

                    //hello
                    
                }
            }
        });

        bLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move2
                if (fight == true && opponentIsAttacking == false && choosing == false) {
                    fight = false;

                    Log.d("poke", "onClick: " + opponentHP);

                    battleNarration.setText("");

                    accCalculate = r.nextInt(110);

                    if (move2Str != 0) {


                     //   if(move2Acc > accCalculate) {
                            opponentHP = opponentHP - move2Str;

                            battlefood(move2.toUpperCase()+" did "+move2Str+" damage very effective!");
                            Log.d("poke", "onClick: " + myPokeName + " used " + move2);

                            textLengthTime = battleNarration.length() * 100;
                    //    }else{
                    //        battlefood( move2.toUpperCase() + " missed and was not effective");


                      //  }














                    } else {
                        noAttackDamage = new String[3];
                        noAttackDamageMoveNum = r.nextInt(3);


                        noAttackDamage[0] = opponentPokemonNameText.getText() + "'s defense fell!";
                        noAttackDamage[1] = opponentPokemonNameText.getText() + "'s attack fell!";
                        noAttackDamage[2] = opponentPokemonNameText.getText() + "'s accuracy fell!";



                        battlefood((myPokeName + " used " + move2 + ", " + noAttackDamage[noAttackDamageMoveNum]).toUpperCase());
                    }









                    tLeft.setText("Fight");
                    bLeft.setText("Backpack");
                    tRight.setText("Pokemon");
                    bRight.setText("Run");
                    fight = false;
                    opponentIsAttacking = true;


                    Log.d("poke", "onClick: " + opponentHP);


                }else if (fight == true) {
                    opponentAttacks();
                }

                if(myHP == 0 || myHP < 0){
                    myHP = 0;
                    battlefood(opponentPokemonNameText.getText() + " wins!");

                }else if(opponentHP == 0 || opponentHP < 0) {
                    opponentHP = 0;
                    battlefood(myPokeName.toUpperCase() + " wins!");

                }
            }
        });

        tRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move3

                if (fight == false && choosing == false && battleStarted){
                    pokemans.setVisibility(VISIBLE);
                    choosing = true;
                    tLeft.setText("");
                    bLeft.setText("");
                    tRight.setText("back");
                    bRight.setText("");
                } else if (choosing == true) {
                    tLeft.setText("Fight");
                    bLeft.setText("Backpack");
                    tRight.setText("Pokemon");
                    bRight.setText("Run");
                    choosing = false;
                    pokemans.setVisibility(View.INVISIBLE);
                }


                if (fight == true && opponentIsAttacking == false && choosing == false) {
                    fight = false;
                    Log.d("poke", "onClick: " + opponentHP);

                    battleNarration.setText("");

                    accCalculate = r.nextInt(110);

                    if (move3Str != 0) {

                   //     if(move3Acc > accCalculate) {
                            opponentHP = opponentHP - move3Str;

                            battlefood(move3.toUpperCase()+" did "+move3Str+" damage very effective!");
                            Log.d("poke", "onClick: " + myPokeName.toUpperCase() + " used " + move3);

                            textLengthTime = battleNarration.length() * 100;
                      //  }else{
                            //battlefood(move3.toUpperCase() + " missed and was not effective");
                     //   }

                    } else {
                        noAttackDamage = new String[3];
                        noAttackDamageMoveNum = r.nextInt(3);


                        noAttackDamage[0] = opponentPokemonNameText.getText() + "'s defense fell!";
                        noAttackDamage[1] = opponentPokemonNameText.getText() + "'s attack fell!";
                        noAttackDamage[2] = opponentPokemonNameText.getText() + "'s accuracy fell!";



                        battlefood((myPokeName + " used " + move3 + ", " + noAttackDamage[noAttackDamageMoveNum]).toUpperCase());
                    }



                    tLeft.setText("Fight");
                    bLeft.setText("Backpack");
                    tRight.setText("Pokemon");
                    bRight.setText("Run");
                    fight = false;
                    opponentIsAttacking = true;



                }else if (fight == true){
                    opponentAttacks();
                }
                if(myHP == 0 || myHP < 0){
                    myHP = 0;
                    battlefood(opponentPokemonNameText.getText() + " wins!");

                }else if(opponentHP == 0 || opponentHP < 0) {
                    opponentHP = 0;
                    battlefood(myPokeName.toUpperCase() + " wins!");

                }

            }
        });

        bRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move4

                if (fight == false && gymNum == 10 && battleStarted) {
                    int runAcc = r.nextInt(110);
                    if (runAcc > 40) {
                        backToGyms(10);
                    } else {
                        battlefood("You could not escape!");
                    }
                } else if (gymNum != 10 && battleStarted) {
                    battlefood("Cannot run in a trainer battle!");
                }

                if (fight == true && opponentIsAttacking == false && choosing == false) {
                fight = false;


                    Log.d("poke", "onClick: " + opponentHP);

                    battleNarration.setText("");

                    accCalculate = r.nextInt(110);

                    if (move4Str != 0) {


                        if(move4Acc > accCalculate) {
                            opponentHP = opponentHP - move4Str;

                                battlefood(move4.toUpperCase() + " did " + move4Str + " damage");
                                Log.d("poke", "onClick: " + myPokeName.toUpperCase() + " used " + move4.toUpperCase());

                            textLengthTime = battleNarration.length() * 100;
                        }else{

                            battlefood(move4.toUpperCase() + " missed and was not effective");

                        }

                    } else {
                        if(battleNarration.getVisibility() == VISIBLE){
                            battleNarration.setText("");
                            battleNarration2.setText("");
                            battleNarration2.setVisibility(VISIBLE);
                            battleNarration.setVisibility(View.INVISIBLE);

                            noAttackDamage = new String[3];
                            noAttackDamageMoveNum = r.nextInt(3);


                            noAttackDamage[0] = opponentPokemonNameText.getText() + "'s defense fell!";
                            noAttackDamage[1] = opponentPokemonNameText.getText() + "'s attack fell!";
                            noAttackDamage[2] = opponentPokemonNameText.getText() + "'s accuracy fell!";



                            setBattleNarration2((myPokeName + " used " + move4 + ", " + noAttackDamage[noAttackDamageMoveNum]).toUpperCase());
                        }else{
                            battleNarration.setText("");
                            battleNarration2.setText("");
                            battleNarration2.setVisibility(View.INVISIBLE);
                            battleNarration.setVisibility(VISIBLE);

                            noAttackDamage = new String[3];
                            noAttackDamageMoveNum = r.nextInt(3);


                            noAttackDamage[0] = opponentPokemonNameText.getText() + "'s defense fell!";
                            noAttackDamage[1] = opponentPokemonNameText.getText() + "'s attack fell!";
                            noAttackDamage[2] = opponentPokemonNameText.getText() + "'s accuracy fell!";
                            setBattleNarration((myPokeName + " used " + move4 + ", " + noAttackDamage[noAttackDamageMoveNum]).toUpperCase());
                        }
                    }
                    tLeft.setText("Fight");
                    bLeft.setText("Backpack");
                    tRight.setText("Pokemon");
                    bRight.setText("Run");
                    fight = false;
                    opponentIsAttacking = true;


                } else if (fight == true){
                    opponentAttacks();
                }
                if(myHP == 0 || myHP < 0){
                    myHP = 0;
                    battlefood(opponentPokemonNameText.getText() + " wins!");

                }else if(opponentHP == 0 || opponentHP < 0) {
                    opponentHP = 0;
                    battlefood(myPokeName.toUpperCase() + " wins!");

                }


            }
        });

        battleNarration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textClicks == 0) {
                    opponentThrowsPokemon();
                    textClicks++;
                }else{
                    System.out.println("nothing");

                }

                if(gameOver){
                    backToGyms(gymNum);
                }






            }
        });


        battleNarration2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(gameOver){
                    backToGyms(gymNum);
                }




            }
        });

      /*  if(first == false) {

            if (fight == false && opponentIsAttacking == true) {

                tLeft.setText("Fight");
                bLeft.setText("Backpack");
                tRight.setText("Pokemon");
                bRight.setText("Run");

            } else {

                tLeft.setText("" + move1);
                bLeft.setText("" + move2);
                tRight.setText("" + move3);
                bRight.setText("" + move4);

            }

        }


       */

    }

    public void getMyPokemon(int x) {
        Log.d("mode", "onClick: ");
        poke = x;
                            try {
                                JSONObject obj = new JSONObject(sharedPref.pokemons[poke]);
                                //JSONArray info = obj.getJSONArray("sprites");

                                String name = obj.getString("name");
                                myPokeName = name;
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
                                JSONObject m2 = move02.getJSONObject("move");
                                JSONObject m3 = move03.getJSONObject("move");
                                JSONObject m4 = move04.getJSONObject("move");



                                // pokeMoves =

                                move1 = m1.getString("name");
                                move2 = m2.getString("name");
                                move3 = m3.getString("name");
                                move4 = m4.getString("name");

                                // pokeMoves STATS

                                move1URL = m1.getString("url");
                                move2URL = m2.getString("url");
                                move3URL = m3.getString("url");
                                move4URL = m4.getString("url");

                                //Other Shit

                                pokeName.setText(name.toUpperCase());
                                myHealth.setMax(300);
                                myHealth.setProgress(300);
                                pic(pokeImageBack,myPokemon);

                                // GET ALL MOVES!!!!!

                                final Request request1 = new Request.Builder()
                                        .url(move1URL)
                                        .get()
                                        .build();

                                client.newCall(request1).enqueue(new Callback() {
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
                                                        JSONObject objMove1 = new JSONObject(myResponse);
                                                        move1i = myResponse;
                                                        move1Str = objMove1.getInt("power");
                                                        move1Acc = objMove1.getInt("accuracy");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });

                                final Request request2 = new Request.Builder()
                                        .url(move2URL)
                                        .get()
                                        .build();

                                client.newCall(request2).enqueue(new Callback() {
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
                                                        JSONObject objMove2 = new JSONObject(myResponse);
                                                        move2i = myResponse;
                                                        move2Str = objMove2.getInt("power");
                                                        move2Acc = objMove2.getInt("accuracy");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });

                                final Request request3 = new Request.Builder()
                                        .url(move3URL)
                                        .get()
                                        .build();

                                client.newCall(request3).enqueue(new Callback() {
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
                                                        JSONObject objMove3= new JSONObject(myResponse);
                                                        move3i = myResponse;
                                                        move3Str = objMove3.getInt("power");
                                                        move3Acc = objMove3.getInt("accuracy");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    }

                                });

                                final Request request4 = new Request.Builder()
                                        .url(move4URL)
                                        .get()
                                        .build();

                                client.newCall(request4).enqueue(new Callback() {
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
                                                        JSONObject objMove4 = new JSONObject(myResponse);
                                                        move4i = myResponse;
                                                        move4Str = objMove4.getInt("power");
                                                        move4Acc = objMove4.getInt("accuracy");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
        healthHandler();

    }

    public void getOpponentPokemon(int x) {
        if (x == 1){
            //
            opponentPokemonImage.setImageResource(R.drawable.brock);
            battleNarration.setText("");

            setBattleNarration("BROCK would like to battle!");

            opponentPokemon = new int[2];
            opponentPokemon[0] = 95;
            opponentPokemon[1] = 74;
        }
        else if(x == 2){
            opponentPokemon = new int[4];

            opponentPokemonImage.setImageResource(R.drawable.misty);
            battleNarration.setText("");

            setBattleNarration("MISTY would like to battle!");
            opponentPokemon[0] = 55;
            opponentPokemon[1] = 121;
            opponentPokemon[2] = 131;
            opponentPokemon[3] = 119;
        }
        else if(x == 3){
            opponentPokemon = new int[3];
            opponentPokemonImage.setImageResource(R.drawable.ltsurge);
            battleNarration.setText("");

            setBattleNarration("Lt. SURGE would like to battle!");
            opponentPokemon[0] = 100;
            opponentPokemon[1] = 26;
            opponentPokemon[2] = 25;

        }
        else if(x == 4){
            opponentPokemon = new int[3];

            opponentPokemonImage.setImageResource(R.drawable.erika);
            battleNarration.setText("");

            setBattleNarration("ERIKA would like to battle!");
            opponentPokemon[0] = 71;
            opponentPokemon[1] = 114;
            opponentPokemon[2] = 45;
        }
        else if(x == 5){
            opponentPokemon = new int[3];

            opponentPokemonImage.setImageResource(R.drawable.koga);
            battleNarration.setText("");

            setBattleNarration("KOGA would like to battle!");

            opponentPokemon[0] = 109;
            opponentPokemon[1] = 89;
            opponentPokemon[2] = 110;
        }
        else if(x == 6){
            opponentPokemon = new int[4];

            opponentPokemonImage.setImageResource(R.drawable.sabrina);
            battleNarration.setText("");

            setBattleNarration("SABRINA would like to battle!");

            opponentPokemon[0] = 64;
            opponentPokemon[1] = 122;
            opponentPokemon[2] = 65;
            opponentPokemon[3] = 196;
        }
        else if(x == 7){
            opponentPokemon = new int[7];
            opponentPokemonImage.setImageResource(R.drawable.blaine);
            battleNarration.setText("");

            setBattleNarration("BLAINE would like to battle!");
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
            opponentPokemonImage.setImageResource(R.drawable.giovanni);
            battleNarration.setText("");

            setBattleNarration("GIOVANNI would like to battle!");
            opponentPokemon[0] = 111;
            opponentPokemon[1] = 115;
            opponentPokemon[2] = 51;
        }

        else if(x == 10){
            opponentPokemon = new int[1];
            opponentPokemonImage.setImageResource(R.drawable.questions2);

            battleNarration.setText("");

            setBattleNarration("You've encountered a wild Pokémon");


            int randWild = r.nextInt(807);

            opponentPokemon[0] = randWild;
        }

        else{
            System.out.println("toobad");
        }

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
                         /*   Log.d("pokenum", "getOpponentPokemon: " + opponentPokemon[1]);
                            Log.d("pokenum", "getOpponentPokemon: " + opponentPokemonImageURL);
                            Log.d("pokelength", "" + opponentPokemon.length);

                            System.out.println(opponentPokemon.length);

                          */






                        }






    public void pic(String x, ImageView y) {
        Picasso.with(Battle.this).load(x).into(y);
    }
    public void setBattleNarration(final String s) {
        if (typing = true) {
           // timer1.cancel();
        }
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Timer timer = new Timer();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c = s.charAt(i[0]);
                battleNarration.append(String.valueOf(c));
                i[0]++;
                typing = true;
                Log.d("poke", "RUNNING: ");
                hide.setVisibility(VISIBLE);

                /*

                hide = findViewById(R.id.button);


                hide.setHeight(700);
                hide.setWidth(700);

                 */
                if (i[0] == length) {
                    timer.cancel();
                    /*



                    hide = findViewById(R.id.button);


                    hide.setHeight(1);
                    hide.setWidth(1);

                     */
                    hide();

                    //hiding = true;
                    //hide.setVisibility(View.INVISIBLE);
                }

            }
        };
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {

                    hide();
                    /*
                    hide = findViewById(R.id.button);


                    hide.setHeight(1);
                    hide.setWidth(1);

                     */


                    Log.d("poke", "INVDISIFGADFJDAJRJEIGJIREJIGJIREJGIJRIJEGIIJG ");

                    typing = false;
                    //hide.setVisibility(View.INVISIBLE);
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 60);


    }
    public void setBattleNarration2(final String s) {
        if (typing = true) {
          //timer2.cancel();
        }
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();

        final Timer timer = new Timer();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c = s.charAt(i[0]);
                battleNarration2.append(String.valueOf(c));
                i[0]++;
                typing = true;

                hide.setVisibility(VISIBLE);
                Log.d("poke", "RUNNING: ");

                /*

                hide = findViewById(R.id.button);

                hide.setHeight(700);
                hide.setWidth(700);

                 */

                if (i[0] == length) {
                    timer.cancel();
                    /*



                    hide = findViewById(R.id.button);


                    hide.setHeight(1);
                    hide.setWidth(1);

                     */
                    hide();

                    //hiding = true;
                    //hide.setVisibility(View.INVISIBLE);
                }


            }
        };
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {

                    /*



                    hide = findViewById(R.id.button);


                    hide.setHeight(1);
                    hide.setWidth(1);

                     */
                    hide();

                    typing = false;
                    //hide.setVisibility(View.INVISIBLE);
                }

            }
        };
        timer.schedule(taskEverySplitSecond, 1, 60);


    }
    public void opponentThrowsPokemon(){
        Random rand = new Random();
        int pokeNum = r.nextInt(opponentPokemon.length);

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
                                opponentObject = obj;
                                //JSONArray info = obj.getJSONArray("sprites");
                                String opponentPokeName = obj.getString("name");
                                JSONObject sprites = obj.getJSONObject("sprites");
                                opponentPokemonImageURL = sprites.getString("front_default");

                                // pokeMoves STATS


                                if (gymNum == 1){
                                    //
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("BROCK sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("BROCK sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
battleStarted = true;

                                }
                                else if(gymNum == 2){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("MISTY sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("MISTY sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 3){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("Lt. SURGE sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("Lt. SURGE sent out " + opponentPokeName.toUpperCase() + " !");
                                    }

                                    battleStarted = true;
                                }
                                else if(gymNum == 4){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("ERIKA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("ERIKA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 5){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("KOGA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("KOGA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 6){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("SABRINA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("SABRINA sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 7){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("BLAINE sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("BLAINE sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 8){
                                    battleNarration.setText("");
                                    if(battleNarration.getVisibility() == VISIBLE){
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(VISIBLE);
                                        battleNarration.setVisibility(View.INVISIBLE);
                                        setBattleNarration2("GIOVANNI sent out " + opponentPokeName.toUpperCase() + " !");
                                    }else{
                                        battleNarration.setText("");
                                        battleNarration2.setText("");
                                        battleNarration2.setVisibility(View.INVISIBLE);
                                        battleNarration.setVisibility(VISIBLE);
                                        setBattleNarration("GIOVANNI sent out " + opponentPokeName.toUpperCase() + " !");
                                    }
                                    battleStarted = true;
                                }
                                else if(gymNum == 10){
                                    battlefood(opponentPokeName.toUpperCase() + " has appeared, what will you do ?!!");
                                    battleStarted = true;
                                }

                                else{
                                    System.out.println("toobad");
                                }
                                pic(opponentPokemonImageURL, opponentPokemonImage);
                                opponentPokemonNameText.setText(opponentPokeName.toUpperCase());
                                //hide.setVisibility(View.INVISIBLE);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        /*final Request request1 = new Request.Builder()
                .url(opponentAttackURL)
                .get()
                .build();

        client.newCall(request1).enqueue(new Callback() {
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
                                JSONObject objMove1 = new JSONObject(myResponse);
                                opponentMovei = myResponse;
                                opponentAttackDamage = objMove1.getInt("power");
                                opponentAttackAcc = objMove1.getInt("accuracy");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }); */
            //System.out.println("hello" + opponentAttackURL);

    }

    public void opponentAttacks(){



            JSONArray moves = null;
            try {
                moves = opponentObject.getJSONArray("moves");

                int papa = moves.length();
                int laka = r.nextInt(papa);

                JSONObject move01 = moves.getJSONObject(laka);

                JSONObject m1 = move01.getJSONObject("move");

                opponentMoveName = m1.getString("name");
                opponentMoveUrl = m1.getString("url");

                final Request requestOpp = new Request.Builder()
                        .url(opponentMoveUrl)
                        .get()
                        .build();

                client.newCall(requestOpp).enqueue(new Callback() {
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
                                        JSONObject objMove1 = new JSONObject(myResponse);
                                        move1i = myResponse;
                                        opponentMoveStr = objMove1.getInt("power");
                                        opponentMoveAcc = objMove1.getInt("accuracy");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });

               // battleNarration.setText("");


                Log.d("attack", "opponentAttacks: " + opponentMoveStr);


                if (opponentMoveStr != 0) {

                    accCalculate = r.nextInt(110);

                    if(opponentMoveAcc < accCalculate) {
                        myHP = myHP - opponentMoveStr;

                        battlefood(opponentPokemonNameText.getText()  + "'S " +opponentMoveName.toUpperCase()+" did "+opponentMoveStr+" damage very effective!");
                        Log.d("poke", "onClick: " + myPokeName.toUpperCase() + " used " + move1.toUpperCase());

                        textLengthTime = battleNarration.length() * 100;
                    }else{
                        battlefood(opponentPokemonNameText.getText()  + "'S "+ opponentMoveName.toUpperCase() + " missed and was not effective");

                    }

                } else {
                    battleNarration.setText("");
                    battleNarration2.setText("");
                    setBattleNarration(opponentPokemonNameText.getText()+" used "+opponentMoveName.toUpperCase()+"!");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        opponentIsAttacking = false;
        fight = true;
    }

    public void getOpponentMove(){

    }

    public void battlefood(String x){
        if(battleNarration.getVisibility() == VISIBLE){
            battleNarration.setText("");
            battleNarration2.setText("");
            battleNarration2.setVisibility(VISIBLE);
            battleNarration.setVisibility(View.INVISIBLE);
            setBattleNarration2(x);
        }else{
            battleNarration.setText("");
            battleNarration2.setText("");
            battleNarration2.setVisibility(View.INVISIBLE);
            battleNarration.setVisibility(VISIBLE);
            setBattleNarration(x);
        }
    }


    private void healthHandler(){
        healthPlr = new CountDownTimer(999999999,1) {
            @Override
            public void onTick (long millisUntilFinished){

                opponentHealth.setProgress(opponentHP);
                opponentHealthNum.setText(opponentHP + "/300");

                myHealth.setProgress(myHP);
                myHealthNum.setText(myHP +"/300");

                if(myHP == 0 || myHP < 0){
                    myHP = 0;
                    if (gameOver == false) {
                       // backToGyms(gymNum);
                    }
                    gameOver = true;
                }else if(opponentHP == 0 || opponentHP < 0) {
                    opponentHP = 0;
                    if (gameOver == false) {
                     //   backToGyms(gymNum);
                    }
                    gameOver = true;
                }

            }

            @Override
            public void onFinish () {

            }

        }.start();
    }



    private void hide(){
       hide.setVisibility(View.INVISIBLE);
    }

    public void backToGyms(int gymNum){
        Intent toGyms = new Intent(this, ChooseGym.class);


        if(gymNum != 10) {


            Log.d("poke2", "backToGyms1:DADDY " + gymNum + " THIS 4 U DADDY" + sharedPref.badgeCollection[gymNum - 1]);
            if (opponentHP == 0 || opponentHP < 0) {
                Log.d("poke2", "backToGyms2:DADDY " + gymNum + " THIS 4 U DADDY" + sharedPref.badgeCollection[gymNum - 1]);
                sharedPref.addBadge(gymNum);
                sharedPref.addMoney(100);
                Log.d("poke2", "backToGyms3:DADDY " + gymNum + " THIS 4 U DADDY" + sharedPref.badgeCollection[gymNum - 1]);
            }
        }

        startActivity(toGyms);

    }

    public void getPokemon() {
        //poke = y;
        Log.d("mode", "onClick: ");

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[0]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront1 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront1).into(pok1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[1]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront2 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront2).into(pok2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[2]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront3 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront3).into(pok3);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[3]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront4 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront4).into(pok4);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[4]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront5 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront5).into(pok5);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(sharedPref.pokemons[5]);
            //JSONArray info = obj.getJSONArray("sprites");
            String name = obj.getString("name");
            Log.d("pokemon", "run: " + name);
            JSONObject sprites = obj.getJSONObject("sprites");
            pokeImageFront6 = sprites.getString("front_default");
            Picasso.with(this).load(pokeImageFront6).into(pok6);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
