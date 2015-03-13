package com.example.don.othello;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;

public class MenuPage extends ActionBarActivity {
    ImageButton btnstart;
    // for the start of the game
    Button btnDots;// for the menu/*not made yet*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        btnstart = (ImageButton) findViewById(R.id.startButton);
        btnstart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                // get the edit texts from the menu page
                EditText topEditText = (EditText) findViewById(R.id.player1Edit);
                EditText bottomEditText = (EditText) findViewById(R.id.player2Edit);
                // add them into two extras used to pass stuff between activities
                intent.putExtra("topPlayer", topEditText.getText().toString());
                intent.putExtra("bottomPlayer", bottomEditText.getText().toString());
                startActivityForResult(intent, 0);

            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.menu_Rules:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intentRule = new Intent(this,RulesPage.class);
                startActivityForResult(intentRule,0);
                return true;
//           case R.id.menu_settings:
//               if(item.isChecked())
//                   item.setChecked(false);
//               else
//                   item.setChecked(true);
//               Intent intentSetting = new Intent(MenuPage.this,RulesPage.class);
//               return true;

//           case R.id.menu_scores:
//               if(item.isChecked())
//                   item.setChecked(false);
//               else
//                   item.setChecked(true);
//               Intent intentScore = new Intent(MenuPage.this,RulesPage.class);
//               return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
