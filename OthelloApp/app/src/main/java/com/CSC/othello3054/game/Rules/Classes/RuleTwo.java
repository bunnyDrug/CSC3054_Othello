package com.CSC.othello3054.game.Rules.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CSC.othello3054.game.R;


public class RuleTwo extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_two);

        Button btnNext =(Button)findViewById(R.id.btnNex);

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleTwo.this, RuleThree.class);
                startActivity(intent);
            }
        });
    }
}
