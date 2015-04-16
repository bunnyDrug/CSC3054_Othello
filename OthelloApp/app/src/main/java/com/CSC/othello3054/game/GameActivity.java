package com.CSC.othello3054.game;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.CSC.othello3054.game.Game.OthelloSystem;

public class GameActivity extends ActionBarActivity {

    OthelloSystem othelloSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        // creates the game system that manages the game.
        // TODO: remove hard coded values

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

        othelloSystem = new OthelloSystem(this, false);
        othelloSystem.startGame(topPlayer, bottomPlayer);
    }
}