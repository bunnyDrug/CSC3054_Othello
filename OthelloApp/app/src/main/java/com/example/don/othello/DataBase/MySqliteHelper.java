package com.example.don.othello.DataBase;

/**
 * Created by Chris on 16/03/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
public class MySqliteHelper extends SQLiteOpenHelper{
    //initialise the table of the database

        public static final String Table_Name = "highScores";
        public static final String Column_Player = "player";
        public static final String Column_Score = "score";
        public static final String Column_ID = "id";

    private static final String DATABASE_NAME = "othello.db";
    private static final int DATABASE_VERSION = 1;

    //database creation sql statement
    private static final String DATABASE_CREATE= "create table" + Table_Name + "(" + Column_ID
            + "integer primary key autoincrement," + Column_Player + "text not null," +
            Column_Score + "integer);";

    public MySqliteHelper(Context context){
        super (context,DATABASE_NAME,null,DATABASE_VERSION );

    }
    @Override
    public void onCreate (SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVer, int NewVer){
        Log.w(MySqliteHelper.class.getName(),"Upgrading old version" + oldVer + "to" + NewVer +
                ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + Table_Name);
        onCreate(db);
    }
}
