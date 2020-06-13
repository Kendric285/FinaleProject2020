package com.example.finalproject2020;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Array;

public class SharedPref {
    SharedPreferences mySharedPref;
    SharedPreferences.Editor editor;
    String[] pokemons = new String[6];
    int money;

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        editor = mySharedPref.edit();
        pokemons[0] = mySharedPref.getString("pokemon1","");
        pokemons[1] = mySharedPref.getString("pokemon2","");
        pokemons[2] = mySharedPref.getString("pokemon3","");
        pokemons[3] = mySharedPref.getString("pokemon4","");
        pokemons[4] = mySharedPref.getString("pokemon5","");
        pokemons[5] = mySharedPref.getString("pokemon6","");

        money = mySharedPref.getInt("userMoney",0);





    }

    public int addMoney(int x) {
        money = money + x;
        return money;
    }
    public int subMoney(int x){
        money = money - x;
        return money;
    }

    public void addPokemon (String x) {

        if (pokemons[0] == "") {
            pokemons[0] = x;
            editor.putString("pokemon1",pokemons[0]).commit();
            Log.d("pokemon", "addPokemon: "+pokemons[0]);
        } else if (pokemons[1] == "") {
            pokemons[1] = x;
            editor.putString("pokemon2",pokemons[1]).commit();
        } else if (pokemons[2] == "") {
            pokemons[2] = x;
            editor.putString("pokemon3",pokemons[2]).commit();
        } else if (pokemons[3] == "") {
            pokemons[3] = x;
            editor.putString("pokemon4",pokemons[3]).commit();
        } else if (pokemons[4] == "") {
            pokemons[4] = x;
            editor.putString("pokemon5",pokemons[4]).commit();
        } else if (pokemons[5] == "") {
            pokemons[5] = x;
            editor.putString("pokemon6",pokemons[5]).commit();
        } else {
            Log.d("TAG", "addPokemon: CANT CUZ U OUTTA SLOTS NIGGA");
        }
        //editor.putInt("MODE",mode).commit();

    }

    public void pokemonStartBattle() {
        //pokemons[0] = 0;
    }

}
