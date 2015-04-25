package com.CSC.othello3054.game.Game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;

/**
 * Created by don on 05/03/2015.
 *
 * This is the game system. It handles all game events and processing.
 */

public class OthelloSystem extends ActionBarActivity{

    // static class variable for 100 Orange
    public static final String ORANGE_100 = "#FFE0B2";

    // new grid view
    private GridView gridView;
    private GameBoard gameBoard;

    // player variables
    private Player whitePlayer;
    private Player blackPlayer;
    private int blackDisk = R.drawable.black_disk;
    private int whiteDisk = R.drawable.white_disk;
    private Player currentPlayer;
    private Player waitingPlayer;

    private Player loser;
    private Player winner;

    // timer variables.
    private boolean isTimedGame;
    private int timerValue;

    // taken from OthelloGame (the visual game screen)
    // need this to access the xml layout for that activity.
    private Activity activity;

    // database
    private DBHelper database = MainMenu.getDatabase();
    private boolean scoresAdded = false;


    /**
     * System Constructor. Takes an activity and a selected timer value.
     * @param activity Activity: The activity from the game layout.
     * @param timerValueSelected int: A user selected value deciding whether a
     *                           game should have a timer or not.
     * @param colourBlindMode boolean - change disks for colourblind users.
     */

    public OthelloSystem (Activity activity, int timerValueSelected, boolean colourBlindMode) {


        // This allows for access to XML values on the layout.
        this.activity = activity;

        // if the timer is greater than 0, enabled the game timer and set the
        // timer value - we deal with converting this to milliseconds later...
        if (timerValueSelected > 0) {
            isTimedGame = true;
            timerValue = timerValueSelected;
        } else {
            // disable timer
            isTimedGame = false;
        }

        // init colourblind tokens.
        if (colourBlindMode) {
            blackDisk = R.drawable.black_disk_cb;
            whiteDisk = R.drawable.white_disk_cb;
        }
    }

