package com.CSC.othello3054.game.Rules.Class;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.CSC.othello3054.game.R;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag0_rule_Intro;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag1_rule_AimOfTheGame;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag2_rule_PlayingOthello;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag3_rule_BasicRules;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag4_rule_HowToWin;

/**
 * using the fragments and the drawer slider to create a view of the rules and guide lines
 */
public class Rules extends ActionBarActivity {

    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * the on create calls all the methods that where created in this class
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        setInitialFrag();

        initDrawer();

        setDrawerIcon();

        startDrawerClickListener();

    }

    private void startDrawerClickListener() {
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
    /**
     * This method sets the drawer icon, to three bars instead of the basic arrow
     */
    private void setDrawerIcon() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    /**
     * sync the toggle state
     * @param savedInstanceState
     */
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

    /**
     * getting the array highScore_array
     * and creating the drawer slider list view
     */
    private void initDrawer() {
        String[] rulesMenuList = getResources().getStringArray(R.array.rules_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, rulesMenuList));
    }
    /**
     * This sets the fragment that will display of the loading of the activity
     * using the fragment manager is sets the default to the top high scores
     */
    private void setInitialFrag() {
        Fragment fragment1 = new Frag0_rule_Intro();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment1)
                .commit();
    }

    /**
     * once the user clicks on one of the menu items the onItemClickListener checks which
     * one has been picked in what position
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);

            String[] titles = getResources().getStringArray(R.array.rules_array);

            setTitle(titles[position]);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment0 = new Frag0_rule_Intro();
        Fragment fragment1 = new Frag1_rule_AimOfTheGame();
        Fragment fragment2 = new Frag2_rule_PlayingOthello();
        Fragment fragment3 = new Frag3_rule_BasicRules();
        Fragment fragment4 = new Frag4_rule_HowToWin();

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
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment2)
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment3)
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment4)
                        .commit();
                break;
            default:
                break;
        }

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }
}

