package com.example.don.othello.Game;

import com.example.don.othello.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The rules class - this handles all the processing for game rules.
 * Created by don on 11/03/2015.
 */
public final class Rules {
    private final static int NORTH = -8; // from current position
    private final static int EAST = + 1;
    private final static int SOUTH = + 8;
    private final static int WEST = - 1;

    private final static int NORTH_EAST = (NORTH) + EAST;
    private final static int NORTH_WEST = (NORTH) + WEST;

    private final static int SOUTH_EAST = SOUTH + EAST;
    private final static int SOUTH_WEST = SOUTH + WEST;

    private Rules() {

    }

    /**
     * Runs all validation checks on a tile to ensure the placement is correct
     * @param position
     * @param gameBoard
     * @param currentDisk
     * @return
     */
    public static boolean validDiskPlacement(
            int position, GameBoard gameBoard, int currentDisk) {

        // get the tiles to check
        int[] tilesToCheck = getTilesAroundTappedPosition(
                position,
                gameBoard.getLeftBoardEdge(),
                gameBoard.getRightBoardEdge(),
                gameBoard.getTopBoardEdge(),
                gameBoard.getBottomBoardEdge());

        return isTappedTileEmpty(position, gameBoard)

               &&

               nextToOppositeColourDisk(position, currentDisk,
                       tilesToCheck, gameBoard);
    }

    /**
     * Checks to see if the counter wishing to be placed is next to a counter of
     * opposite colour.
     * @return true if a counter is next to another counter of a different
     * colour
     */
    private static boolean nextToOppositeColourDisk(int positionTapped,
                                                    int currentDisk,
                                                    int[] tilesToCheck,
                                                    GameBoard gameBoard) {
        boolean validMove = false;

            // for every tile
            for (int tile : tilesToCheck) {

                // if any of the tiles are not placement counters
                if (gameBoard.getPiece(positionTapped + tile)
                        != R.drawable.placement_counter) {

                    // if any of the tiles surrounding are not the same
                    // colour as the current disk
                    if (gameBoard.getPiece(positionTapped + tile)
                            != currentDisk){
                        validMove = true;
                        break;
                    }
                }
            }

        return validMove;
    }

    /**
     * ensures the player is not trying to place a counter on an occupied tile
     * @return
     */
    private static boolean isTappedTileEmpty(int position,
                                             GameBoard gameBoard) {
        return gameBoard.getPiece(position) == R.drawable.placement_counter;
    }

    /**
     * checks if the position tapped is at the top edge of the board
     * @param edgeArray and int array that contains values of tiles at the
     *                       top of the screen
     * @param positionTapped the position tapped by the user on the grid
     * @return
     */
    private static boolean atEdge(int[] edgeArray, int positionTapped) {
        boolean result = false;
        for (int anEdgeTile : edgeArray) {
            if (anEdgeTile == positionTapped) {
                result = true; // we are at the edge of the board
                break; // no need to keep looping
            }
        }
        return result;
    }

    /**
     * Discovers all the valid moves that a player may take in his or her
     * current turn. A players boardPieces is only allowed to be placed in any
     * of the legal spaces for that turn.
     *
     * A possible overlay of movies that are valid would be nice
     * @return boolean
     */
    private static boolean calculateCurrentValidMoves() {

        return true;
    }


    /**
     * Creates an array of tile positions around the tapped position on the grid
     * that are used for checking various things.
     * @param positionTapped
     * @param westBoardEdge
     * @param eastBoardEdge
     * @param northBoardEdge
     * @param southBoardEdge
     * @return
     */
    private static int[] getTilesAroundTappedPosition(int positionTapped,
                                                      int[] westBoardEdge,
                                                      int[] eastBoardEdge,
                                                      int[] northBoardEdge,
                                                      int[] southBoardEdge) {
        ArrayList<Integer> positionsToCheck = new ArrayList<>();

        positionsToCheck.add(NORTH);
        positionsToCheck.add(NORTH_EAST);
        positionsToCheck.add(NORTH_WEST);
        positionsToCheck.add(EAST);
        positionsToCheck.add(WEST);
        positionsToCheck.add(SOUTH);
        positionsToCheck.add(SOUTH_EAST);
        positionsToCheck.add(SOUTH_WEST);

        if (atEdge(northBoardEdge, positionTapped)) {
            // remove positions  \ | /
            positionsToCheck.remove((Integer) NORTH);
            positionsToCheck.remove((Integer) NORTH_EAST);
            positionsToCheck.remove((Integer) NORTH_WEST);
        }
        if (atEdge(westBoardEdge, positionTapped)) {
            // remove positions \
            //                  -
            //                  /
            positionsToCheck.remove((Integer) WEST);
            positionsToCheck.remove((Integer) NORTH_WEST);
            positionsToCheck.remove((Integer) SOUTH_WEST);
        }
        if (atEdge(southBoardEdge, positionTapped)) {
            // remove positions /|\
            positionsToCheck.remove((Integer) SOUTH);
            positionsToCheck.remove((Integer) SOUTH_WEST);
            positionsToCheck.remove((Integer) SOUTH_EAST);
        }
        if (atEdge(eastBoardEdge, positionTapped)) {
            // remove positions /
            //                  -
            //                  \
            positionsToCheck.remove((Integer) EAST);
            positionsToCheck.remove((Integer) NORTH_WEST);
            positionsToCheck.remove((Integer) SOUTH_EAST);
        }
        positionsToCheck.trimToSize();
        return convertIntegers(positionsToCheck);
    }

    /**
     * converts a ListArray<Integer> into an array of int
     * Taken from <a href="http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array">here</a>
     *
     * @param integers the ListArray you wished converted to integer
     * @return an array of ints
     */
    private static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
