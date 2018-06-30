package com.mtandev.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       try {

           SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
           sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4) )");
           sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('First programmable computer created', 1936)");
           sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('MITS Altair 8800 the first personal computer ', 1975)");
           sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('The Year of the Computer', 1982)");
           sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('The Macintosh Portable', 1989)");
           sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('Microsoft Windows 95', 1995)");

           Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM events", null);
           int nameIndex = c.getColumnIndex("name");
           int yearIndex = c.getColumnIndex("year");
           c.moveToFirst();

           while (c != null) {
               Log.i("Results - name", c.getString(nameIndex));
               Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));

               c.moveToNext();
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
