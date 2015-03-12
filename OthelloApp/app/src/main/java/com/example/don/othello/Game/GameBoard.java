package com.example.don.othello.Game;

import com.example.don.othello.R;

/**
 * Created by don on 05/03/2015.
 * Holds the locations of the counters on the game board
 * These are stored in an array.
 */
class GameBoard {

    private int[] board = new int[64];

    private final int[] leftBoardEdge = new int[8];
    private final int[] rightBoardEdge = new int[8];
    private final int[] topBoardEdge = new int[8];
    private final int[] bottomBoardEdge = new int[8];

    /**
     * Creates a new array to hold the disks/counters in play. The new board
     * is initialised with othello starting positions - two white and two black
     * counters.
     */
    public GameBoard() {
        initStartingPieces();
        defineBoardEdges();
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
     * creates four arrays used to check if a disk is at the edge of the board
     * magic numbers could be at the very least placed into constants later
     * TODO: tidy this up
     */
    private void defineBoardEdges() {
        defineLeftBoardEdge();
        defineRightBoardEdge();
        defineTopBoardEdge();
        defineBottomBoardEdge();
    }

    private void defineLeftBoardEdge() {
        int row = board.length / 8;
        for (int i = 0; i < row; i++) {
            leftBoardEdge[i] = row * (i);
        }
    }

    private void defineRightBoardEdge() {
        int columns = board.length / 8 - 1;
        for (int i = 0; i < columns +1; i++) {
            rightBoardEdge[i] = leftBoardEdge[i] + columns;
        }
    }

    private void defineTopBoardEdge() {
        int columns = board.length / 8;
        for (int i = 0; i < columns; i++) {
            topBoardEdge[i] = i;
        }
    }

    private void defineBottomBoardEdge() {
        int columns = board.length / 8;
        for (int i = 0; i < columns; i++) {
            bottomBoardEdge[i] = topBoardEdge[i] + 56;
        }
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

    /**
     * Returns the board array which holds the location of all pieces in
     * the game.
     * @return an integer array which holds the location of all pieces in play
     */
    public int[] getBoard() {
        return board;
    }

    /**
     * returns the positions in the array that make up the left
     * edge of the board
     * @return an array holding the positions that make up the visual left edge
     * of the game board.
     */
    public int[] getLeftBoardEdge() {
        return leftBoardEdge;
    }

    /**
     * returns the positions in the array that make up the right
     * edge of the board
     * @return an array holding the positions that make up the visual right edge
     * of the game board.
     */
    public int[] getRightBoardEdge() {
        return rightBoardEdge;
    }

    /**
     * returns the positions in the array that make up the bottom
     * edge of the board
     * @return an array holding the positions that make up the visual bottom
     * edge of the game board.
     */
    public int[] getBottomBoardEdge() {
        return bottomBoardEdge;
    }

    /**
     * returns the positions in the array that make up the top
     * edge of the board
     * @return an array holding the positions that make up the visual top edge
     * of the game board.
     */
    public int[] getTopBoardEdge() {
        return topBoardEdge;
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


    // public void flipColour(GameBoard p, int pos) {
    //     //need to get int vaules -8 to 8 to check around the piece and change the colour
    //     GameBoard next = null;
    //     int offset =0;
    //     getPiece(pos);
    //     switch (colour) {
    //         case BLACK:
    //                 //to get the positions that have black pieces
    //             break;
    //         case WHITE:
    //                 //to get the positions that have white pieces
    //             break;
    //         default:
    //             break;

    //         //need an if statement to check the surrounding  pieces




    //     }



    // public void flipColour() {

    // }


