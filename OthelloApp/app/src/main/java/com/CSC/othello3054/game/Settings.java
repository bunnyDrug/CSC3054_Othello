package com.CSC.othello3054.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.Toast;

import com.CSC.othello3054.game.ThemeControls.themeUtil;




public class Settings extends ActionBarActivity {
    boolean checked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        themeUtil.onCreateSetTheme(this);



}
    public void onClickbtnGreen(View view){
            ImageView greenP = (ImageView)findViewById(R.id.piece);
            greenP.setImageResource(R.drawable.green_disk);

            //Toast.makeText(this,"Didnt work",Toast.LENGTH_SHORT).show();
    }

    public void onClickbtnBlack(View view){
        ImageView blackp = (ImageView)findViewById(R.id.piece);
        blackp.setImageResource(R.drawable.black_disk);
    }

    public void onClickNormalTheme(View view) {
        themeUtil.changeToTheme(this, themeUtil.Original);

    }

    public void onClickBlackTheme(View view) {
      themeUtil.changeToTheme(this, themeUtil.BLACK);
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(this, R.style.BlackTheme);
        LayoutInflater layoutInflater = LayoutInflater.from(themeWrapper);
        layoutInflater.inflate(R.layout.activity_settings, null,true);
    }

    public void onClickWhiteTheme(View view) {
        themeUtil.changeToTheme(this, themeUtil.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }
}


