package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    public static int mComputerSymbol;
    public static int mUserSymbol;
    Random r=new Random();
    AlertDialog.Builder builder;
    int hasImage[][] = new int[3][3];
    ImageView[][] ar = new ImageView[3][3];
    int winner=0,turns=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ar[0][0] = findViewById(R.id.array_11);
        ar[0][1] = findViewById(R.id.array_12);
        ar[0][2] = findViewById(R.id.array_13);
        ar[1][0] = findViewById(R.id.array_21);
        ar[1][1] = findViewById(R.id.array_22);
        ar[1][2] = findViewById(R.id.array_23);
        ar[2][0] = findViewById(R.id.array_31);
        ar[2][1] = findViewById(R.id.array_32);
        ar[2][2] = findViewById(R.id.array_33);
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
    public void onClick (View v){
        if(error(v.getId())) {
            matchID(v.getId());
            ImageView i = findViewById(v.getId());
            i.setImageResource(mUserSymbol);
            turns++;
        }
        else {
            Toast.makeText(GameActivity.this, "Please select another box", Toast.LENGTH_LONG).show();
            return;
        }
        if(turns==9){
            winnerChosing();
            declaration();
            gameContinuation();
        }
        else {
            if (winnerChosing()) {
                declaration();
                gameContinuation();
            }
            computerTurn();
            if (winnerChosing()) {
                declaration();
                gameContinuation();
            }
            turns++;
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
    private void gameReset(){
        Intent newIntent=new Intent(GameActivity.this,ChosingSymbolActivity.class);
        startActivity(newIntent);
        releaseMemory();
        finish();
    }
    private void releaseMemory(){
        hasImage=null;
        ar=null;
        r=null;
    }
    private void matchID(int resID){
        switch (resID){
            case R.id.array_11:hasImage[0][0]=1;
            break;
            case R.id.array_12:hasImage[0][1]=1;
                break;
            case R.id.array_13:hasImage[0][2]=1;
                break;
            case R.id.array_21:hasImage[1][0]=1;
                break;
            case R.id.array_22:hasImage[1][1]=1;
                break;
            case R.id.array_23:hasImage[1][2]=1;
                break;
            case R.id.array_31:hasImage[2][0]=1;
                break;
            case R.id.array_32:hasImage[2][1]=1;
                break;
            case R.id.array_33:hasImage[2][2]=1;
                break;
            default:break;
        }
    }
    public void declaration(){
        if(winner==1)
            Toast.makeText(GameActivity.this,"Congratulations you won",Toast.LENGTH_LONG).show();
        else if(winner==2)
            Toast.makeText(GameActivity.this,"You lose...better luck next time",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(GameActivity.this,"Its a tie",Toast.LENGTH_LONG).show();
    }
    public void computerTurn(){
        int row=r.nextInt(3);
        int col=r.nextInt(3);
        if(hasImage[row][col]==-1) {
            ar[row][col].setImageResource(mComputerSymbol);
            hasImage[row][col] = 2;
        }
        else
            computerTurn();
    }
    public boolean winnerChosing(){
        int i;
        for(i=0;i<3;i++) {
            if(hasImage[i][0] == -1 || hasImage[i][1] ==-1 || hasImage[i][2]==-1)
                continue;
            else {
                if (hasImage[i][0] == hasImage[i][1] && hasImage[i][1] == hasImage[i][2]) {
                    winner = hasImage[i][0];
                    return true;

                }
            }
        }
        for(i=0;i<3;i++) {
            if(hasImage[0][i] == -1 || hasImage[1][i] ==-1 || hasImage[2][i]==-1)
                continue;
            else {
                if (hasImage[0][i] == hasImage[1][i] && hasImage[1][i] == hasImage[2][i]) {
                    winner = hasImage[0][i];
                    return true;
                }
            }
        }
        if(hasImage[0][0]!=-1 && hasImage[1][1]!=-1 && hasImage[2][2]!=-1) {
            if (hasImage[0][0] == hasImage[1][1] && hasImage[1][1] == hasImage[2][2]) {
                winner = hasImage[0][0];
                return true;
            }
        }
        if(hasImage[0][2]!=-1 && hasImage[1][1]!=-1 && hasImage[2][0]!=-1) {
            if (hasImage[1][1] == hasImage[2][0] && hasImage[1][1] == hasImage[0][2]) {
                winner = hasImage[1][1];
                return true;
            }
        }
        return false;
    }
}