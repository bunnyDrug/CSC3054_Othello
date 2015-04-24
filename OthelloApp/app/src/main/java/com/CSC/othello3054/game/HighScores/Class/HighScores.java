package com.CSC.othello3054.game.HighScores.Class;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.CSC.othello3054.game.HighScores.HighScoresFragments.Frag0_higScores_TopScores;
import com.CSC.othello3054.game.HighScores.HighScoresFragments.Frag1_higScores_AllScores;
import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;
import com.facebook.FacebookSdk;

;

/**
 * Created by Don - 14/04/2015 (my birthday)
 */

public class HighScores extends ActionBarActivity {


    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();

    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // read the javadoc...

        // initialise the facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_scores);

        setInitialFrag();

        initDrawer();

        setDrawerIcon();

        startDrawerClickListener();

        //populateListView();

        //populateTopListView();
    }

    private void startDrawerClickListener() {
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private void setDrawerIcon() {
        drawerLayout = (DrawerLayout) findViewById(R.id.scores_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void initDrawer() {
        String[] rulesMenuList = getResources().getStringArray(R.array.highScore_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.scores_drawer_layout);
        drawerList = (ListView) findViewById(R.id.scores_left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, rulesMenuList));
    }

    private void setInitialFrag() {
        Fragment fragment1 = new Frag0_higScores_TopScores();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment1)
                .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);

            String[] titles = getResources().getStringArray(R.array.highScore_array);

            setTitle(titles[position]);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        Fragment fragment0 = new Frag0_higScores_TopScores();
        Fragment fragment1 = new Frag1_higScores_AllScores();


        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment0)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment1)
                        .commit();
                break;
            default:
                break;
        }

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }




//    /**
//     * Sets the text of TextView.textScores to the provided string parameter.
//     */
//    public void printScores(String scores){
//        txtViewTextScores = (TextView) findViewById(R.id.textScores);
//        txtViewTextScores.setText(scores);
//    }






}


