package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChosingSymbolActivity extends AppCompatActivity {

    ImageView mSymbol1,mSymbol2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosing_symbol);
        mSymbol1=findViewById(R.id.symbol_O);
        mSymbol1.setImageResource(R.drawable.tictactoe_cross);
        mSymbol2=findViewById(R.id.symbol_X);
        mSymbol2.setImageResource(R.drawable.tictactoe_circle);
        mSymbol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.mComputerSymbol=R.drawable.tictactoe_circle;
                GameActivity.mUserSymbol=R.drawable.tictactoe_cross;
                Intent newIntent=new Intent(ChosingSymbolActivity.this,GameActivity.class);
                startActivity(newIntent);
                releaseMemory();
                finish();
            }
        });
        mSymbol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.mUserSymbol=R.drawable.tictactoe_circle;
                GameActivity.mComputerSymbol=R.drawable.tictactoe_cross;
                Intent newIntent=new Intent(ChosingSymbolActivity.this,GameActivity.class);
                startActivity(newIntent);
                releaseMemory();
                finish();
            }
        });
    }
    @SuppressLint("NewApi")
    private void releaseMemory(){
        mSymbol1.releasePointerCapture();
        mSymbol1=null;
        mSymbol2=null;
    }
}