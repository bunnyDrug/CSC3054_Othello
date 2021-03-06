package com.CSC.othello3054.game.Game;

import com.CSC.othello3054.game.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by don on 11/03/2015.
 *
 * The rules class - this handles all the processing for game rules.
 */
final class Rules {
    private final static int NORTH = -8; // from current position
    private final static int EAST = + 1;
    private final static int SOUTH = + 8;
    private final static int WEST = - 1;

    private final static int NORTH_EAST = (NORTH) + EAST;
    private final static int NORTH_WEST = (NORTH) + WEST;

    private final static int SOUTH_EAST = SOUTH + EAST;
    private final static int SOUTH_WEST = SOUTH + WEST;


    private static ArrayList<Integer> directionFound = new ArrayList<>();


    // don't allow instance of Rules
    private Rules() {

    }

    /**
     * Assumes that a player has a move to make -
     * (ensure you have run canPlayerMakeAMove() first<br>
     * Runs all validation checks on a player tapped tile to ensure the
     * placement is correct
     * @param position the position you want checked
     * @param gameBoard Gameboard: the board we are playing on.
     * @param currentDisk int: the current players disk.
     * @return boolean true if the move is valid and a counter can be placed here
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
                        tilesToCheck, gameBoard)

                &&

                validPlacement(convertIntegers(directionFound),currentDisk,position,gameBoard, false);
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
                    directionFound.add(tile);
                    validMove = true;
                }
            }
        }
        directionFound.trimToSize();
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
     * checks if the position tapped is at the edge of the board
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
     * checks if a counter can be placed in the tile - ie it needs to be able to
     * flip at least one opposing counter.
     * @param directionsFound
     * @param currentDisk
     * @param position
     * @param gameBoard
     * @return
     */
    private static boolean validPlacement(int[] directionsFound, int currentDisk,int position, GameBoard gameBoard, boolean moveCheck) {

        // LOG.D used for debugging - uncomment if you wish.

        boolean result = false;

        // Log.d("Results", "****TAPPED POSITION**** " + position);

        // for all directions
        for (int direction: directionsFound) {

            boolean eastWest = false;
            if ((direction == EAST) || (direction == WEST)) {
                // Log.d("Rules", "check east or west");
                eastWest = true;
            }
            boolean northSouth = false;
            if ((direction == NORTH) || (direction == SOUTH)) {
                northSouth = true;
                // Log.d("Rules", "check north or south");
            }

            // Log.d("Rules", "checking direction " + direction);
            int newPosition = position;

            // if adjacent tile is at the edge
            if (reachedEdge((newPosition = newPosition + direction), gameBoard) && !eastWest && !northSouth) {
                // check the tile after it - ensure not out of bounds.
                if ((newPosition = newPosition + direction) < 0 || (newPosition) > 64) {
                    // Log.d("Results", "Next counter in direction " + direction + " would be over edge - skip");
                }
            }

            else {
                do {
                    // in other words you have reached the edge of a diagonal check
                    if ((reachedEdge(newPosition, gameBoard)) && (direction != EAST) && (direction != WEST) && (direction != NORTH) && (direction != SOUTH) && (gameBoard.getPiece(newPosition) != currentDisk)) {
                        // Log.d("Rules:", "Reached edge of board - skipping");
                        //result = false;
                        break;
                    }
                    if (direction == EAST) {
                        if (atEdge(gameBoard.getRightBoardEdge(),newPosition) && (gameBoard.getPiece(newPosition) != currentDisk)){
                            // Log.d("Rules:", "Reached the end of the board checking EAST - skipping");
                            //result = false;
                            break;
                        }
                    }
                    if (direction == WEST) {
                        if (atEdge(gameBoard.getLeftBoardEdge(),newPosition) && (gameBoard.getPiece(newPosition) != currentDisk)){
                            // Log.d("Rules:", "Reached the end of the board checking WEST - skipping");
                            //result = false;
                            break;
                        }
                    }
                    if (direction == NORTH) {
                        if (atEdge(gameBoard.getTopBoardEdge(),newPosition) && (gameBoard.getPiece(newPosition) != currentDisk)){
                            // Log.d("Rules:", "Reached the end of the board checking NORTH - skipping");
                            //result = false;
                            break;
                        }
                    }
                    if (direction == SOUTH) {
                        if (atEdge(gameBoard.getBottomBoardEdge(),newPosition) && (gameBoard.getPiece(newPosition) != currentDisk)){
                            // Log.d("Rules:", "Reached the end of the board checking SOUTH - skipping");
                            //result = false;
                            break;
                        }
                    }
                    if (gameBoard.getPiece(newPosition) == R.drawable.placement_counter) {
                        // Log.d("Results", "Counter in tile " + newPosition + " is a blank tile - skip");
                        break;
                    }
                    if ((gameBoard.getPiece(newPosition) == currentDisk)){
                        // Log.d("Rules:", "found a matching tile in the direction " + direction + " at position " + newPosition);
                        result = true;

                        // backtrack to position tapped. - checking for valid moves on all disks on the board.
                        if (!moveCheck) {
                            while (newPosition != position) {
                                newPosition = newPosition - direction;
                                gameBoard.placePiece(newPosition, currentDisk);
                            }
                        }
                        break;
                    }
                    // advance the tile by the direction we are looking.
                    else {
                        // Log.d("Rules:", "Current position = " + newPosition + " increase newPosition by " + direction);
                        newPosition = newPosition + direction;
                        // Log.d("Rules:", "New position = " + newPosition);
                    }
                } while (true);
            }
        }

        // clear the directionFoundArrayList for use again during the next turn
        directionFound.clear();
        return result;
    }

