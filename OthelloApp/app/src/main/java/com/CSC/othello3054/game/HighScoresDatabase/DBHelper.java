package com.CSC.othello3054.game.HighScoresDatabase;

/**
 * Created by Chris on 27/03/2015.
 * This class is to be used to connect a database to the project.
 * Assists in creating, updating and deleting values from the database
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Othello.db";

    private static final String TABLE_NAME = "OthelloScores";
    private static final String Column_id = "_id";
    public static final String Column_Player = "player_name";
    public static final String Column_PlayerScore = "player_score";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                Column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_Player + " TEXT, " +
                Column_PlayerScore + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Don:
     * Adds a new row into the database
     *
     * @param playerName String: the name of the player you wish to add
     * @param score      int: his or her score
     * @param db         SQLiteDatabase: the database to be updated with the new record
     */
    public void add(String playerName, int score, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Column_Player, playerName);
        values.put(Column_PlayerScore, score);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Don:
     * Gets the top 5 players from the database and sorts them in order of best
     * score. This information is returned in a formatted String
     *
     * @param db the database you wish to query
     * @return String: A formatted string containing the top 5 players.
     */
    public String topPlayerToStringFBSHARE(SQLiteDatabase db) {
        String dbString = "";
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Column_PlayerScore + " DESC LIMIT 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        // run for the length of the database
        while (!c.isAfterLast()) {
            dbString += "Name: " + c.getString(c.getColumnIndex(Column_Player));
            dbString += " Score: " + c.getString(c.getColumnIndex(Column_PlayerScore));
            dbString += "\n";

            // move to the row to continue appending to the dbString.
            c.moveToNext();
        }

        c.close();
        db.close();
        return dbString;
    }
    /*
     * Cursor method to return all rows to be populated in the listView
     * Gets a query of select all from the table Othello scores
     *
     * @param db the database you wish to query
     * @return Cursor: A formatted string containing the all players.
     *
     */


    public Cursor getAllRows(SQLiteDatabase db) {

            String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Column_PlayerScore + " DESC ";
            Cursor c = db.rawQuery(query, null);
            if (c != null) {
                c.moveToFirst();
            }
        return c;

    }

    /*
     *Cursor method to return the top ten scores, and put them in a list view
     * Gets a query of select al from the table and limiting the results to the top 10
     *
     * @param db the database you wish to query
     * @return String: A formatted string containing the top 10 players.
     */
    public Cursor topTen(SQLiteDatabase db){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Column_PlayerScore + " DESC LIMIT 10";
        Cursor c = db.rawQuery(query,null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
}


