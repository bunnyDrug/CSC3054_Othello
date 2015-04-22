package com.CSC.othello3054.game.Comments;

/*
 * java class to get and set the comments
 * also to house the constructor methods for a comment
 */

public class comment {

    private int _id;
    private String _comment;

    public comment(){}

    public comment(String comment){
        this._comment = comment;
    }

    public int get_id() {
        return _id;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }
}
