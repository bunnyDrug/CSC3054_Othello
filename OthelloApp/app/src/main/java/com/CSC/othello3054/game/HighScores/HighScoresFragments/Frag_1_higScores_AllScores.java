package com.CSC.othello3054.game.HighScores.HighScoresFragments;

import android.app.Fragment;
import android.database.Cursor;
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
public final class Frag_1_higScores_AllScores extends Fragment {
    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();

    /**
     * Frag0_higScores_TopScores()
     * empty constructor
     *
     */
    public Frag_1_higScores_AllScores() {

    }

    /**
     * This method sets the rootView to the fragment of top scores into the to be false
     * calls in the populateListView method on the activity load
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return rootView  the layout of All Scores
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scores_fragment_01, container, false);

        populateListView(rootView);

        return rootView;
    }


    /**
     * populateListView
     * takes the String fromTheFields to get the two columns from the database that we want
     * places them into the two layout items that we want by using the toTheList array
     * listAdapter creates new SimpleCursorAdapter to place the string values in the layout values
     * then sets them into the list view
     * @param view
     */
    private void populateListView(View view){

        Cursor cursor = dbHelper.getAllRows(dbHelper.getReadableDatabase());

        String[] fromTheFields = new String[]{DBHelper.Column_Player, DBHelper.Column_PlayerScore};
        int[] ToTheList = new int[]{R.id.item_Name, R.id.item_score};
        ListView lv =(ListView) view.findViewById(R.id.allScoresList);
        ListAdapter adapter = new SimpleCursorAdapter(view.getContext(),R.layout.score_table_contents, cursor, fromTheFields, ToTheList, 0);
        lv.setAdapter(adapter);

    }
}
