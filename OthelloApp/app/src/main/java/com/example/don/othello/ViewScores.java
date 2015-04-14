package com.example.don.othello;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.don.othello.GameDataBase.DBHelper;

public class ViewScores extends ActionBarActivity {

    TextView txtViewTextScores;
    DBHelper dbHelper = MenuPage.getDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        txtViewTextScores = (TextView) findViewById(R.id.textScores);

        printScores();
    }

    /**
     * Prints the database scores to the screen.
     */
    public void printScores(){
        if (dbHelper.databaseToString(dbHelper.getWritableDatabase()).equals("")) {
            txtViewTextScores.setText("No Scores to display");
        } else {
            txtViewTextScores.setText(dbHelper.databaseToString(dbHelper.getReadableDatabase()));
        }
    }
}