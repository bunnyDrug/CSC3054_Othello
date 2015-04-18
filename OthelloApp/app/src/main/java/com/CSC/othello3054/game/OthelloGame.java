package com.CSC.othello3054.game;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.CSC.othello3054.game.Game.OthelloSystem;

public class OthelloGame extends ActionBarActivity {

    OthelloSystem othelloSystem;
    String topPlayer;
    String bottomPlayer;
    int timerValueSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topPlayer = extras.getString("topPlayer");
            bottomPlayer = extras.getString("bottomPlayer");
            timerValueSelected = extras.getInt("spinnerSelection");
        }

        new AlertDialog.Builder(this).setTitle("Game start")
                         .setMessage("The game will begin as soon as the " +
                                 "bottom player makes their first move. \nPlayer turns are indicated by the blue coloring on your name")
                         .setIcon(android.R.drawable.ic_lock_idle_alarm)
                         .show();

        // creates the system that runs the game.
        othelloSystem = new OthelloSystem(this, timerValueSelected);
        othelloSystem.startGame(topPlayer, bottomPlayer);
    }
}