package com.CSC.othello3054.game;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.CSC.othello3054.game.Game.OthelloSystem;

/**
 * Created by don on 11/03/2015.
 * The Othello game screen - this class gets the values passed in from the main
 * menu, passes them into the system that controls the game and starts the
 * game system. See OthelloSystem for the system class.
 */
public class OthelloGame extends ActionBarActivity {

    // Class variables
    OthelloSystem othelloSystem;
    String topPlayer;
    String bottomPlayer;
    int timerValueSelected;
    boolean colourblindMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        // SEE JAVADOC FOR DETAILS. (F1 in Android Studio)

        getExtras();

        showIntroductionMessage();

        startGame();
    }

    /**
     * Creates and starts the system that controls and runs the game.
     */
    private void startGame() {
        othelloSystem = new OthelloSystem(this, timerValueSelected, colourblindMode);
        othelloSystem.startGame(topPlayer, bottomPlayer);
    }

    /**
     * Displays a initial introduction message on screen.
     */
    private void showIntroductionMessage() {
        new AlertDialog.Builder(this).setTitle("Game start")
                         .setMessage("The game will begin as soon as the " +
                                 "bottom player makes their first move. " +
                                 "\nPlayer turns are indicated by the blue " +
                                 "coloring on your name")
                         .setIcon(android.R.drawable.ic_lock_idle_alarm)
                         .setPositiveButton("Got it",null)
                         .show();
    }

    /**
     * Retrieves the extras provided by the MainMenu activity
     * <b>(player names and game timer)</b>
     * to class variables. These are ready to be given to an instance of the
     * OthelloSystem class.
     */
    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            topPlayer = extras.getString("topPlayer");
            bottomPlayer = extras.getString("bottomPlayer");
            timerValueSelected = extras.getInt("spinnerSelection");
            colourblindMode = extras.getBoolean("cbMode");
        }
    }
}