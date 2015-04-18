package com.CSC.othello3054.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.CSC.othello3054.game.Rules.Classes.RuleOne;

import java.util.Random;


public class MainMenu extends ActionBarActivity {

    private static DBHelper databaseHelper;
    Spinner mySpinner;
    int spinnerSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        // create the database used in the while application.
        initDatabase(false, false);

        initTimerSpinner();
        startSpinnerListener();


        // run the click listener for the start game button.
        bntStartGame();

    }

    private void initTimerSpinner() {
        mySpinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.timer_values_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        // Apply the adapter to the spinner
        mySpinner.setAdapter(adapter);

    }

    private void startSpinnerListener() {
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerSelection = Integer.valueOf(mySpinner.getSelectedItem().toString().substring(0,1));
                } else {
                    spinnerSelection = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Initialises the database, parameters allow for wiping it and removing all
     * user data.
     */
    private void initDatabase(boolean wipeDatabase, boolean populateDummyVal){
        // create a new database
        databaseHelper = new DBHelper(getApplicationContext());

        // wipe all data from database (if true)
        if (wipeDatabase) {
            databaseHelper.onUpgrade(databaseHelper.getWritableDatabase(),1,1);
        }

        if (populateDummyVal) {
            Random test = new Random();
            for (int i = 0; i < 20; i++) {
                databaseHelper.add("User " + (i + 1), (test.nextInt(100)), databaseHelper.getWritableDatabase());
            }
        }
    }

    /**
     * Displays the game board. Takes the values typed in to the text fields and
     * passes them across to the new activity. Validation is active on empty
     * fields.
     */
    private void bntStartGame() {
        Button btnStart = (Button) findViewById(R.id.startButton);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OthelloGame.class);

                // get the input fields from the menu page
                EditText topEditText = (EditText) findViewById(R.id.player1Edit);
                EditText bottomEditText = (EditText) findViewById(R.id.player2Edit);

                if (TextUtils.isEmpty(topEditText.getText()) || (TextUtils.isEmpty(bottomEditText.getText()))) {
                    Toast.makeText(getBaseContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("topPlayer", topEditText.getText().toString());
                    intent.putExtra("bottomPlayer", bottomEditText.getText().toString());
                    intent.putExtra("spinnerSelection", spinnerSelection);
                }
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Return an instance of the database.
     * @return the global database used to store scores.
     */
    public static DBHelper getDatabase(){
        return databaseHelper;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menu_Rules:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intentRule = new Intent(this,RuleOne.class);
                startActivity(intentRule);
                return true;

            case R.id.menu_scores:

                Intent intent = new Intent(MainMenu.this, HighScores.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
