package com.example.don.othello.Game;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by don on 05/03/2015.
 * Player class
 * each player holds its own score and time remaining (until game over)
 */

public class Player {

    private boolean validMovesAvailable = true;

    // TODO: read this in from a settings page - remove hard coded value
    private long playerTimeLimit = 30000;

    private String name;
    private TextView textViewName;

    int score;
    private TextView textViewScore;


    boolean timedGame;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;

    public Player(String name, TextView textViewName,
                  int score, TextView textViewScore,
                  TextView textViewTimer) {

        this.name = name;
        this.textViewName = textViewName;

        this.score = score;
        this.textViewScore = textViewScore;

        this.textViewTimer = textViewTimer;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(playerTimeLimit, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("Time left: " + millisUntilFinished / 1000 + " seconds");
                playerTimeLimit = millisUntilFinished;
            }

            public void onFinish() {
                textViewTimer.setText("Game over");
            }
        }.start();
    }

    public void pauseTimer() {
        if (countDownTimer == null) {
            Log.d("Player class","no timer to pause");
        } else{
            countDownTimer.cancel();
        }
    }

    public void printName() {
        textViewName.setText(name);
    }
}
