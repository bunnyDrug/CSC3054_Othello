package com.CSC.othello3054.game.SpalshScreen;

import com.CSC.othello3054.game.MainMenu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.CSC.othello3054.game.R;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(this, MainMenu.class);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        Timer splash = new Timer();
        splash.schedule(task,7000);



    }


}
