package com.CSC.othello3054.game;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.CSC.othello3054.game.Game.OthelloSystem;

public class OthelloGame extends ActionBarActivity {

    OthelloSystem othelloSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);



        String topPlayer = "";
        String bottomPlayer = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topPlayer = extras.getString("topPlayer");
            bottomPlayer = extras.getString("bottomPlayer");
        }

        new AlertDialog.Builder(this).setTitle("Game start")
                         .setMessage("The game will begin as soon as the " +
                                 "bottom player makes their move")
                         .setIcon(android.R.drawable.ic_lock_idle_alarm)
                         .show();

        // creates the system that runs the game.
        othelloSystem = new OthelloSystem(this, false);
        othelloSystem.startGame(topPlayer, bottomPlayer);
    }
}