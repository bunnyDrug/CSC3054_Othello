package com.CSC.othello3054.game.Game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;

/**
 * Created by don on 05/03/2015.
 *
 * This is the game system. It handles all the events and processing.
 */

public class OthelloSystem extends ActionBarActivity{

    // new grid view
    private GridView gridView;
    private GameBoard gameBoard;
    private int blackDisk = R.drawable.black_disk;
    private int whiteDisk = R.drawable.white_disk;

    // player variables
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayerTurn;

    private boolean isTimedGame;

    // taken from GameActivity
    // need this to access the xml layout for activity_game.xml
    private Activity activity;

    private Player loser;
    private Player winner;

    // database
    private DBHelper database = MainMenu.getDatabase();
    private boolean scoresAdded = false;

    /**
     * System constructor
     * @param activity the activity from the GameActivity class.
     */
    public OthelloSystem (Activity activity, boolean isTimedGame) {
        this.activity = activity;
        this.isTimedGame = isTimedGame;
    }

    public void startGame(String whitePlayerName, String blackPlayerName) {

        // player related methods
        createPlayers(whitePlayerName, blackPlayerName);
        printNamesToScreen();
        setFirstTurn(blackPlayer);

        // board setup
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
     * @param whitePlayerName the name for the white player
     * @param blackPlayerName the name for the black player
     */
    private void createPlayers(String whitePlayerName, String blackPlayerName) {
        createWhitePlayer(whitePlayerName);
        createBlackPlayer(blackPlayerName);
    }

    /**
     * Sets the first turn to the black player.
     * @param player
     */
    private void setFirstTurn(Player player) {
        currentPlayerTurn = player;
    }

    /**
     * Sets the relevant text views to display the player's chosen names.
     */
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
                (blackPlayerName, textViewBlackPlayerName,
                        0, textViewBlackPlayerScore, textViewBlackPlayerTimer);
    }


    private void createWhitePlayer(String whitePlayerName) {
        TextView textViewWhitePlayerName =
                (TextView) activity.findViewById(R.id.white_player_name);
        TextView textViewWhitePlayerScore =
                (TextView) activity.findViewById(R.id.white_player_score);
        TextView textViewWhitePlayerTimer =
                (TextView) activity.findViewById(R.id.white_player_timer);

        whitePlayer = new Player
                (whitePlayerName, textViewWhitePlayerName,
                        0, textViewWhitePlayerScore, textViewWhitePlayerTimer);
    }

    /**
     * Creates the gameBoard object that stores the current game pieces.
     */
    private void createGameBoard() {
        gameBoard = new GameBoard();
    }

