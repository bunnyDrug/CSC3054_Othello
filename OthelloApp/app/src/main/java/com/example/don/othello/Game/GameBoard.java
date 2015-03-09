package com.example.don.othello.Game;

import com.example.don.othello.R;

/**
 * Created by don on 05/03/2015.
 * Holds the locations of the counters on the game board
 * These are stored in an array.
 */
public class GameBoard {

    private static int[] board = new int[64];
    PieceColour colour;


    public GameBoard() {

    }

    /**
     * populates the board array with images.
     * The starting positions are also taken into account.
     * @return
     */
    public static void initStartingPieces() {
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


    public static int[] getBoard() {
        return board;
    }

    public static void placePiece(int positionTapped, int piece) {
        board[positionTapped] = piece;
    }

    public void flipColour() {

    }

    public static int getPiece(int positionTapped) {
        return board[positionTapped];
    }
}
