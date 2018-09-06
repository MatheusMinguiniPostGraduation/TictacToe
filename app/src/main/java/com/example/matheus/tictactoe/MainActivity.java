package com.example.matheus.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Integer PLAYER1 = 2;
    private static Integer PLAYER2 = 9;

    private static Integer PONCTUATION_WINNER_PLAYER1 = 6;
    private static Integer PONCTUATION_WINNER_PLAYER2 = 27;


    //Matrix to validate whether there is a winner or not
    private Integer [][] currentStateMatrix = new Integer[3][3];
    private Boolean winner;
    private Button reset_button;
    private TextView messageWinner;

    int currentPlayer = PLAYER1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMatrix(currentStateMatrix);
        winner = false;

        reset_button = (Button) findViewById(R.id.restart_button);
        messageWinner = (TextView) findViewById(R.id.messageWinner);
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        String[] positionClicked = counter.getTag().toString().split("");

        Integer line = Integer.valueOf(positionClicked[1]);
        Integer column = Integer.valueOf(positionClicked[2]);

        if(currentStateMatrix[line][column] == 0){
            currentStateMatrix[line][column] = currentPlayer;
            insertRockIntoTheGame(counter);
        }

        winner = veriFyWinner();

        if(winner) {
            ImageView winnerEffect = findViewById(R.id.winnerAnimation);

            winnerEffect.setVisibility(View.VISIBLE);

            winnerEffect.animate().translationYBy(2000f).setDuration(4000);

            reset_button.setVisibility(View.VISIBLE);
            messageWinner.setVisibility(View.VISIBLE);


        }else{
            if(verifyIfAllFieldsAreTaken()){
                Toast.makeText(this, "O JOGO DEU VELHA!", Toast.LENGTH_LONG).show();
                reset_button.setVisibility(View.VISIBLE);
            }
        }

    }

    public void restart(View reset_button){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void insertRockIntoTheGame(ImageView counter) {
        //Bring the view from the top of the screen
        counter.setTranslationY(-1000f);

        if(currentPlayer == PLAYER1) {
            counter.setImageResource(R.drawable.yellow);
            currentPlayer = PLAYER2;
        }else{
            counter.setImageResource(R.drawable.red);
            currentPlayer = PLAYER1;
        }

        //Animation
        counter.animate().translationYBy(1000f).rotation(700).setDuration(700);
    }

    private Boolean veriFyWinner(){
        return checkHorizontally() || checkVertically() || checkDiagonally();
    }

    private Boolean checkHorizontally(){
        Integer counter = 0;

        for(int line = 0; line < 3; line ++){
            for(int column = 0; column < 3; column++) {
                counter += currentStateMatrix[line][column];
            }

            if (verifyPonctuation(counter)) return Boolean.TRUE;

            counter = 0;
        }

        return Boolean.FALSE;
    }

    private Boolean checkVertically(){
        Integer counter = 0;

        for(int line = 0; line < 3; line ++){
            for(int column = 0; column < 3; column++) {
                counter += currentStateMatrix[line][column];
            }

            if (verifyPonctuation(counter)) return Boolean.TRUE;

            counter = 0;
        }

        return Boolean.FALSE;
    }

    private Boolean checkDiagonally(){
        int counter = 0;

        for(int auxiliar = 0; auxiliar <3; auxiliar++){
            counter+= currentStateMatrix[auxiliar][auxiliar];
        }

        if (verifyPonctuation(counter)) return Boolean.TRUE;

        counter = 0;
        int coluna = 2;
        for(int linha = 0; linha < 3; linha++){
            counter+= currentStateMatrix[linha][coluna];
            coluna--;
        }

        if (verifyPonctuation(counter)) return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private boolean verifyPonctuation(int counter) {
       return counter == PONCTUATION_WINNER_PLAYER1 || counter == PONCTUATION_WINNER_PLAYER2;
    }

    private void populateMatrix(Integer currentStateMatrix[][]){
        for(int line = 0; line < 3; line ++){
            for(int column = 0; column < 3; column++) {
                currentStateMatrix[line][column] = 0;
            }
        }
    }

    private Boolean verifyIfAllFieldsAreTaken(){
        int counter = 0;
        for(int line = 0; line < 3; line ++){
            for(int column = 0; column < 3; column++) {
               if(currentStateMatrix[line][column] != 0){
                   counter++;
               }
            }
        }

        return (counter == 9);
    }
}
