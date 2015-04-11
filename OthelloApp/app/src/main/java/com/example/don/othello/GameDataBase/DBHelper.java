package com.example.don.othello.GameDataBase;

/**
 * Created by Chris on 27/03/2015.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * this class is to be used to connect a database to the project, in creating, updating and deleting
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Othello.db";

    public static final String TABLE_NAME = "OthelloScores";
    public static final String Column_id = "_id";
    public static final String Column_Player = "player_name";
    public static final String Column_PlayerScore = "player_score";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
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
    //add new row to database
    public void add(score player ){
        ContentValues values = new ContentValues();
        values.put(Column_Player, player.get_player());
        values.put(Column_PlayerScore,player.get_score());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();

    }


    //delete from the database
    public void delete(String player){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + Column_Player + "=\"" + player + "\";");
    }

    //print out the database as a string
    public String databaseToString(){
        String dbString= "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("player_name"))!=null){
                dbString += c.getString(c.getColumnIndex("player_name"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

}
