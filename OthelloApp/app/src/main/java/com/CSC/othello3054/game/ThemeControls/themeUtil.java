package com.CSC.othello3054.game.ThemeControls;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.CSC.othello3054.game.R;

public class themeUtil {

    public static int cTheme;
    public final static int WHITE = 1;
    public final static int BLACK = 2;
    public final static int Original = 0;

    public static void changeToTheme(Activity activity, int Theme){
        cTheme = Theme;
        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

/*
 *A method to switch between themes on the activity its called in,
 *
 */
    public static void onCreateSetTheme(Activity activity)
    {

        switch (cTheme)
        {
            default:

            case Original:
                activity.setTheme(R.style.AppTheme);
                break;
            case WHITE:
                activity.setTheme(R.style.WhiteTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.BlackTheme);
                break;
//            // to add more themes add cases



        }
    }
}





