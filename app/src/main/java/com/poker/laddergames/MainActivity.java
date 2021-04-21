package com.poker.laddergames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.btnMainPlay);
        salir = findViewById(R.id.btnMainClose);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGames();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initGames(){
        Intent i = new Intent(this, GamesActivity.class);
        startActivity(i);
    }
}
