package com.example.don.othello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RuleTwo extends ActionBarActivity {

    Button btnpre,btnnex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_two);

        btnnex =(Button)findViewById(R.id.btnNex);
        btnpre=(Button)findViewById(R.id.btnPre);

        btnnex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleTwo.this, RuleThree.class);
                startActivity(intent);
            }
        });

        btnpre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RuleTwo.this, RulesPage.class);
                startActivity(intent);
            }
        });
    }
}
