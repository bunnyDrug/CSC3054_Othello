package com.example.don.othello.Game;

/**
 * Created by don on 05/03/2015.
 */
public class Piece {

    PieceColour colour;

    public Piece () {
//     this.colour = playerColour;

//        this.colour = player.getColour();
    }

    private void placePiece() {

    }

    public void flipColour() {
        if (this.colour == PieceColour.BLACK) {
            this.colour = PieceColour.WHITE;
        } else {
            this.colour = PieceColour.BLACK;
        }
    }
}
