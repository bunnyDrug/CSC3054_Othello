package com.CSC.othello3054.game.Rules.Classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CSC.othello3054.game.R;

public class RuleOne extends ActionBarActivity {
Button btnRule1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_page);

        btnRule1 = (Button) findViewById(R.id.btnRuleOne);


                    btnRule1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RuleOne.this, RuleTwo.class);
                        startActivity(intent);
                }
            });



    }

}