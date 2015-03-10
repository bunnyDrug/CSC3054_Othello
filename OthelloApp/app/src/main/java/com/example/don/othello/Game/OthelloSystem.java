package com.example.don.othello.Game;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.don.othello.ImageAdapter;
import com.example.don.othello.R;

/**
 * Created by don on 05/03/2015.
 *
 * This is the game system. It handles all the events and processing.
 */

public class OthelloSystem extends ActionBarActivity{

    // new grid view
    private GridView gridView;
    private GameBoard gameBoard;

    // player variables
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayerTurn;

    private boolean isTimedGame;

    // taken from MainActivity
    // need this to access the xml layout for activity_main.xml
    private Activity activity;

    /**
     * System constructor
     * @param activity the activity from the MainActivity class.
     */
    public OthelloSystem (Activity activity, boolean isTimedGame) {
        this.activity = activity;
        this.isTimedGame = isTimedGame;
    }

    public void startGame(String whitePlayerName, String blackPlayerName) {
        createPlayers(whitePlayerName, blackPlayerName);
        printNamesToScreen();
        createGameBoard();
        updateBoard(gameBoard.getBoard());
        setBoardColour();
        setOnClickListener();
    }


    /**
     * Creates two player objects required to play the game whitePlayer and
     * blackPlayer. Each object is given the textViews from the form so it can
     * handle its own scores and time limit.
     * Prints the player names to the display
     * The logic for these values is done in the System class.
     * @param timer
     * @param whitePlayerName
     * @param blackPlayerName
     */
    private void createPlayers(String whitePlayerName, String blackPlayerName) {
        createWhitePlayer(whitePlayerName);
        createBlackPlayer(blackPlayerName);
        currentPlayerTurn = blackPlayer;
    }

    private void printNamesToScreen() {
        whitePlayer.printName();
        blackPlayer.printName();
    }

    private void createBlackPlayer(String blackPlayerName) {
        TextView textViewBlackPlayerName =
                (TextView) activity.findViewById(R.id.black_player_name);
        TextView textViewBlackPlayerScore =
                (TextView) activity.findViewById(R.id.black_player_score);
        TextView textViewBlackPlayerTimer =
                (TextView) activity.findViewById(R.id.black_player_timer);

        blackPlayer = new Player
                (blackPlayerName, textViewBlackPlayerName, 0, textViewBlackPlayerScore, textViewBlackPlayerTimer);
    }

    private void createWhitePlayer(String whitePlayerName) {
        TextView textViewWhitePlayerName =
                (TextView) activity.findViewById(R.id.white_player_name);
        TextView textViewWhitePlayerScore =
                (TextView) activity.findViewById(R.id.white_player_score);
        TextView textViewWhitePlayerTimer =
                (TextView) activity.findViewById(R.id.white_player_timer);

        whitePlayer = new Player
                (whitePlayerName, textViewWhitePlayerName, 0, textViewWhitePlayerScore, textViewWhitePlayerTimer);
    }

    /**
     * Creates the gameBoard object that stores the current game pieces
     */
    private void createGameBoard() {
        gameBoard = new GameBoard();
    }

    /**
     * This is the click listener for the Grid View - it registers a tap from
     * the user and takes it position to perform various actions.
     *
     * As of today 9th/Mar/2015 @ 1:32am it will kick off the turn method which
     * sparks off a bunch of other checks and validations. This may or may not
     * be the best way to go about it in the future but at this stage it's a
     * work in progress.
     */
    void setOnClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick
                    (AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(activity.getBaseContext(), "" + position, Toast.LENGTH_SHORT).show();

                turn(position);
            }
        });
    }

    /**
     * this needs to change, maybe keep a themes class where final values
     * are stored
     * Not sure how themes work at this stage.
     * TODO: implement themes
     */
    private void setBoardColour() {
        // colour the grind with the 100 shade orange
        gridView.setBackgroundColor(Color.parseColor("#FFE0B2"));
    }

    /**
     * Creates a new grid view (8x8) and displays it on the screen.
     * The grid features the starting positions of the game ready to go.
     * <br>
     * Pieces are displayed via images which are decided by the array parameter
     * @param boardLayout
     */
    private void updateBoard(int[] boardLayout) {

        // find the gridview and assign it to a gridView Object
        gridView = (GridView) this.activity.findViewById(R.id.gridView1);

        // new class
        gridView.setAdapter(new ImageAdapter(activity, boardLayout));
    }

    // THIS SHOULD NOT STAY LIKE THIS - THE TURN METHOD IS ONLY FOR DECIDING
    // THE TURN OF A USER - AT ITS CURRENT SITS IT IS DOING TOO MUCH.
    // TODO: FIX THIS MESS AND BREAK UP INTO SMALLER METHODS
    private void turn(int tileTapped){

        if (validMovePossible(tileTapped)) {
            if (currentPlayerTurn == blackPlayer) {
                gameBoard.placePiece(tileTapped, R.drawable.black_disk);
                currentPlayerTurn = whitePlayer;
                if (isTimedGame) {
                    whitePlayer.startTimer();
                    blackPlayer.pauseTimer();
                }
            } else {
                gameBoard.placePiece(tileTapped, R.drawable.white_disk);
                currentPlayerTurn = blackPlayer;
                if (isTimedGame) {
                    whitePlayer.pauseTimer();
                    blackPlayer.startTimer();
                }
            }
            updateBoard(gameBoard.getBoard());
        }
        else {
            displayToast("Pick another tile, this one is occupied");
        }
    }

    /**
     * Discovers all the valid moves that a player may take in his or her
     * current turn. A players boardPieces is only allowed to be placed in any
     * of the legal spaces for that turn.
     *
     * A possible overlay of movies that are valid would be nice
     * @return boolean
     */
    private boolean calculateCurrentValidMoves() {

        return true;
    }

    public void displayValidMoves() {

    }

    /**
     * Returns true or false if the game is over or not
     * This could be due to a player running out of time or that there are no
     * more moves possible to make on the gameBoard.
     * @return boolean
     *
     */
    //TODO: split this up into smaller methods.
    //private boolean gameOver() {
    //
    //    // if player has run out of time
    //    // if whitePlayer.timer == 0 or something
    //
    //    // if both players have NO valid moves.
    //    if (!whitePlayer.hasValidMovesAvailable()
    //            && !blackPlayer.hasValidMovesAvailable()){
    //        return true;
    //    }
    //    return false;
    //}

    public GridView getGridView() {
        return gridView;
    }

    /**
     * checks if the position tapped is valid and allows the game to progress
     * without error
     *
     * TODO: define rules for validity
     * in its current state it only checks for an occupied tile
     * @param tileTapped
     * @return
     */
    private boolean validMovePossible(int tileTapped) {
        //return calculateCurrentValidMoves();

        //TODO: split these up into either smaller methods or a static class
        //***************************************************
        // Othello RULES


        // if there is a black or white disk in the tile return false
        return gameBoard.getPiece(tileTapped) == R.drawable.placement_counter;
    }

    private void displayToast(String message) {
        Toast.makeText(activity.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}

