package com.example.don.othello.Game;

import com.example.don.othello.R;

/**
 * Created by don on 05/03/2015.
 * Holds the locations of the counters on the game board
 * These are stored in an array.
 */
public class GameBoard {

    private static int[] boardPieces = new int[64];
    PieceColour colour;


    public GameBoard() {

    }

    /**
     * populates the board array with images.
     * The starting positions are also taken into account.
     * @return
     */
    public int[] initStartingPieces() {
        for (int i = 0; i < boardPieces.length; i ++) {
            boardPieces[i] = R.drawable.placement_counter;
            if (i == 27 || i == 36) {
                boardPieces[i] = R.drawable.white_disk;
            }
            if (i == 28 || i == 35) {
                boardPieces[i] = R.drawable.black_disk;
            }
        }
        return boardPieces;
    }

    private void placePiece() {

    }

    public void flipColour() {

    }
}