    /**
     * Creates a new grid view (8x8) and displays it on the screen.
     * The grid features the starting positions of the game ready to go.
     * <br>
     * Pieces are displayed via images which are decided by the array parameter
     * @param boardLayout a populated board array. The array should hold the
     *                    current positions of all the counters on the board.
     */
    private void updateBoard(int[] boardLayout) {

        // find the gridview and assign it to a gridView Object
        gridView = (GridView) this.activity.findViewById(R.id.gridView1);

        // new class
        gridView.setAdapter(new ImageAdapter(activity, boardLayout));
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

                if (gameOver()) {

                    new AlertDialog.Builder(activity)
                            .setTitle(winner.getName() + " is the winner!")
                            .setMessage("Congratulations " + winner.getName() +
                                    ". Your final score was: "
                                    + winner.getScore())

                            .setPositiveButton("Awesome!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    if (!scoresAdded) {
                                        // add player scores into the database.
                                        database.add(winner.getName(), winner.getScore(), database.getWritableDatabase());
                                        database.add(loser.getName(), loser.getScore(), database.getWritableDatabase());
                                        scoresAdded = true;
                                    }

                                }
                            })
                            .setIcon(android.R.drawable.star_big_on)
                            .show();
                } else {
                    turn(position);
                }
            }
        });
    }

    private void turn(int tileTapped){

        if (currentPlayerTurn == blackPlayer) {
            if (Rules.canPlayerMakeAMove(gameBoard, blackDisk)) {
                if (validMovePossible(tileTapped, blackDisk)) {
                    gameBoard.placePiece(tileTapped, blackDisk);
                    currentPlayerTurn = whitePlayer;

                    if (isTimedGame) {
                        whitePlayer.startTimer();
                        blackPlayer.pauseTimer();
                    }
                }
            } else {
                new AlertDialog.Builder(activity)
                        .setTitle(blackPlayer.getName() + " Cannot Move")
                        .setMessage(blackPlayer.getName() + " has no moves. You turn is forfeit. Please pass the device to " + whitePlayer.getName())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .show();
                currentPlayerTurn = whitePlayer;

                if (isTimedGame) {
                    whitePlayer.startTimer();
                    blackPlayer.pauseTimer();
                }
            }
        }
        else if (currentPlayerTurn == whitePlayer) {

            if (Rules.canPlayerMakeAMove(gameBoard, whiteDisk)) {

                if (validMovePossible(tileTapped, whiteDisk)) {
                    gameBoard.placePiece(tileTapped, whiteDisk);
                    currentPlayerTurn = blackPlayer;

                    if (isTimedGame) {
                        whitePlayer.pauseTimer();
                        blackPlayer.startTimer();
                    }
                }
            }
            else {
                new AlertDialog.Builder(activity)
                        .setTitle(whitePlayer.getName() + " Cannot Move")
                        .setMessage(whitePlayer.getName() + " has no moves. You turn is forfeit. Please pass the device to " + blackPlayer.getName())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .show();
                currentPlayerTurn = blackPlayer;

                if (isTimedGame) {
                    whitePlayer.pauseTimer();
                    blackPlayer.startTimer();
                }
            }
        }
        updateBoard(gameBoard.getBoard());
        blackPlayer.setScore(gameBoard.countPieces(blackDisk));
        whitePlayer.setScore(gameBoard.countPieces(whiteDisk));
        blackPlayer.printScore();
        whitePlayer.printScore();
    }

    /**
     * Returns true or false if the game is over or not
     * This could be due to a player running out of time or that there are no
     * more moves possible to make on the gameBoard.
     * @return boolean
     *
     */
    private boolean gameOver() {

        boolean gameOver = false;

        if (whitePlayer.hasRunOutOfTime()) {
            gameOver = true;
            loser = whitePlayer;
            winner = blackPlayer;
        }
        if (blackPlayer.hasRunOutOfTime()) {
            gameOver = true;
            loser = blackPlayer;
            winner = whitePlayer;
        }

        if (!Rules.canPlayerMakeAMove(gameBoard, whiteDisk) &&
                !Rules.canPlayerMakeAMove(gameBoard, blackDisk)) {
            gameOver = true;
            if (whitePlayer.getScore() > blackPlayer.getScore()) {
                loser = blackPlayer;
                winner = whitePlayer;
            } else {
                loser = whitePlayer;
                winner = blackPlayer;
            }

        }

        return gameOver;
    }

    /**
     * checks if the position tapped is valid and allows the game to progress
     * without error
     * in its current state it only checks for an occupied tile
     * @param positionTapped an integer on the grid that the user wishes to
     *                       interact with
     * @return true if a user is able to place a token in the tapped position
     * (adhering to the rules of the game)
     */
    private boolean validMovePossible(int positionTapped, int diskColour){

        boolean validMove = false;

        if (Rules.validDiskPlacement(positionTapped, gameBoard, diskColour)) {
            validMove = true;
        } else {
            displayToast("Invalid move, please try again");
        }


        return validMove;
    }

    private void displayToast(String messageToDisplay) {
        Toast.makeText(activity.getBaseContext(), messageToDisplay,
                Toast.LENGTH_SHORT).show();
    }


}

