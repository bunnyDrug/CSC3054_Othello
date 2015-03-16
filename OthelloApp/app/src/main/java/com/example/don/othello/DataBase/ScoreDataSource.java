package com.example.don.othello.DataBase;

/**
 * Created by Chris on 16/03/2015.
 *
 */
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class ScoreDataSource {
    //DataBase fields
    private SQLiteDatabase database;
    private MySqliteHelper dbHelper;
    private String [] allColumns ={MySqliteHelper.Column_ID, MySqliteHelper.Column_Player , MySqliteHelper.Column_Score};

    public ScoreDataSource(Context context){
        //new class
        dbHelper = new MySqliteHelper(context);
    }
    public void open()throws SQLException{
        //open for read and write
        database = dbHelper.getWritableDatabase();

    }
    public void close(){
        //to close
        dbHelper.close();
    }

    public Score createScore(String pla, int sco){
        ContentValues values = new ContentValues();
        values.put(MySqliteHelper.Column_Player,pla);


        long insertId = database.insert(MySqliteHelper.Table_Name,null,values);

        Cursor cursor =database.query(MySqliteHelper.Table_Name,allColumns,MySqliteHelper.Column_ID + "=" +
        insertId,null,null,null,null);

        cursor.moveToFirst();
        Score newScore = cursorToScore(cursor);
        return  newScore;

    }
    public  void deleteScore(Score score){
        long id =score.getId();
        System.out.print("Score deleted with id:" + id);
        database.delete(MySqliteHelper.Table_Name,MySqliteHelper.Column_ID + " = " + id, null);

    }
    public List<Score> getAllScores(){
        List<Score> scores = new ArrayList<>();
        Cursor cursor = database.query(MySqliteHelper.Table_Name,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Score score = cursorToScore(cursor);
            scores.add(score);
            cursor.moveToNext();
        }
        cursor.close();
        return scores;
    }
    private Score cursorToScore (Cursor cursor){
        Score score = new Score();
        score.setId(cursor.getLong(0));
        score.setPlayer(cursor.getString(1));
        return score;
    }


}
