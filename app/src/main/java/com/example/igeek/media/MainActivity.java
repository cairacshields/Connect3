package com.example.igeek.media;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int ActivePlayer = 0;

    boolean gameIsActive = true;
    //Since 0=yellow and 1=red, we can make 2=unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropIn(View view) {

        ImageView chip = (ImageView) view;

        int tappedCounter = Integer.parseInt(chip.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = ActivePlayer;

            chip.setTranslationY(-1000f);

            if (ActivePlayer == 0) {

                chip.setImageResource(R.drawable.yellowdot);
                ActivePlayer = 1;
            } else {
                chip.setImageResource(R.drawable.redchip);
                ActivePlayer = 0;
            }
            chip.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                //Here we are looping through our gameState array and checking to see if any of our positions are the same and therefore
                //someone has won the game! It seems complicated but really all we are doing is checking our possible winning positions and
                //comparing those indexes from our gamestate Array to see if our active player has three matching indexes... Maybe i'm confusing you more!
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    gameIsActive = false;
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    TextView tv = (TextView) layout.findViewById(R.id.winnerMessage);
                    if (gameState[winningPosition[0]] == 0) {
                        tv.setText("Player 1 has won!");
                    }
                    if (gameState[winningPosition[0]] == 1) {
                        tv.setText("Player 2 has won!");
                    }
                    layout.setVisibility(View.VISIBLE);
                }else {
                    boolean gameIsOver =true;

                    for(int counterState: gameState){
                        if(counterState ==2) gameIsOver =false;

                    }
                    if(gameIsOver){
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        TextView tv = (TextView)layout.findViewById(R.id.winnerMessage);
                        tv.setText("Its a draw!");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
                }
            }
        }

    public void playAgain(View view){

        gameIsActive =true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        ActivePlayer =0;

        for(int i =0; i<gameState.length; i++){
            gameState[i]=2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        //Remember that the method .getChildCount will return to you the number of items in a parent element
        for(int i =0; i<gridLayout.getChildCount(); i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }
    }


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_blank);

        }


}
