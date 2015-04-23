package com.CSC.othello3054.game;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

/**
 * Created by Don - 14/04/2015 (my birthday)
 */

public class HighScores extends ActionBarActivity {

    // the scores text view on the page.
    TextView txtViewTextScores;

    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialise the facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_high_scores);

        // read the javadoc...

        printScores(getScores());
        populateListView();


        initFacebookShare();
    }

    /**
     * Builds up a content Class to allow score sharing to a users Facebook
     * timeline.<br>
     * If no scores are available in the database, the share button is disabled.
     * The facebook link shared is the public facing GitHub Pages web page for
     * the project. It offers APK and source code downloads via a user
     * friendly presentation/interface
     */
    private void initFacebookShare() {
        ShareButton shareButton = (ShareButton)findViewById(R.id.share_view);

        if (getScores().equals("No Scores to display")){
            shareButton.setEnabled(false);
        } else {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://bunnydrug.github.io/CSC3054_Othello/"))
                    .setContentTitle("Othello - Top 5 High Scores")
                    .setContentDescription("Can you beat my top scores?\n" + getScores())
                    .build();

            shareButton.setShareContent(content);
        }
    }

    /**
     * Sets the text of TextView.textScores to the provided string parameter.
     */
    public void printScores(String scores){
        txtViewTextScores = (TextView) findViewById(R.id.textScores);
        txtViewTextScores.setText(scores);
    }

    /**
     * Gets the top 5 scores from the database of players returned as a string.
     * If there are no scores to return, an error string is return but no
     * exception thrown.
     * @return String of scores from the database.
     */
    private String getScores() {
        String scores;
        if (dbHelper.topFivePlayersToString(dbHelper.getWritableDatabase()).equals("")) {
            scores = "No Scores to display";
        } else {
            scores = dbHelper.topFivePlayersToString(dbHelper.getReadableDatabase());
        }
        return scores;
    }
    /**
     * ListView method to show all results of the database
     * Using SimpleCursorAdapter
     *
     */

    private void populateListView(){


        Cursor cursor = dbHelper.getAllRows(dbHelper.getReadableDatabase());

            String[] fromTheFields = new String[]{dbHelper.Column_Player, dbHelper.Column_PlayerScore};
            int[] ToTheList = new int[]{R.id.item_Name, R.id.item_score};
            ListView lv =(ListView)findViewById(R.id.myList);
            ListAdapter adapter = new SimpleCursorAdapter(this,R.layout.contents, cursor, fromTheFields, ToTheList,0);
            lv.setAdapter(adapter);



        }


    }