    /**
     * Begin the game.<br>
     * <ul>
     *     <li>Creates both players, prints names and sets the first turn</li>
     *     <li>If a timed game - set the timer for each player</li>
     *     <li>Create/init the game board</li>
     *     <li>Display board on screen & set its colour</li>
     *     <li>Start the click listener which controls player moves per tap</li>
     * </ul>
     * @param whitePlayerName String: display name for the white player
     * @param blackPlayerName String: display name for the black player
     */
    public void startGame(String whitePlayerName, String blackPlayerName) {

        // player related methods
        createPlayers(whitePlayerName, blackPlayerName);
        printNamesToScreen();
        setFirstTurn(blackPlayer, whitePlayer);
        currentPlayer.setTurnIndicator();

        // if it's a timed game, then the timer needs set
        if (isTimedGame) {
            Long x = (long) timerValue * 10000;
            whitePlayer.setGameTimer(x);
            blackPlayer.setGameTimer(x);
        }

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
     * @param whitePlayerName String: the name for the white player
     * @param blackPlayerName String the name for the black player
     */
    private void createPlayers(String whitePlayerName, String blackPlayerName) {
        createWhitePlayer(whitePlayerName);
        createBlackPlayer(blackPlayerName);
    }

    /**
     * Sets the first turn to the player parameter (as per othello rules the
     * black player should go first and white second, this method and its
     * parameter allows for future modification).
     * @param first Player: player making the first move
     * @param second Player: player making the second move
     */
    private void setFirstTurn(Player first, Player second) {
        currentPlayer = first;
        waitingPlayer = second;
    }

    /**
     * Sets the relevant text views to display the player's chosen names.
     */
    private void printNamesToScreen() {
        whitePlayer.printName();
        blackPlayer.printName();
    }

    /**
     * Create the black player. Obtains all text fields that are relevant to
     * the black player - name, score and timer.
     * Instantiates a new instance of the player class.
     * @param blackPlayerName String: display name for the black player
     */
    private void createBlackPlayer(String blackPlayerName) {
        TextView textViewBlackPlayerName =
                (TextView) activity.findViewById(R.id.black_player_name);
        TextView textViewBlackPlayerScore =
                (TextView) activity.findViewById(R.id.black_player_score);
        TextView textViewBlackPlayerTimer =
                (TextView) activity.findViewById(R.id.black_player_timer);
        ImageView imageView
                = (ImageView) activity.findViewById(R.id.black_player_turn);

        blackPlayer = new Player
                (blackPlayerName,
                        textViewBlackPlayerName,
                        2,
                        textViewBlackPlayerScore,
                        textViewBlackPlayerTimer,
                        blackDisk,
                        imageView
                );
    }

    /**
     * Create the white player. Obtains all text fields that are relevant to
     * the black player - name, score and timer.
     * Instantiates a new instance of the player class.
     * @param whitePlayerName String: display name for the white player
     */
    private void createWhitePlayer(String whitePlayerName) {
        TextView textViewWhitePlayerName =
                (TextView) activity.findViewById(R.id.white_player_name);
        TextView textViewWhitePlayerScore =
                (TextView) activity.findViewById(R.id.white_player_score);
        TextView textViewWhitePlayerTimer =
                (TextView) activity.findViewById(R.id.white_player_timer);
        ImageView imageView
                = (ImageView) activity.findViewById(R.id.white_player_turn);


        whitePlayer = new Player
                (whitePlayerName,
                        textViewWhitePlayerName,
                        2, textViewWhitePlayerScore,
                        textViewWhitePlayerTimer,
                        whiteDisk,
                        imageView
                );
    }

    /**
     * Creates an instance of GameBoard that stores the current game pieces.
     */
    private void createGameBoard() {
        gameBoard = new GameBoard(blackDisk, whiteDisk);
    }

    /**
     * Creates a new grid view (8x8) and displays it on the screen.
     * The grid features the starting positions of the game ready to go.
     * <br>
     * Pieces are displayed via images which are decided by the array parameter
     * @param boardLayout int[]: a populated board array. The array should hold
     *                    the current positions of all the counters on the board
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
        gridView.setBackgroundColor(Color.parseColor(ORANGE_100));
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
                    endGameAlert(winner);
                    addScoresToDatabase(winner, loser);
                } else {
                    // turn logic taking the position tapped as a parameter.
                    playerTurn(position);
                }
            }
        });
    }

    /**
     * Displays an end game alert to the user. The winning player and their
     * score is shown.
     */
    private void endGameAlert(Player winningPlayer) {
        new AlertDialog.Builder(activity)
                .setTitle(winningPlayer.getName() + " is the winner!")
                .setMessage("Congratulations " + winningPlayer.getName() +
                        ". Your final score was: "
                        + winningPlayer.getScore())
                .setPositiveButton("Awesome!", null)
                .setIcon(android.R.drawable.star_big_on)
                .show();
    }

    /**
     * Adds scores to the database for both players.
     * Once scores have been set no more additions to the database will happen
     * until a new game is started.
     */
    private void addScoresToDatabase(Player winningPlayer, Player losingPlayer) {
        if (!scoresAdded) {
            database.add(winningPlayer.getName(),   // add name
                    winningPlayer.getScore(),       // add score
                    database.getWritableDatabase());// get db to write values to

            database.add(losingPlayer.getName(),    // add name
                    losingPlayer.getScore(),        // add score
                    database.getWritableDatabase());// get db to write values to

            // ensure no more scores are added to the DB for this game.
            scoresAdded = true;
        }
    }


    /**
     * Player turn logic.<br>
     *     Checks to see if a player has valid moves - if they have valid moves
     *     on the board, check to see if teh attempted move is a valid one.
     *
     *     if no moves are available to a player, they are prompted to pass the
     *     device.
     * @param tileTapped int: the position on the board a player has tapped.
     */
    private void playerTurn(int tileTapped) {

        // check to see if a player has any moves available to them on the board
        if (Rules.canPlayerMakeAMove(gameBoard, currentPlayer.getPlayerDiskColour())) {

            // if they have a move available - was the position they tapped a valid tile?
            if (validMovePossible(tileTapped, currentPlayer.getPlayerDiskColour())) {
                // place the piece
                gameBoard.placePiece(tileTapped, currentPlayer.getPlayerDiskColour());
                // change the turn
                changeTurn();
            }
            // if the current player has NO valid moves available to them on the board.
        } else {
            // pause their timer (if this is a timed game)
            if (isTimedGame) { currentPlayer.pauseTimer(); }
            // display a message - the button click listener will change the player turn.
            new AlertDialog.Builder(activity)
                    .setTitle(currentPlayer.getName() + " Cannot Move")
                    .setMessage(currentPlayer.getName() + " has no valid moves available on the board. " +
                            "You turn is forfeit. Please pass the device to " +
                            waitingPlayer.getName())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            changeTurn();
                        }
                    })
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .show();
        }
        updateBoard(gameBoard.getBoard());

        updateScores(currentPlayer, waitingPlayer);

        printScores(currentPlayer, waitingPlayer);
    }

    /**
     * Prints the current score to the screen for each player parameter
     * @param currentPlayer Player: a player you wish to print scores for.
     * @param waitingPlayer Player: a player you wish to print scores for.
     */
    private void printScores(Player currentPlayer,Player waitingPlayer) {
        currentPlayer.printScore();
        waitingPlayer.printScore();
    }

    /**
     * Counts the number of pieces on the board belonging to a player and sets
     * the total to that players score variable.
     * @param currentPlayer Player: a Player whose score you wish to update
     * @param waitingPlayer Player: a Player whose score you wish to update
     */
    private void updateScores(Player currentPlayer,Player waitingPlayer) {
        currentPlayer.setScore(gameBoard.countPieces(currentPlayer.getPlayerDiskColour()));
        waitingPlayer.setScore(gameBoard.countPieces(waitingPlayer.getPlayerDiskColour()));
    }

    /**
     * Swaps active and waiting players.
     * pauses and starts timers accordingly
     */
    private void changeTurn() {
        // swap player variables - the waiting player is now the
        // currentPlayer
        Player temp = currentPlayer;
        currentPlayer = waitingPlayer;
        waitingPlayer = temp;

        // current player has updated - set their turn indicator
        currentPlayer.setTurnIndicator();
        waitingPlayer.resetTurnIndicator();

        if (isTimedGame) {
            currentPlayer.startTimer();
            waitingPlayer.pauseTimer();
        }
    }

    /**
     * Returns true or false if the game is over or not
     * This could be due to a player running out of time or that there are no
     * more moves possible to make on the gameBoard.
     * <br>
     *     If you run out of time you automatically  lose, regardless of score.
     * @return boolean
     */
    private boolean gameOver() {
        boolean gameOver = false;

        // if the current player and the waiting player cannot make a valid move
        // on the game board, then it's game over
        if (!Rules.canPlayerMakeAMove(gameBoard, currentPlayer.getPlayerDiskColour()) &&
                !Rules.canPlayerMakeAMove(gameBoard, waitingPlayer.getPlayerDiskColour())) {
            // set game over to true
            gameOver = true;
            setWinnerBasedOnScore();
        }

        // check this secondly - because regardless of score, if you ran out of
        // time - you automatically lose.
        if (currentPlayer.hasRunOutOfTime()) {
            gameOver = true;
            loser = currentPlayer;
            winner = waitingPlayer;
        }

        if (gameOver) {
            stopGameTimers();
        }

        return gameOver;
    }

    /**
     * Sets the winner to the player with the highest score and the loser to the
     * player with the lowest score.
     */
    private void setWinnerBasedOnScore() {
        // assign the winner based on who has the greater score.
        if (currentPlayer.getScore() > waitingPlayer.getScore()) {
            winner = currentPlayer;
            loser = waitingPlayer;
        } else {
            winner = waitingPlayer;
            loser = currentPlayer;
        }
    }

    /**
     * Stops all game timers for both players. Validation is in place to ignore
     * this method if the game has no timer set.
     */
    private void stopGameTimers() {
        if (isTimedGame) {
            winner.pauseTimer();
            loser.pauseTimer();
        }
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

    /**
     * Easy to use toast method to save clutter.
     * @param messageToDisplay String: message you wish printed to the screen.
     */
    private void displayToast(String messageToDisplay) {
        Toast.makeText(activity.getBaseContext(), messageToDisplay,
                Toast.LENGTH_SHORT).show();
    }


}

