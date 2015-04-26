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
public final class Frag1_higScores_AllScores extends Fragment {
    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();


    public Frag1_higScores_AllScores() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scores_fragment_01, container, false);

        populateListView(rootView);

        return rootView;
    }



    /**
     * ListView method to show all results of the database
     * Using SimpleCursorAdapter
     * @method to be called in the button of load a new layout All scores
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
