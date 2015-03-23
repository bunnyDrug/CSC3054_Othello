package com.example.don.othello;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.don.othello.Game.OthelloSystem;
import com.example.don.othello.GameDataBase.DBAdapter;

import static com.example.don.othello.R.layout.item_layout;


public class HighScores extends ActionBarActivity {

    DBAdapter myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        openDB();



     //   populateListView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_scores, menu);
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

    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }
    private void populateListView(){
        Cursor cursor = myDB.getAllRows();
        String[] fromFieldNames = new String[]{DBAdapter.Column_PlayerNAME, DBAdapter.Column_Score};
        int[] toViewIDs = new int[]{R.id.name,R.id.number};

        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout,cursor,fromFieldNames,toViewIDs,0);

        ListView myList = (ListView) findViewById(R.id.listViewScores);
        myList.setAdapter(myCursorAdapter);
    }

}
