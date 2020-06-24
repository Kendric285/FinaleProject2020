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
    Boolean[] badgeCollection = new Boolean[8];
    int[] pokemonLvl = new int[6];
    int[] pokemonExp = new int[6];
    int money = 50;
    int pokeball = 3;

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
        editor = mySharedPref.edit();
        pokemons[0] = mySharedPref.getString("pokemon1","");
        pokemons[1] = mySharedPref.getString("pokemon2","");
        pokemons[2] = mySharedPref.getString("pokemon3","");
        pokemons[3] = mySharedPref.getString("pokemon4","");
        pokemons[4] = mySharedPref.getString("pokemon5","");
        pokemons[5] = mySharedPref.getString("pokemon6","");

        badgeCollection[0] = mySharedPref.getBoolean("badge1", false);
        badgeCollection[1] = mySharedPref.getBoolean("badge2", false);
        badgeCollection[2] = mySharedPref.getBoolean("badge3", false);
        badgeCollection[3] = mySharedPref.getBoolean("badge4", false);
        badgeCollection[4] = mySharedPref.getBoolean("badge5", false);
        badgeCollection[5] = mySharedPref.getBoolean("badge6", false);
        badgeCollection[6] = mySharedPref.getBoolean("badge7", false);
        badgeCollection[7] = mySharedPref.getBoolean("badge8", false);

        pokemonLvl[0] = mySharedPref.getInt("lvl", 1);
        pokemonLvl[1] = mySharedPref.getInt("lvl", 1);
        pokemonLvl[2] = mySharedPref.getInt("lvl", 1);
        pokemonLvl[3] = mySharedPref.getInt("lvl", 1);
        pokemonLvl[4] = mySharedPref.getInt("lvl", 1);
        pokemonLvl[5] = mySharedPref.getInt("lvl", 1);

        pokemonExp[0] = mySharedPref.getInt("exp", 0);
        pokemonExp[1] = mySharedPref.getInt("exp", 0);
        pokemonExp[2] = mySharedPref.getInt("exp", 0);
        pokemonExp[3] = mySharedPref.getInt("exp", 0);
        pokemonExp[4] = mySharedPref.getInt("exp", 0);
        pokemonExp[5] = mySharedPref.getInt("exp", 0);

        money = mySharedPref.getInt("userMoney",50);
        pokeball = mySharedPref.getInt("pokeballs", 3);

    }

    public void addMoney(int x) { money = money + x;editor.putInt("userMoney", money).commit(); }
    public void subMoney(int x){ money = money - x;editor.putInt("userMoney", money).commit(); }
    public int getMoney(){
        return money;
    }

    public void addBalls(int x) { pokeball = pokeball + x;editor.putInt("pokeballs",pokeball).commit(); }
    public void subBalls(int x) { pokeball = pokeball - x;editor.putInt("pokeballs",pokeball).commit(); }
    public int getBalls() {return pokeball;}

    public void addPokemon (String x) {

        if (pokemons[0] == "" || pokemons[0] == null) {
            pokemons[0] = x;
            editor.putString("pokemon1",pokemons[0]).commit();
            Log.d("pokemon", "addPokemon: "+pokemons[0]);
        } else if (pokemons[1] == "" || pokemons[1] == null) {
            pokemons[1] = x;
            editor.putString("pokemon2",pokemons[1]).commit();
        } else if (pokemons[2] == "" || pokemons[2] == null) {
            pokemons[2] = x;
            editor.putString("pokemon3",pokemons[2]).commit();
        } else if (pokemons[3] == "" || pokemons[3] == null) {
            pokemons[3] = x;
            editor.putString("pokemon4",pokemons[3]).commit();
        } else if (pokemons[4] == "" || pokemons[4] == null) {
            pokemons[4] = x;
            editor.putString("pokemon5",pokemons[4]).commit();
        } else if (pokemons[5] == "" || pokemons[5] == null) {
            pokemons[5] = x;
            editor.putString("pokemon6",pokemons[5]).commit();
        } else {
            Log.d("TAG", "addPokemon: CANT CUZ U OUTTA SLOTS");
        }
        //editor.putInt("MODE",mode).commit();

    }

    public void addExp(int x, int y){
        pokemonExp[x] = pokemonExp[x] + y;
        editor.putInt("exp",pokemonExp[x]).commit();
        if(pokemonExp[x] > (pokemonLvl[x]*2) || pokemonExp[x] == (pokemonLvl[x]*2)){
            levelUp(x);
        }
    }

    public void levelUp(int x){
        pokemonLvl[x] = pokemonLvl[x] ++;
        editor.putInt("lvl",pokemonLvl[x]).commit();

    }

    public void opponentPokemon() {

    }

    public void pokemonStartBattle() {
        //pokemons[0] = 0;
    }

    public void addBadge(int gymNum) {
        badgeCollection[gymNum-1] = true;
        editor.putBoolean("badge"+gymNum,badgeCollection[gymNum-1]).commit();
    }

}
