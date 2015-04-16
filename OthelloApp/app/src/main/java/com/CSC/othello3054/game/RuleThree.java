package com.CSC.othello3054.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RuleThree extends ActionBarActivity {
        Button pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_three);
        pre= (Button)findViewById(R.id.btnPre3);

        pre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleThree.this, RuleTwo.class);
                startActivity(intent);
            }
        });
    }
}
