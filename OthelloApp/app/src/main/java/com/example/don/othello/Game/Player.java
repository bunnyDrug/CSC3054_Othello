package com.example.don.othello.Game;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by don on 05/03/2015.
 * Player class
 * each player holds its own score and time remaining (until game over)
 */

class Player {

    // 1 second
    private final long TICK_INTERVAL = 1000;

    // TODO: read this in from a settings page - remove hard coded value
    private long playerTimeLimit = 30000;


    private String name;
    private TextView textViewName;



    private int score;
    private TextView textViewScore;

    private TextView textViewTimer;
    private CountDownTimer countDownTimer;

    /**
     * A class that represents the players in the game. This player class is
     * responsible for holding its own timer and score. Any updates to these
     * stored values should be printed to the screen using the print methods
     * @param name the user input name
     * @param textViewName the text area on the screen where the name should
     *                     appear
     * @param score a users score - made up from the number of tiles on the
     *              board that represent their colour
     * @param textViewScore the text area that displays the score value to a
     *                      player
     * @param textViewTimer the text area that displays the time remaining for
     *                      a player.
     */
    public Player(String name, TextView textViewName,
                  int score, TextView textViewScore,
                  TextView textViewTimer) {

        this.name = name;
        this.textViewName = textViewName;

        this.score = score;
        this.textViewScore = textViewScore;

        this.textViewTimer = textViewTimer;
    }

    /**
     * Set the number of minutes each player has before a game is over
     * @param timer
     */
    public void setGameTimer(long timer) {
        playerTimeLimit = timer;
    }

    /**
     * Creates a new countdown timer for the player.
     * Each tick updates the text on the
     * display - the text view passed in via the constructor.
     *
     * After each tick the time remaining is stored in a variable so it may be
     * resumed after it is paused.
     */
    public void startTimer() {
        if (playerTimeLimit > 0) {
            countDownTimer = new CountDownTimer(playerTimeLimit, TICK_INTERVAL) {
                public void onTick(long millisUntilFinished) {
                    textViewTimer.setText("Time left: " + millisUntilFinished / 1000 + " seconds");
                    playerTimeLimit = millisUntilFinished;
                }

                public void onFinish() {
                    textViewTimer.setText("Game over");
                    playerTimeLimit = 0;
                }
            }.start();
        }
    }

    /**
     * Stops the player timer
     */
    public void pauseTimer() {
        if (countDownTimer == null) {
            Log.d("Player class","no timer to pause");
        } else{
            countDownTimer.cancel();
        }
    }

    /**
     * Sets the text on the screen to show the player name.
     */
    public void printName() {
        textViewName.setText(name);
    }

    public void setTurnColour() {
        //accent colour
        //TODO: refactor this
        textViewName.setTextColor(Color.parseColor("#2196F3"));
    }

    public void setNotTurnColour() {
        //TODO: refactor this
        // lighter colour
        textViewName.setTextColor(Color.parseColor("#FFE0B2"));
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void printScore() {
        textViewScore.setText("Current Score: " + score);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean hasRunOutOfTime() {
        return playerTimeLimit == 0;
    }
}
