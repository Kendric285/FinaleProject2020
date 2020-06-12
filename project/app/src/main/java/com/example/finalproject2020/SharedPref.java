package com.example.finalproject2020;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Array;

public class SharedPref {
    SharedPreferences mySharedPref;
    SharedPreferences.Editor editor;
    int[] pokemons = new int[6];
    int money;

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        editor = mySharedPref.edit();
        pokemons[0] = mySharedPref.getInt("pokemon1",0);
        pokemons[1] = mySharedPref.getInt("pokemon2",0);
        pokemons[2] = mySharedPref.getInt("pokemon3",0);
        pokemons[3] = mySharedPref.getInt("pokemon4",0);
        pokemons[4] = mySharedPref.getInt("pokemon5",0);
        pokemons[5] = mySharedPref.getInt("pokemon6",0);

        money = mySharedPref.getInt("userMoney",0);






    }

    public int getPokemon(int x) {
        return pokemons[x];
    }

    public int addMoney(int x) {
        money = money + x;
        return money;
    }
    public int subMoney(int x){
        money = money - x;
        return money;
    }

    public void addPokemon (int x) {

        if (pokemons[0] == 0) {
            pokemons[0] = x;
            editor.putInt("pokemon1",pokemons[0]).commit();
            Log.d("pokemon", "addPokemon: "+pokemons[0]);
        } else if (pokemons[1] == 0) {
            pokemons[1] = x;
            editor.putInt("pokemon2",pokemons[0]).commit();
        } else if (pokemons[2] == 0) {
            pokemons[2] = x;
            editor.putInt("pokemon3",pokemons[0]).commit();
        } else if (pokemons[3] == 0) {
            pokemons[3] = x;
            editor.putInt("pokemon4",pokemons[0]).commit();
        } else if (pokemons[4] == 0) {
            pokemons[4] = x;
            editor.putInt("pokemon5",pokemons[0]).commit();
        } else if (pokemons[5] == 0) {
            pokemons[5] = x;
            editor.putInt("pokemon6",pokemons[0]).commit();
        } else {
            Log.d("TAG", "addPokemon: CANT CUZ U OUTTA SLOTS NIGGA");
        }
        //editor.putInt("MODE",mode).commit();

    }

    public void pokemonStartBattle() {
        pokemons[0] = 0;
    }

}
