package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Button b1=findViewById(R.id.single_player);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent=new Intent(PlayerActivity.this,ChosingSymbolActivity.class);
                startActivity(newIntent);
                finish();
            }
        });
        Button b2=findViewById(R.id.multiplayer);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent=new Intent(PlayerActivity.this,PlayerChosingSymbolActivity.class);
                startActivity(newIntent);
                finish();
            }
        });
    }
}