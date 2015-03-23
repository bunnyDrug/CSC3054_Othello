package com.example.don.othello.SpalshScreen;

import com.example.don.othello.MenuPage;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.don.othello.R;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(this, MenuPage.class);

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
