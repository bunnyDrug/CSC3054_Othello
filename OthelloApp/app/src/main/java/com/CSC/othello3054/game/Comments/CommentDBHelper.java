package com.CSC.othello3054.game.Comments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
 * Another dbhelper for the comments review
 * this second one was created to keep the code clean as didn't want too much in the one method
 */
public class CommentDBHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Othello.db";

    private static final String TABLE_NAME = "OthelloComments";
    private static final String Column_id = "_id";
    private static final String Column_Comment = "comment";

    public CommentDBHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int verison){

    }

}