    /**
     * have we reached a board edge?
     * @param position the position you want checked
     * @param gameBoard the board to check the position of
     * @return boolean: true if you reached any edge of the board (top, left,
     * right or bottom).
     */
    private static boolean reachedEdge(int position, GameBoard gameBoard) {
        return  atEdge(gameBoard.getTopBoardEdge(), position) ||
                atEdge(gameBoard.getBottomBoardEdge(), position) ||
                atEdge(gameBoard.getRightBoardEdge(), position) ||
                atEdge(gameBoard.getLeftBoardEdge(), position);
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
            positionsToCheck.remove((Integer) NORTH_EAST);
            positionsToCheck.remove((Integer) SOUTH_EAST);
        }
        positionsToCheck.trimToSize();
        return convertIntegers(positionsToCheck);
    }

    /**
     * converts a ListArray<Integer> into an array of int,
     * taken from <a href="http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array">here</a>
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

    /**
     * Discovers all the valid moves that a player may take in his or her
     * current turn. If a player has no moves available to them, a false will be
     * returned.
     * @param gameBoard Gameboard: the board we are playing on.
     * @param diskColour int: the colour of disk to check for validity - ie, can
     *                   black make any valid moves this turn?
     * @return boolean: true for yes - at least 1 valid move is possible
     * somewhere on the board<br>
     *     false if no moves are possible for that players colour.
     */
    public static boolean canPlayerMakeAMove(GameBoard gameBoard, int diskColour) {
        boolean result = false;

        int[] boardPositions= new int[64];

        for (int i = 0; i < boardPositions.length; i ++) {
            boardPositions[i] = i;
        }

        for (int position: boardPositions) {

            int[] tilesToCheck = getTilesAroundTappedPosition(
                    position,
                    gameBoard.getLeftBoardEdge(),
                    gameBoard.getRightBoardEdge(),
                    gameBoard.getTopBoardEdge(),
                    gameBoard.getBottomBoardEdge());

            if (isTappedTileEmpty(position, gameBoard) &&
                    nextToOppositeColourDisk(position, diskColour, tilesToCheck, gameBoard) &&
                    validPlacement(convertIntegers(directionFound), diskColour, position, gameBoard, true)) {
                result = true;
            }
        }
        return result;
    }
}
