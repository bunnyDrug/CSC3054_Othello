package com.example.don.othello.Game;

/**
 * Created by don on 05/03/2015.
 */
public class OthelloSystem {

    Player player1;
    Player player2;

    /**
     * Creates 2 player objects
     * @param player1 A name for player one (Can obtained in from a Text Field)
     * @param player2 A name for player two (Can obtained in from a Text Field)
     * @param timer Is this game a timed one? Set via the app settings.
     */
    public OthelloSystem (String player1, String player2,
                          Boolean timer) {

        // if the timer setting enabled - read the timer value, add it to each
        // player

        this.player1 = new Player(player1, timer);
        this.player2 = new Player(player2, timer);
    }

    public void turn(){

//        player1.placePiece(new Piece(), )

    }

    /**
     * Discovers all the valid moves that a player may take in his or her
     * current turn. A players piece is only allowed to be placed in any of the
     * legal spaces for that turn.
     *
     * A possible overlay of movies that are valid would be nice
     * @return boolean
     */
    public boolean calculateCurrentValidMoves() {

        return true;
    }

    public void displayValidMoves() {

    }


    /**
     * Returns true or false if the game is over or not
     * This could be due to a player running out of time or that there are no
     * more moves possible to make on the board.
     * @return boolean
     */
    public boolean gameOver(Player player1, Player player2) {

        // if player has run out of time

        // if both players have NO valid moves.
        if (!player1.hasValidMovesAvailable()
                && !player2.hasValidMovesAvailable()){
            return true;
        }

        return false;
    }

    public boolean validMovePossible() {
        return calculateCurrentValidMoves();
    }
}
