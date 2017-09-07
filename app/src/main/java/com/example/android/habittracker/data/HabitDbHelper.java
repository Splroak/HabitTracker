package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Splroak on 9/7/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    //Name of the database file
    private static final String DATABASE_NAME = "habit.db";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Construct new instance
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //This String is used to create SQL database
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE "
                + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION + " TEXT, "
                + HabitEntry.COLUMN_HABIT_STATUS + " INTEGER NOT NULL DEFAULT 2);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }
    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
