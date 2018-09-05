package com.example.matheus.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // player1 = 0
    // player2 = 1

    int [] currentState = {2,2,2,2,2,2,2,2,2};

    int currentPlayer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){

        Log.d("info", "teste");
        ImageView counter = (ImageView) view;




        Integer positionClicked = Integer.valueOf(counter.getTag().toString());

        if(currentState[positionClicked] == 2){

            currentState[positionClicked] = currentPlayer;
            insertRockIntoTheGame(counter);

        }


        /*ImageView winnerEffect = findViewById(R.id.winnerAnimation);

        //winnerEffect.setTranslationY(-2000);

        winnerEffect.setVisibility(View.VISIBLE);

        winnerEffect.animate().translationYBy(2000f).setDuration(4000);*/

    }

    private void insertRockIntoTheGame(ImageView counter) {
        //Bring the view from the top of the screen
        counter.setTranslationY(-1000f);

        if(currentPlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            currentPlayer = 1;
        }else{
            counter.setImageResource(R.drawable.red);
            currentPlayer = 0;
        }

        //Animation
        counter.animate().translationYBy(1000f).rotation(700).setDuration(700);
    }
}
