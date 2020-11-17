package com.example.jeudesmots;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_Manager extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "GuessGame.db";
    private static final int DATABASE_VERSION = 1;

    public DB_Manager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<String>getList(String categorie){
        List<String> list = new ArrayList<String>();

        String statement = " Select word from Word where category = '"+categorie+"' ";

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery(statement, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public joueur getPlayer(){
        joueur player = new joueur() ;

        String statement = " Select * from Player where id = 1 ";

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery(statement,null);

        if(cursor.moveToFirst()){
            do{
                player.setName(cursor.getString(cursor.getColumnIndex("name")));
                player.setScore(cursor.getInt(cursor.getColumnIndex("score")));
               // player.setLevel(cursor.getInt(cursor.getColumnIndex("level")));
            }while (cursor.moveToNext());
        }

        return player;
    }
    public void update(int score, int level){

        String strSQL = "UPDATE Player SET score = "+score+" WHERE id = 1";
        SQLiteDatabase myDB = this.getReadableDatabase();
        myDB.execSQL(strSQL);

        strSQL = "UPDATE Player SET level = "+level+" WHERE id = 1";
        myDB = this.getReadableDatabase();
        myDB.execSQL(strSQL);
    }
}
