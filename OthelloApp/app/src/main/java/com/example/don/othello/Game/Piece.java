package com.example.don.othello.Game;

/**
 * Created by don on 05/03/2015.
 */
public class Piece {

    PieceColour colour;

    public Piece (PieceColour playerColour) {
     this.colour = playerColour;
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
