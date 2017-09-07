package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitDbHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDatabaseInfo();
    }
    private Cursor readFromDatabase(){
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_STATUS,
        };
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,
                null,
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(cursor);
        return cursor;
    }
    public void displayDatabaseInfo() {
        Cursor cursor = readFromDatabase();

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habits table in the database).
            TextView displayView = (TextView) findViewById(R.id.habit_text_view);
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_DURATION + " - " +
                    HabitEntry.COLUMN_HABIT_STATUS + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int statusColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STATUS);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDuration = cursor.getString(durationColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDuration + " - " +
                        currentStatus));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertHabit() {
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Lying in bed");
        values.put(HabitEntry.COLUMN_HABIT_DURATION, "2h30'");
        values.put(HabitEntry.COLUMN_HABIT_STATUS, HabitEntry.STATUS_TOO_LITTLE);
        //Actually insert a new row
        db.insert(HabitEntry.TABLE_NAME, null, values);
        displayDatabaseInfo();
    }
}
