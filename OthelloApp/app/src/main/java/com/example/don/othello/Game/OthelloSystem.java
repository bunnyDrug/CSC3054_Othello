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
 */

public class OthelloSystem extends ActionBarActivity{

    // new grid view
    GridView gridView;

    // player variables
    Player whitePlayer;
    Player blackPlayer;
    Player currentPlayerTurn;

    // taken from MainActivity
    // need this to access the xml layout for activity_main.xml
    Activity activity;

    // The game board class should actually be the one taking care of board
    // positions - The system should have nothing to do with the board array
    // apart from passing in new updates
    // TODO: extract this to the GameBoard class and set getters for it
    GameBoard gameBoard = new GameBoard();
    int[] boardPieces;

    /**
     * Creates 2 player objects
     * @param player1 A name for player one (Can obtained in from a Text Field)
     * @param player2 A name for player two (Can obtained in from a Text Field)
     * @param timer Is this game a timed one? Set via the app settings.
     */
    public OthelloSystem (String player1,
                          String player2,
                          Boolean timer,
                          Activity activity) {

        // need to pass this in so this class knows what is going on
        this.activity = activity;

        createPlayers(player1, player2, timer);
        currentPlayerTurn = blackPlayer;

        // Creates the gameBoard object that stores the current game pieces
        boardPieces = gameBoard.initStartingPieces();

        // Creates the grid that displays the gameBoard
        updateBoard(boardPieces);

        // obvious?
        setBoardColour();

        setOnClickListener();

        // TODO: look at removing this as it may not be needed.
        //might this not be needed since we are working with a tap event?
        //gameLoop();
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
    public void setOnClickListener() {
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
     *
     * @param initialPieceLayout
     */
    private void updateBoard(int[] initialPieceLayout) {

        // find the gridview and assign it to a gridView Object
        gridView = (GridView) this.activity.findViewById(R.id.gridView1);

        // new class
        gridView.setAdapter(new ImageAdapter(activity, initialPieceLayout));
    }

    /**
     * Main game loop - all runs until game over conditions allow for
     * termination
     */
    private void gameLoop() {
        while (!gameOver()) {
            System.out.println("testing print");
        }
    }

    /**
     * creates the two player objects required to play the game.
     * Players have a default colour and check the timer boolean to enable a
     * timed game or not.
     * @param player1 String
     * @param player2 String
     * @param timer boolean
     */
    private void createPlayers(String player1, String player2, Boolean timer) {
        this.whitePlayer = new Player(player1, timer);
        this.blackPlayer = new Player(player2, timer);

        updatePlayerText(player1,player2);
    }

    /**
     * Sets the TextViews for each player to their desired names
     * TODO: have player names read in from a main menu.
     * @param p1
     * @param p2
     */
    private void updatePlayerText(String p1, String p2) {
        TextView txtPlayer1 = (TextView)
                this.activity.findViewById(R.id.textview_player1);
        TextView txtPlayer2 = (TextView)
                this.activity.findViewById(R.id.textview_player2);

        txtPlayer1.setText(p1);
        txtPlayer2.setText(p2);
    }

    // THIS SHOULD NOT STAY LIKE THIS - THE TURN METHOD IS ONLY FOR DECIDING
    // THE TURN OF A USER - AT ITS CURRENT SITS IT IS DOING TOO MUCH.
    // TODO: FIX THIS MESS AND BREAK UP INTO SMALLER METHODS
    private void turn(int positionTapped){

        if (validMovePossible(positionTapped)) {
            if (currentPlayerTurn == blackPlayer) {
                boardPieces[positionTapped] = R.drawable.black_disk;
                currentPlayerTurn = whitePlayer;
            } else {
                boardPieces[positionTapped] = R.drawable.white_disk;
                currentPlayerTurn = blackPlayer;
            }
            updateBoard(boardPieces);
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
    private boolean gameOver() {

        // if player has run out of time
        // if whitePlayer.timer == 0 or something

        // if both players have NO valid moves.
        if (!whitePlayer.hasValidMovesAvailable()
                && !blackPlayer.hasValidMovesAvailable()){
            return true;
        }
        return false;
    }

    public GridView getGridView() {
        return gridView;
    }

    /**
     * checks if the position tapped is valid and allows the game to progress
     * without error
     *
     * TODO: define rules for validity
     * in its current state it only checks for an occupied tile
     * @param positionTapped
     * @return
     */
    private boolean validMovePossible(int positionTapped) {
        //return calculateCurrentValidMoves();

        //TODO: split these up into either smaller methods or a static class
        //***************************************************
        // Othello RULES


        // if there is a black or white disk in the tile return false
        return boardPieces[positionTapped] == R.drawable.placement_counter;
    }

    private void displayToast(String message) {
        Toast.makeText(activity.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}

