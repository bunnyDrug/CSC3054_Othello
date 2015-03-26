package com.example.don.othello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.don.othello.GameDataBase.DBAdapter;

//import com.example.don.othello.DataBase.DatabaseOperations;
//
public class MenuPage extends ActionBarActivity {
<<<<<<< HEAD
    ImageButton btnstart;
    DBAdapter myDB;
=======
//    private DatabaseOperations DatabaseOperations = null;
//

    Button btnStart;

>>>>>>> origin/master
    // for the start of the game
    Button btnTest;// for the menu/*not made yet*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        //keeps breaking on the open of the db
        openDB();
        preloadValues();
        //this button is a test, to be removed later
        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(v.getContext(),HighScores.class);
                startActivityForResult(test, 0);

            }
        } );


        btnStart = (Button) findViewById(R.id.startButton);
        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                // get the input fields from the menu page
                EditText topEditText = (EditText) findViewById(R.id.player1Edit);
                EditText bottomEditText = (EditText) findViewById(R.id.player2Edit);


                if(TextUtils.isEmpty(topEditText.getText()) || (TextUtils.isEmpty(bottomEditText.getText()))){
                    Toast.makeText(getBaseContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("topPlayer", topEditText.getText().toString());
                    intent.putExtra("bottomPlayer", bottomEditText.getText().toString());
                }

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

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    // Handle action bar item clicks here. The action bar will
    //    // automatically handle clicks on the Home/Up button, so long
    //    // as you specify a parent activity in AndroidManifest.xml.
    //
    //
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menu_Rules:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent intentRule = new Intent(this,RulesPage.class);
                startActivity(intentRule);

                return true;
            case R.id.menu_scores:
                Toast.makeText(this, "Tapped scores",
                        Toast.LENGTH_SHORT).show();
                return true;
<<<<<<< HEAD
//           case R.id.menu_settings:
//               if(item.isChecked())
//                   item.setChecked(false);
//               else
//                   item.setChecked(true);
//               Intent intentSetting = new Intent(MenuPage.this,RulesPage.class);
//               return true;

           case R.id.menu_scores:
               Intent intent = new Intent(this, HighScores.class);
=======
>>>>>>> origin/master
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //database controls
    protected void onRestart(){
        super.onRestart();
    }
    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }
    private void preloadValues(){
        myDB.insertRow("Don",30);
        myDB.insertRow("james",20);


    }
}
