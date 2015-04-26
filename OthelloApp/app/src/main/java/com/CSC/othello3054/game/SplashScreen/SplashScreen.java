package com.CSC.othello3054.game.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.CSC.othello3054.game.MainMenu;
import com.CSC.othello3054.game.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * creating a splash screen for the app
 * it uses a timer to go to the next activity
 * this to run at app load so the user sees this screen first
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(this, MainMenu.class);
/**
 * the timer TimeTask to count the time before jumping to the next activity "MainMenu"
 */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        Timer splash = new Timer();
        splash.schedule(task,3000);

    }


}
