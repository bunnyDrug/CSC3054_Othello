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

import com.CSC.othello3054.game.GameDatabase.DBHelper;
import com.CSC.othello3054.game.Rules.Classes.RuleOne;

import java.util.Random;


public class MainMenu extends ActionBarActivity {

    // create an instance of database helper in the
    // initial class for use throughout the app
    private static DBHelper databaseHelper;

    Spinner mySpinner;
    int spinnerSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        // SEE JAVADOC for details.

        // create the database used in the while application.
        // parameters are used for debugging - consider removing as unsafe for
        // release application.
        initDatabase(false, false);

        initTimerSpinner();

        startSpinnerListener();

        // run the click listener for the start game button.
        bntStartGame();
    }

    /**
     * Creates an arrayAdapter which reads in values from an XML file
     * containing a predefined array. This array adapter is set to the spinner
     * allowing it to display these values for selection by the user.
     * <br> array located here - res/values/timerValuesArray.xml
     */
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

    /**
     * Assigns the first character from the spinner to a class variable unless
     * no timer is selected.
     * Due to the way the time is calculated later if a user wishes to have a 50
     * second timer - the number 5 set by this method. Later we multiply this by
     * 10000 to gain 50 seconds in milliseconds.
     *
     * If a user selects no timer - the number 0 is returned
     */
    private void startSpinnerListener() {
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerSelection = Integer.valueOf(mySpinner.getSelectedItem().toString().substring(0, 1));
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
     * Initialises the database.
     * @param wipeDatabase set to true if you wish to remove all values from the
     *                     database.
     * @param populateDummyVal populate the database with 20 dummy values. Each
     *                         value is given a number user name and a random
     *                         score.
     */
    private void initDatabase(boolean wipeDatabase, boolean populateDummyVal){
        // create a new database
        databaseHelper = new DBHelper(getApplicationContext());

        // wipe all data from database (based on parameter...)
        if (wipeDatabase) {
            databaseHelper.onUpgrade(databaseHelper.getWritableDatabase(),1,1);
        }

        // populate with dummy values (based on parameter...)
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

                // Assign the Text elements from the from the menu layout.
                // (user name input fields)
                EditText topEditText = (EditText) findViewById(R.id.player1Edit);
                EditText bottomEditText = (EditText) findViewById(R.id.player2Edit);

                // Validation: Ensure both names are populated before proceeding
                if (TextUtils.isEmpty(topEditText.getText())
                        || (TextUtils.isEmpty(bottomEditText.getText()))) {
                    shortToast("Please enter your name");
                    return;
                } else {
                    // Validation Pass: pass the values of user names and the
                    // spinner(game timer) into the next activity
                    intent.putExtra("topPlayer", topEditText.getText().toString());
                    intent.putExtra("bottomPlayer", bottomEditText.getText().toString());
                    intent.putExtra("spinnerSelection", spinnerSelection);
                }
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Display a short toast to the screen - handy method to avoid clutter
     * @param text string of text to be displayed.
     */
    private void shortToast(String text) {
        Toast.makeText(getBaseContext(), text,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Return an instance of the database.
     * @return the global database used to store scores.
     */
    public static DBHelper getDatabase(){
        return databaseHelper;
    }

    // create and allow for selection of options menus - note other classes do
    // not need or have this code as other screens do not require any options.
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
