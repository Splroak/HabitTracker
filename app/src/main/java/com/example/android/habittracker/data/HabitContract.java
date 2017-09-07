package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Splroak on 9/7/2017.
 */

public class HabitContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    //Inner class to define the properties of the habit
    public static final class HabitEntry implements BaseColumns {
        //Name of the database
        public final static String TABLE_NAME = "habit";

        //ID number for the habit
        //Type: INTEGER
        public final static String _ID = BaseColumns._ID;

        //Name of the habit
        public final static String COLUMN_HABIT_NAME = "name";

        //Duration of the habit
        public final static String COLUMN_HABIT_DURATION = "duration";

        //Status of the habit, indicate that if it is too much,
        //too little or just right duration-wise.
        public final static String COLUMN_HABIT_STATUS = "status";

        //Posibility of status column
        public final static int STATUS_TOO_MUCH = 0;
        public final static int STATUS_TOO_LITTLE = 1;
        public final static int STATUS_JUST_RIGHT = 2;
    }

}
