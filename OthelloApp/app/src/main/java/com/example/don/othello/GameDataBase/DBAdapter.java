package com.example.don.othello.GameDataBase;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * Created by Chris on 22/03/2015.
 */
public class DBAdapter {
    private static final String TAG = "DBAdapter"; //used for logging database version
    //database path
    //this is meant to be the default path but its not creating it there "angry face"
    private static String DB_Path = "/data/data/com.example.don.othello/databases/";
    //Field names:

    public static final String Column_PlayerID = "playerID";
    public static final String Column_PlayerNAME = "playerName";
    public static final String Column_Score = "score";

    public static final String[] All_Columns = new String[]{Column_PlayerID,Column_PlayerNAME,Column_Score};

    //Column Numbers for each field Name:
    public static final int Col_ROWID = 0;
    public static final int Col_PLAYERNAME = 1;
    public static final int Col_Score =2;

    //Database info:
    public static final String TABLE_NAME ="GameScores";
    public static final String DataBase_Name = "Othello";
    public static final int DATABASE_VER = 1; //This version must be incremented

    //SQL statement to create database
    private static final String DATABASE_CREATE_SQL =
            "CREATE TABLE " + TABLE_NAME + "(" + Column_PlayerID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Column_PlayerNAME + " TEXT NOT NULL, "
            + Column_Score + " INTEGER "
            +");";

    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context =ctx;
        myDBHelper = new DatabaseHelper (context);
    }

    //open the database connection.
    public DBAdapter open(){
        db = myDBHelper.getWritableDatabase();

        return this;
    }
    //Close the database connection
    public void close(){
        myDBHelper.close();
    }

    //add a new set of values to be inserted into the database
    public long insertRow (String player,Integer score){
        ContentValues initialValues = new ContentValues();
        initialValues.put(Column_PlayerNAME,player);
        initialValues.put(Column_Score,score);

        //insert the data into the database.
        return db.insert(TABLE_NAME,null,initialValues);


    }
    //delete a row from the database, by column id(primary key)
    public boolean deleteRow(long rowId){
        String where = Column_PlayerID +"=" + rowId;
        return db.delete(TABLE_NAME,where,null)!=0;
    }
    //return all data in the database.
    public Cursor getAllRows(){
        String where = null;
        Cursor c = db.query(true,TABLE_NAME,All_Columns,where,null,null,null,null,null);
        if(c !=null){
            c.moveToFirst();
        }
        return c;
    }
    //change existing row to be equal to new data
    public boolean updateRow(long rowId,String player, int score){
        String where = Column_PlayerID + "=" + rowId;
        ContentValues newValues = new ContentValues();
        newValues.put(Column_PlayerNAME,player);
        newValues.put(Column_Score,score);
        //insert it into the database.
        return db.update(TABLE_NAME, newValues, where,null)!=0;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context){
            super(context,DataBase_Name,null,DATABASE_VER);
        }


        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVer, int newVer) {
            Log.w(TAG, "Upgrading application's database from version" + oldVer
                    + " to " + newVer + ",which will destory all old data!");
            //destroy old database
            _db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            //recreate new database
            onCreate(_db);
        }
    }



}
