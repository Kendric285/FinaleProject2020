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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button add;
    ImageView img;
    Random r = new Random();
    int digiman = (r.nextInt(19) + 0);
    String imgs;
    TextView dad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        img = findViewById(R.id.imageView);
        dad = findViewById(R.id.tv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                OkHttpClient client = new OkHttpClient();

                String url = "https://pokeapi.co/api/v2/pokemon/"+digiman;
                Log.d("mode", "onClick: ");
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

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                   try {
                                        JSONObject obj = new JSONObject(myResponse);
                                        //JSONArray info = obj.getJSONArray("sprites");
                                       String name = obj.getString("name");
                                        dad.setText(""+name);
                                       JSONObject sprites = obj.getJSONObject("sprites");
                                       imgs = sprites.getString("front_default");
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
        });

    }

    public void pic() {
        Picasso.with(this).load(imgs).into(img);

    }
}