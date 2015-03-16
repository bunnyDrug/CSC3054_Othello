package com.example.don.othello.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
* Created by Chris on 13/03/2015.
*/
public class DatabaseOperations extends SQLiteOpenHelper {
    public static final String DataBasePath = "/data/data/com.example.don.othello.Database";
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE" + TableData.TableInfo.TABLE_NAME + "("+ TableData.TableInfo.COLUMN_NAME_PLAYERNAME+" TEXT,"
            + TableData.TableInfo.COLUMN_NAME_PLAYERSCORE + "INTEGER" + TableData.TableInfo.COLUMN_NAME_PLAYERID +" INTEGER PRIMARY KEY autoincrement "+")";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS" + TableData.TableInfo.TABLE_NAME;

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME,null,database_version);
        Log.d("Database operations","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb){
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations","Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase agr0, int oldV,int newV){

    }
    public void CreateDatabase()

    {
        CreateDB();
    }
    public void CreateDB()
    {
        Boolean dbExists = DBExist();

        if(!dbExists){
            this.getReadableDatabase();

            copyDBFromResource();
        }
    }
    public Boolean DBExist (){
        SQLiteDatabase db = null;
        return db !=null ? true: false;
    }
    private void copyDBFromResource(){

    }
    public void putInformation(DatabaseOperations dop, String name,Integer sco){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.COLUMN_NAME_PLAYERNAME,name);
        cv.put(TableData.TableInfo.COLUMN_NAME_PLAYERSCORE,sco);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database operations","one raw insert");

    }
}
