package com.example.don.othello;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.don.othello.DataBase.DatabaseOperations;
import com.example.don.othello.DataBase.TableData;


public class Scores extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // to receive data from the database
    public Cursor GetScores (DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String [] columns ={TableData.TableInfo.COLUMN_NAME_PLAYERNAME, TableData.TableInfo.COLUMN_NAME_PLAYERSCORE};
        Cursor cr = SQ.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null, "playerScore ASC");
        return cr;

    }
}
