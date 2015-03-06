package com.example.don.othello.Game;

import java.util.Timer;

/**
 * Created by don on 05/03/2015.
 */
public class Player {

    private String name;
    private boolean validMovesAvailable = true;
    private Timer individualTimer;

    public Player (String name, Boolean timedGame){
        this.name = name;
        if (timedGame) {
//            individualTimer == settings.timer
        }
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public boolean hasValidMovesAvailable() {
        return validMovesAvailable;
    }

    public void setValidMovesAvailable(boolean validMovesAvailable) {
        this.validMovesAvailable = validMovesAvailable;
    }
}
