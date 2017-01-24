package com.example.jovi.whoisit.persistence;

import android.provider.BaseColumns;

/**
 * Created by jovi on 1/24/2017.
 */

public final class PersonContract {
    //Can't be accidentely created
    private PersonContract(){}

    protected static final String SQL_CREATE_PERSONS = "CREATE TABLE " + personEntry.TABLE_NAME + " ("
            + personEntry._ID + " INTEGER PRIMARY KEY," +
            personEntry.COLUMN_NAME_NAME + " TEXT," +
            personEntry.COLUMN_NAME_PHOTO + " INTEGER," +
            personEntry.COLUMN_NAME_HOBBY + " TEXT," +
            personEntry.COLUMN_NAME_LANGAUGE+ " TEXT," +
            personEntry.COLUMN_NAME_COURSE + " TEXT," +
            personEntry.COLUMN_NAME_AGE + " INTEGER)";

    protected static final String SQL_DELETE_PERSONS = "DROP TABLE IF EXISTS " + personEntry.TABLE_NAME;

    public static class personEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PHOTO = "Photo";
        public static final String COLUMN_NAME_HOBBY = "Hobby";
        public static final String COLUMN_NAME_LANGAUGE = "Favourite_programming_language";
        public static final String COLUMN_NAME_COURSE = "Least_favourite_course";
        public static final String COLUMN_NAME_AGE = "Age";
    }
}
