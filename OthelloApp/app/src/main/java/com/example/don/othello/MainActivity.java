package com.example.don.othello;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    // new grid view
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: remember to change this back to the main activity after testing
        setContentView(R.layout.activity_main);

        // create a new grid and populate it
        testGrid();

        // colour the grind with the 100 shade orange
        gridView.setBackgroundColor(Color.parseColor("#FFE0B2"));

        // start click listener
        setOnClickListener();

    }

    public void testGrid() {
        populateGridView();
    }

    /**
     * Creates a new grad view and displays it to the screen<br>
     * The grid is populated with numerical values from 0 to 64.
     */
    private void populateGridView() {
        // create an array of 64 Strings
        // Elements go from "0" to "63"
        String[] gridFill = new String[64];
        for (int i = 0; i<gridFill.length; i++) {
            gridFill[i] = "o";
        }

        // assign the grid view variable to the grid view on the layout
        gridView = (GridView) findViewById(R.id.gridView1);

        // creates an adapter and fills it with the array created
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, gridFill);
        gridView.setAdapter(adapter);
    }

    public void setOnClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getBaseContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.game_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
