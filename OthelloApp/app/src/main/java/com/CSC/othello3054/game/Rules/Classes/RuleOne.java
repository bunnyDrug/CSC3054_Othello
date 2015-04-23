package com.CSC.othello3054.game.Rules.Classes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.CSC.othello3054.game.Rules.RuleFragments.FragRule1;
import com.CSC.othello3054.game.Rules.RuleFragments.FragRule2;
import com.CSC.othello3054.game.Rules.RuleFragments.FragRule3;
import com.CSC.othello3054.game.Rules.RuleFragments.FragRule4;
import com.CSC.othello3054.game.Rules.RuleFragments.FragRule5;

public class RuleOne extends ActionBarActivity {

    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_one);

        setInitialFrag();

        initDrawer();

        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

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
        String[] rulesMenuList = getResources().getStringArray(R.array.rules_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, rulesMenuList));
    }

    private void setInitialFrag() {
        Fragment fragment1 = new FragRule1();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment1)
                .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment1 = new FragRule1();
        Fragment fragment2 = new FragRule2();
        Fragment fragment3 = new FragRule3();
        Fragment fragment4 = new FragRule4();
        Fragment fragment5 = new FragRule5();

        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment1)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment2)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment3)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment4)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment5)
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

