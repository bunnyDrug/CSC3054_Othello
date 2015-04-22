package com.CSC.othello3054.game.Rules.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;

public class RuleThree extends ActionBarActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_three);
/*
Button to return to the main menu page instead of hitting the back button 4 times
 */


        home = (Button)findViewById(R.id.btnHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleThree.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });


    //    no need for a previous button as we should be making use of the
    //    android design documents and utilising the back button

    }
}
