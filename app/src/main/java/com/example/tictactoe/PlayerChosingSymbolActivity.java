package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayerChosingSymbolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chosing_symbol);
        ImageView symbol1=findViewById(R.id.multiplayer_cross);
        symbol1.setImageResource(R.drawable.tictactoe_cross);
        symbol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiplayerGameActivity.player1Symbol=R.drawable.tictactoe_cross;
                MultiplayerGameActivity.player2Symbol=R.drawable.tictactoe_circle;
                Intent newIntent=new Intent(PlayerChosingSymbolActivity.this,MultiplayerGameActivity.class);
                startActivity(newIntent);
                finish();
            }
        });
        ImageView symbol2=findViewById(R.id.multiplayer_dot);
        symbol2.setImageResource(R.drawable.tictactoe_circle);
        symbol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiplayerGameActivity.player1Symbol=R.drawable.tictactoe_circle;
                MultiplayerGameActivity.player2Symbol=R.drawable.tictactoe_cross;
                Intent newIntent=new Intent(PlayerChosingSymbolActivity.this,MultiplayerGameActivity.class);
                startActivity(newIntent);
                finish();
            }
        });
    }
}