package com.example.don.othello.Game;

import com.example.don.othello.R;

/**
 * Created by don on 05/03/2015.
 * Holds the locations of the counters on the game board
 * These are stored in an array.
 */
class GameBoard {

    private int[] board = new int[64];
    PieceColour colour;


    /**
     * Creates a new array to hold the disks/counters in play. The new board
     * is initialised with othello starting positions - two white and two black
     * counters.
     */
    public GameBoard() {
        initStartingPieces();
    }

    /**
     * Populates the board array with drawable resources to represent the disks
     * in play - the middle four tiles contain the starting counters for a new
     * game.
     */
    private void initStartingPieces() {
        for (int i = 0; i < board.length; i ++) {
            board[i] = R.drawable.placement_counter;
            if (i == 27 || i == 36) {
                board[i] = R.drawable.white_disk;
            }
            if (i == 28 || i == 35) {
                board[i] = R.drawable.black_disk;
            }
        }
    }

    /**
     * Returns the board array which holds the location of all pieces in
     * the game.
     * @return an integer array which holds the location of all pieces in play
     */
    public int[] getBoard() {
        return board;
    }

    /**
     * Places an interger value into the board array which keeps track of the
     * position of all game pieces. It requires a drawable resource and the
     * position in the array you would like it to be placed into.
     * @param positionTapped position in the array where you wish to add a
     *                       player counter
     * @param piece A drawable resources used to display an image on the grid
     *              view.
     */
    public void placePiece(int positionTapped, int piece) {
        board[positionTapped] = piece;
    }

    public void flipColour() {

    }

    /**
     * Returns an integer at a specific location
     * @param positionTapped location tapped on the grid view
     * @return int a drawable resource.
     */
    public int getPiece(int positionTapped) {
        return board[positionTapped];
    }
}
