package com.CSC.othello3054.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.CSC.othello3054.game.GameDataBase.DBHelper;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class ViewScores extends ActionBarActivity {

    TextView txtViewTextScores;
    DBHelper dbHelper = MenuPage.getDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_high_scores);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://bunnydrug.github.io/CSC3054_Othello/"))
                .setContentTitle("Othello - Top 5 High Scores")
                .setContentDescription("Can you beat my top scores?\n" + getScores())
                .build();

        ShareButton shareButton = (ShareButton)findViewById(R.id.share_view);
        shareButton.setShareContent(content);

        printScores(getScores());
    }

    /**
     * Prints the database scores to the screen.
     */
    public void printScores(String scores){
        txtViewTextScores = (TextView) findViewById(R.id.textScores);
        txtViewTextScores.setText(scores);
    }

    private String getScores() {
        String scores;
        if (dbHelper.databaseToString(dbHelper.getWritableDatabase()).equals("")) {
            scores = "No Scores to display";
        } else {
            scores = dbHelper.databaseToString(dbHelper.getReadableDatabase());
        }
        return scores;
    }

    public void share (View view)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Ive just completed True Movies! You should try!";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}

