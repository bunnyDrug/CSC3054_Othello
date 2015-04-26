package com.CSC.othello3054.game.HighScores.HighScoresFragments;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.CSC.othello3054.game.HighScoresDatabase.DBHelper;
import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

/**
 * Created by Chris on 12/03/2015.
 */

/**
 * public class Frag0_higScores_TopScores
 * extends Fragment
 * gets the layout view to be set once called in the highScores drawer slider
 * gets database methods to populate the highScores in list Views
 * gets the method for sharing to facebook
 */
public final class Frag0_higScores_TopScores extends Fragment {
    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();

    /**
     * Frag0_higScores_TopScores()
     * empty constructor
     *
     */
    public Frag0_higScores_TopScores() {
    }

    /**
     * onCreateView
     * This method sets the rootView to the fragment of top scores into the to be false
     * calls in the initFacebookShare
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return rootView the layout of topScores
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scores_fragment_00, container, false);


        initFacebookShare(rootView);

        populateTopListView(rootView);

        return rootView;
    }

    /**
     * Builds up a content Class to allow score sharing to a users Facebook
     * timeline.<br>
     * If no scores are available in the database, the share button is disabled.
     * The facebook link shared is the public facing GitHub Pages web page for
     * the project. It offers APK and source code downloads via a user
     * friendly presentation/interface
     */
    private void initFacebookShare(View view) {

        ShareButton shareButton = (ShareButton) view.findViewById(R.id.share_view);

        if (getScores().equals("No Scores to display")){
            shareButton.setEnabled(false);
        } else {
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://bunnydrug.github.io/CSC3054_Othello/"))
                    .setContentTitle("Othello - Top Score")
                    .setContentDescription("Can you beat my top score?\n" + getScores())
                    .build();

            shareButton.setShareContent(content);
        }
    }

    /**
     * Gets the top 5 scores from the database of players returned as a string.
     * If there are no scores to return, an error string is return but no
     * exception thrown.
     * @return String of scores from the database.
     */
    private String getScores() {
        String scores;
        if (dbHelper.topPlayerToStringFBSHARE(dbHelper.getWritableDatabase()).equals("")) {
            scores = "No Scores to display";
        } else {
            scores = dbHelper.topPlayerToStringFBSHARE(dbHelper.getReadableDatabase());
        }
        return scores;
    }

    /**
     *Gets the top 10 scores from the database of players set into a listView
     * takes the String fromTheFields to get the two columns from the database that we want
     * places them into the two layout items that we want by using the toTheList array
     * listAdapter creates new SimpleCursorAdapter to place the string values in the layout values
     * then sets them into the list view
     * @param view
     */
    private void populateTopListView(View view){
        Cursor cursor = dbHelper.topTen(dbHelper.getReadableDatabase());

        String[] fromTheFields = new String[]{DBHelper.Column_Player, DBHelper.Column_PlayerScore};
        int[] ToTheList = new int[]{R.id.item_Name, R.id.item_score};
        ListView lv =(ListView) view.findViewById(R.id.topList);
        ListAdapter adapter = new SimpleCursorAdapter(view.getContext(), R.layout.score_table_contents, cursor, fromTheFields, ToTheList,0);
        lv.setAdapter(adapter);
    }
}
