//package com.example.don.othello;
//
//import java.util.List;
//import java.util.Random;
//
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//
//import com.example.don.othello.DataBase.Score;
//import com.example.don.othello.DataBase.ScoreDataSource;
//
//import java.util.List;
//
//
//public class Scores extends ListActivity {
//    private ScoreDataSource dataSource;
//    Button btnView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scores);
//
//        dataSource = new ScoreDataSource(this);
//
//        dataSource.open();
//        List<Score> values = dataSource.getAllScores();
//        // to show in list view
//        ArrayAdapter<Score>adapter = new ArrayAdapter<Score>(this,android.R.layout.simple_list_item_1,values);
//        setListAdapter(adapter);
//      btnView.findViewById(R.id.btnView);
//
//    }
//    public void onClick(View view){
//
//        ArrayAdapter<Score> adapter = (ArrayAdapter<Score>) getListAdapter();
//        Score score =null;
//        switch (view.getId()){
//            case R.id.add:
//                String[] players = new String[]{"Don","joe","Tam"};
//                int[] scores = new int[]{50,35,30};
//
//                int nextInt = new Random().nextInt(3);
//
//                dataSource.createScore(players[nextInt],scores[nextInt]);
//                adapter.add(score);
//                break;
//            case R.id.delete:
//
//
//        }
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_scores, menu);
//        return true;
//    }
//
//
//}
//
