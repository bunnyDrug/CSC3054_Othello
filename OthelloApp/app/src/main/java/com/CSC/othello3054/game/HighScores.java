package com.CSC.othello3054.game;


;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

/**
 * Created by Don - 14/04/2015 (my birthday)
 */

public class HighScores extends ActionBarActivity {

    private ListView drawerList;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    // the scores text view on the page.
    TextView txtViewTextScores;

    // get the app database from the main menu.
    DBHelper dbHelper = MainMenu.getDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialise the facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_scores_startup);

        // read the javadoc...
        //setInitial();
        initDrawer();
        setDrawerIcon();
        startDrawerClickListener();

    }
    private void startDrawerClickListener() {
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private void initDrawer() {
        String[] rulesMenuList = getResources().getStringArray(R.array.highScore_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, rulesMenuList));
    }
    private void setInitial()
    {
        setContentView(R.layout.activity_high_scores);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);

            String[] titles = getResources().getStringArray(R.array.highScore_array);

            setTitle(titles[position]);
        }
    }
    private void selectItem(int position) {

        switch (position) {
            case 0:
                setContentView(R.layout.activity_high_scores);
                populateTopListView();
                initFacebookShare();
                break;
            case 1:
                populateListView();
                break;
            default:
                break;
        }

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
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

//    /**
//     * Sets the text of TextView.textScores to the provided string parameter.
//     */
//    public void printScores(String scores){
//        txtViewTextScores = (TextView) findViewById(R.id.textScores);
//        txtViewTextScores.setText(scores);
//    }

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
     * @method to be called in the button of load a new layout All scores
     */

    private void populateListView(){


        Cursor cursor = dbHelper.getAllRows(dbHelper.getReadableDatabase());

            String[] fromTheFields = new String[]{dbHelper.Column_Player, dbHelper.Column_PlayerScore};
            int[] ToTheList = new int[]{R.id.item_Name, R.id.item_score};
            ListView lv =(ListView)findViewById(R.id.myList);
            ListAdapter adapter = new SimpleCursorAdapter(this,R.layout.contents, cursor, fromTheFields, ToTheList,0);
            lv.setAdapter(adapter);

        }

    /**
     *ListView method to show all results of the database
     * Using SimpleCursorAdapter
     * @method to be called in the the page create
     *
     */
    private void populateTopListView(){
       Cursor cursor = dbHelper.topTen(dbHelper.getReadableDatabase());

        String[] fromTheFields = new String[]{dbHelper.Column_Player, dbHelper.Column_PlayerScore};
        int[] ToTheList = new int[]{R.id.item_Name, R.id.item_score};
        ListView lv =(ListView)findViewById(R.id.topList);
        ListAdapter adapter = new SimpleCursorAdapter(this,R.layout.contents, cursor, fromTheFields, ToTheList,0);
        lv.setAdapter(adapter);

    }

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

   }


