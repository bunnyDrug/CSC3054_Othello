package com.example.don.othello.DataBase;

/**
 * Created by Chris on 16/03/2015.
 */
public class Score {
    private long id;
    private String player;
    private int score;

    public long getId()
    {return id;}

    public void setId(long id){
        this.id =id;
    }
    public String getPlayer(){
        return player;
    }
    public int getScore(){
        return score;
    }
    public void setPlayer(String player){
        this.player = player;
    };
    public void setScore(int score){
        this.score = score;
    }
    //will be used by the ArrayAdapter in ListView
    @Override
    public String toString(){
        return player;

    }


}
