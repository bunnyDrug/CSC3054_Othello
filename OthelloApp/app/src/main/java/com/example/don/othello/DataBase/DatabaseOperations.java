package com.example.don.othello.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLDataException;
import java.util.Locale;

/**
* Created by Chris on 13/03/2015.
*/
public class DatabaseOperations extends SQLiteOpenHelper {
    public static final String DataBasePath = "/data/data/com.example.don.othello.Database";
    public static final int database_version = 1;

    private Context myContext;

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
    //just to call createDB
    public void CreateDatabase()

    {
        CreateDB();
    }
    //to check if the database already exists if it doesnt then call copy form resource
    public void CreateDB()
    {
        Boolean dbExists = DBExist();

        if(!dbExists){
            this.getReadableDatabase();

            copyDBFromResource();
        }
    }
    //to check if the database exists
    public Boolean DBExist (){
        SQLiteDatabase db = null;

        try{
            String databasePath =DataBasePath + TableData.TableInfo.DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(databasePath,null,SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setLockingEnabled(true);
            db.setVersion(1);

        }catch(SQLiteException e){
           Log.e("SQLHelper", "Database not found");
        }
        if(db !=null){db.close();
        }
        return db !=null ? true: false;
    }
    //if the database doesnt exits this calls it to copy from resource
    private void copyDBFromResource(){
        InputStream inputStream =null;
        OutputStream outputStream=null;
        String dbFilePath = DataBasePath + TableData.TableInfo.DATABASE_NAME;
//just to catch any errors when copying from the resource
        try{
            inputStream = myContext.getAssets().open(TableData.TableInfo.DATABASE_NAME);
            outputStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch(IOException e){
            throw new Error("Problem copying database");
        }

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
