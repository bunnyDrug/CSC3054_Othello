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
import com.CSC.othello3054.game.Rules.RuleFragments.Frag_0_rule_Intro;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag_1_rule_AimOfTheGame;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag_2_rule_PlayingOthello;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag_3_rule_BasicRules;
import com.CSC.othello3054.game.Rules.RuleFragments.Frag_4_rule_HowToWin;

/**
 * class Rules
 * extends ActionBarActivity
 * using the fragments and the drawer slider to create a view of the rules and guide lines
 * get methods to create the rules view
 */
public class Rules extends ActionBarActivity {
    /**
     * variables required to create the Drawer slider
     */
    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * onCreate
     * the on create calls all the methods that where created in this class
     * setInitialFrag to get the content layout that is run at activity startup
     * set the drawer icon
     * start the Drawer click event
     *
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

    /**
     * startDrawerClickListener
     *
     * sets the click listener for the drawerList
     * which calls in the @method DrawerItemClickListener
     */
    private void startDrawerClickListener() {
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
    /**
     * setDrawerIcon
     * drawer layout gets the Score_drawer_layout and creates it
     * In this method it sets the drawer icon, to three bars instead of the basic arrow
     * calls the @super to get the drawer open and close
     * sets toggle as the DrawerListener
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
     * onPostCreate
     *
     * sets the mDrawerToggle syncState after the DrawerLayout's instance state has been
     * restored
     *
     * @param savedInstanceState stores the previous state
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    /**
     * onConfigurationChanged
     *
     * sets the mDrawerToggle to the new Configuration from the private created actionBar
     * @param newConfig overdrives the mDrawerToggle
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    /**
     * OnOptionsItemSelected
     * Pass the event to ActionBarDrawerToggle, if it returns
     * true, then it has handled the app icon touch event
     *
     * @param item to be pass into the next method
     * @return true if the item is selected
     * @return onOptionsItemSelected(item) of mDrawerToggle
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     *initDrawer
     * Method called in onCreate
     * getting the array highScore_array
     * and creating the drawer slider list view
     * uses the ArrayAdapter to get the list of items to be stored in the slider drawer
     */
    private void initDrawer() {
        String[] rulesMenuList = getResources().getStringArray(R.array.rules_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, rulesMenuList));
    }
    /**
     * setInitialFrag
     * Method called in onCreate
     * This sets the fragment that will display of the loading of the activity
     * using the fragment manager is sets the default to the Introduction fragment
     */
    private void setInitialFrag() {
        Fragment fragment1 = new Frag_0_rule_Intro();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment1)
                .commit();
    }

    /**
     * DrawerItemClickListener
     * implements android.widget.ListView to get the event click
     * method called in onCreate
     * This method is called once a user clicks on one of the menu items the onItemClickListener,
     * checks which one has been picked in what position setsTitle to the position past in
     *
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
     * SelectItem
     * This method is to get the value of what is being selected in the drawer
     * declares new variables of the fragments to use within a case statement
     * the case statement then selects which fragement will displyed depend on the position passed in
     * @param position Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment0 = new Frag_0_rule_Intro();
        Fragment fragment1 = new Frag_1_rule_AimOfTheGame();
        Fragment fragment2 = new Frag_2_rule_PlayingOthello();
        Fragment fragment3 = new Frag_3_rule_BasicRules();
        Fragment fragment4 = new Frag_4_rule_HowToWin();

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

