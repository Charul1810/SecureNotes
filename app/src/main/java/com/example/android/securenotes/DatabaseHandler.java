package com.example.android.securenotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by niharia on 10/09/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "note_manager";

    // Contacts table name
    private static final String TABLE_NOTES = "notes";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_NOTE = "note";

    private static final String KEY_DATE = "date";

    public String getDateTime()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date=new java.util.Date();

        return sdf.format(date);
    }


    //private static final String KEY_DATE="created_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_NOTE + " TEXT "
                + ")";
//@Override
//public void onCreate(SQLiteDatabase db) {
//
//    String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
//            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + KEY_TITLE + " TEXT,"
//            + KEY_NOTE + " TEXT,"
//            + KEY_DATE + " TEXT " + ")";

        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void addNote(note n)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,n.get_title());
        values.put(KEY_NOTE,n.get_note());
     //   values.put(KEY_DATE,getDateTime());
        //long id = db.insert(TABLE_NOTES,null,values);
        db.insert(TABLE_NOTES,null,values);
        db.close();
    }
    // Getting All Contacts
    public List<note> getAllNotes() {
        List<note> noteList = new ArrayList<note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                note n = new note();
                n.set_id(Integer.parseInt(cursor.getString(0)));
                n.set_title(cursor.getString(1));
                n.set_note(cursor.getString(2));
                //n.set_time(new java.sql.Date(cursor.getLong(3)));

                // Adding contact to list
                noteList.add(n);
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }

    // Updating single contact
    public int updateNote(note n) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, n.get_title());
        values.put(KEY_NOTE, n.get_note());

        //************************
//        values.put(KEY_DATE, n.get_created().getTime());
        //************************


        // updating row
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(n.get_id()) });
    }


    // Deleting single contact
    public void deleteNote(note n) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[] { String.valueOf(n.get_id()) });
        db.close();
    }



}
