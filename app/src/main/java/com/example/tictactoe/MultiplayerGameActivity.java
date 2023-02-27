package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiplayerGameActivity extends AppCompatActivity implements View.OnClickListener {
    public static int player1Symbol;
    public static int player2Symbol;
    AlertDialog.Builder builder;
    private int hasImage[][] = new int[3][3];
    final ImageView ar[][] = new ImageView[3][3];
    int winner=0,turns=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game);
        ar[0][0] = findViewById(R.id.multiplayer_array_11);
        ar[0][1] = findViewById(R.id.multiplayer_array_12);
        ar[0][2] = findViewById(R.id.multiplayer_array_13);
        ar[1][0] = findViewById(R.id.multiplayer_array_21);
        ar[1][1] = findViewById(R.id.multiplayer_array_22);
        ar[1][2] = findViewById(R.id.multiplayer_array_23);
        ar[2][0] = findViewById(R.id.multiplayer_array_31);
        ar[2][1] = findViewById(R.id.multiplayer_array_32);
        ar[2][2] = findViewById(R.id.multiplayer_array_33);
        ar[0][0].setOnClickListener(this);
        ar[0][1].setOnClickListener(this);
        ar[1][0].setOnClickListener(this);
        ar[1][1].setOnClickListener(this);
        ar[1][2].setOnClickListener(this);
        ar[2][0].setOnClickListener(this);
        ar[2][1].setOnClickListener(this);
        ar[2][2].setOnClickListener(this);
        ar[0][2].setOnClickListener(this);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                hasImage[i][j]=-1;
            }
        }
        builder=new AlertDialog.Builder(this);
    }
    @Override
    public void onClick(View v) {
        ImageView i=findViewById(v.getId());
        TextView text=findViewById(R.id.player_info);
        if(turns%2==0){
            if(error(v.getId())) {
                matchIDPlayer1(v.getId());
                i.setImageResource(player1Symbol);
                text.setText("Player 2's turn");
                if (winnerChosing()) {
                    declaration();
                    gameContinuation();
                }
            }
            else {
                Toast.makeText(MultiplayerGameActivity.this, "Please select another box", Toast.LENGTH_LONG).show();
                return;
            }
        }
        else {
            if(error(v.getId())) {
                matchIDPlayer2(v.getId());
                i.setImageResource(player2Symbol);
                text.setText("Player 1's turn");
                if (winnerChosing()) {
                    declaration();
                    gameContinuation();
                }
            }
            else {
                Toast.makeText(MultiplayerGameActivity.this, "Please select another box", Toast.LENGTH_LONG).show();
                return;
            }
        }
        turns++;
        if(turns==9){
            declaration();
            gameContinuation();
        }
    }
    private boolean error(int resID){
        if(resID==R.id.array_11&&(hasImage[0][0]==1||hasImage[0][0]==2))
            return false;
        else if(resID==R.id.array_12&&(hasImage[0][1]==1||hasImage[0][1]==2))
            return false;
        else if(resID==R.id.array_13&&(hasImage[0][2]==1||hasImage[0][2]==2))
            return false;
        else if(resID==R.id.array_21&&(hasImage[1][0]==1||hasImage[1][0]==2))
            return false;
        else if(resID==R.id.array_22&&(hasImage[1][1]==1||hasImage[1][1]==2))
            return false;
        else if(resID==R.id.array_23&&(hasImage[1][2]==1||hasImage[1][2]==2))
            return false;
        else if(resID==R.id.array_31&&(hasImage[2][0]==1||hasImage[2][0]==2))
            return false;
        else if(resID==R.id.array_32&&(hasImage[2][1]==1||hasImage[2][1]==2))
            return false;
        else if(resID==R.id.array_33&&(hasImage[2][2]==1||hasImage[2][2]==2))
            return false;
        else
            return true;
    }
    private void matchIDPlayer1(int resID){
        switch (resID){
            case R.id.multiplayer_array_11:hasImage[0][0]=1;
                break;
            case R.id.multiplayer_array_12:hasImage[0][1]=1;
                break;
            case R.id.multiplayer_array_13:hasImage[0][2]=1;
                break;
            case R.id.multiplayer_array_21:hasImage[1][0]=1;
                break;
            case R.id.multiplayer_array_22:hasImage[1][1]=1;
                break;
            case R.id.multiplayer_array_23:hasImage[1][2]=1;
                break;
            case R.id.multiplayer_array_31:hasImage[2][0]=1;
                break;
            case R.id.multiplayer_array_32:hasImage[2][1]=1;
                break;
            case R.id.multiplayer_array_33:hasImage[2][2]=1;
                break;
            default:break;
        }
    }
    private void gameContinuation(){
        builder.setMessage("Do you want to play again?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gameReset();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
    private void matchIDPlayer2(int resID){
        switch (resID){
            case R.id.multiplayer_array_11:hasImage[0][0]=2;
                break;
            case R.id.multiplayer_array_12:hasImage[0][1]=2;
                break;
            case R.id.multiplayer_array_13:hasImage[0][2]=2;
                break;
            case R.id.multiplayer_array_21:hasImage[1][0]=2;
                break;
            case R.id.multiplayer_array_22:hasImage[1][1]=2;
                break;
            case R.id.multiplayer_array_23:hasImage[1][2]=2;
                break;
            case R.id.multiplayer_array_31:hasImage[2][0]=2;
                break;
            case R.id.multiplayer_array_32:hasImage[2][1]=2;
                break;
            case R.id.multiplayer_array_33:hasImage[2][2]=2;
                break;
            default:break;
        }
    }
    private void gameReset(){
        Intent newIntent=new Intent(MultiplayerGameActivity.this,PlayerChosingSymbolActivity.class);
        startActivity(newIntent);
        finish();
    }
    private void declaration(){
        if(winner==1)
            Toast.makeText(MultiplayerGameActivity.this,"Player 1 won",Toast.LENGTH_LONG).show();
        else if(winner==2)
            Toast.makeText(MultiplayerGameActivity.this,"Player 2 won",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MultiplayerGameActivity.this,"Its a tie",Toast.LENGTH_LONG).show();
    }
    public boolean winnerChosing(){
        int i;
        for(i=0;i<3;i++) {
            if(hasImage[i][0] != -1 && hasImage[i][1] != -1 && hasImage[i][2]!= -1){
                if (hasImage[i][0] == hasImage[i][1] && hasImage[i][1] == hasImage[i][2]) {
                    winner = hasImage[i][0];
                    return(true);

                }
            }
        }
        for(i=0;i<3;i++) {
            if(hasImage[0][i] != -1 && hasImage[1][i] !=-1 && hasImage[2][i] !=-1){
                if (hasImage[0][i] == hasImage[1][i] && hasImage[1][i] == hasImage[2][i]) {
                    winner = hasImage[0][i];
                    return(true);
                }
            }
        }
        if(hasImage[0][0]!=-1 && hasImage[1][1]!=-1 && hasImage[2][2]!=-1) {
            if (hasImage[0][0] == hasImage[1][1] && hasImage[1][1] == hasImage[2][2]) {
                winner = hasImage[0][0];
                return(true);
            }
        }
        if(hasImage[0][2]!=-1 && hasImage[1][1]!=-1 && hasImage[2][0]!=-1) {
            if (hasImage[1][1] == hasImage[2][0] && hasImage[1][1] == hasImage[0][2]) {
                winner = hasImage[1][1];
                return(true);
            }
        }
        return false;
    }
}