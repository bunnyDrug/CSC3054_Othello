package com.example.don.othello.GameDataBase;

/**
 * Created by Chris on 27/03/2015.
 */
public class Score {
    String _player;
    int _score ;
    int _id;
    //empty constructor
   // public score(){}

    //constructor
    public Score(String player, int score){
        this._player = player;
        this._score = score;
    }

    public String get_player() {
        return _player;
    }

    public int get_score() {
        return _score;
    }

    public int get_id() {
        return _id;
    }

    public void set_player(String _player) {
        this._player = _player;
    }

    public void set_score(int _score) {
        this._score = _score;
   }

    public void set_id(int _id) {
        this._id = _id;
    }
}

