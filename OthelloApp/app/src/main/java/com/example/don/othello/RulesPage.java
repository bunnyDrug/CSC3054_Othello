package com.example.don.othello;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.don.othello.Frags.FragRule1;
import com.example.don.othello.Frags.FragRule2;
import com.example.don.othello.Frags.FragRule3;

public class RulesPage extends ActionBarActivity {
Button btnRule1,btnRule2,btnRule3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_page);

        btnRule1.findViewById(R.id.btnRuleOne);
        btnRule2.findViewById(R.id.btnRuleTwo);
        btnRule3.findViewById(R.id.btnRuleThree);

        btnRule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM =getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FragRule1 F1 = new FragRule1();
                FT.add(R.id.fragOne,F1);
                FT.addToBackStack("F1");
                FT.commit();
            }
        });

        btnRule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM =getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FragRule2 F2 = new FragRule2();
                FT.add(R.id.fragTwo,F2);
                FT.addToBackStack("F2");
                FT.commit();
            }
        });

        btnRule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FM =getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FragRule3 F3 = new FragRule3();
                FT.add(R.id.fragThree,F3);
                FT.addToBackStack("F3");
                FT.commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rules_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
