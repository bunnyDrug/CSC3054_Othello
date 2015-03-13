package com.example.don.othello.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Chris on 13/03/2015.
 */
public class TableData {
    public TableData()
    {

    }
    public static abstract class TableInfo implements BaseColumns{

        public static final String COLUMN_NAME_PLAYERID = "playerId";
        public static final String COLUMN_NAME_PLAYERNAME = "playerName";
        public static final String COLUMN_NAME_PLAYERSCORE = "playerScore";
        public static final String DATABASE_NAME = "othello.db";
        public static final String TABLE_NAME = "highScores";
    }
}
