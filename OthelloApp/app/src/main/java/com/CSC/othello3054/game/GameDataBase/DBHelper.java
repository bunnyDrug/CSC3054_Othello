package com.CSC.othello3054.game.GameDataBase;

/**
 * Created by Chris on 27/03/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * this class is to be used to connect a database to the project, in creating, updating and deleting
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Othello.db";

    private static final String TABLE_NAME = "OthelloScores";
    private static final String Column_id = "_id";
    private static final String Column_Player = "player_name";
    private static final String Column_PlayerScore = "player_score";

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

    public void deleteTalbe(SQLiteDatabase db){
        db.delete(TABLE_NAME,null,null);
    }

    //add new row to database
    public void add(String playerName, int score, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(Column_Player, playerName);
        values.put(Column_PlayerScore, score);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //print out the database as a string
    public String databaseToString(SQLiteDatabase db){
        String dbString= "";
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Column_PlayerScore + " DESC LIMIT 5";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            dbString += "Name: " + c.getString(c.getColumnIndex(Column_Player));
            dbString += " Score: " + c.getString(c.getColumnIndex(Column_PlayerScore));
            dbString += "\n";
            c.moveToNext();
        }


        c.close();
        db.close();
        return dbString;
    }

}
