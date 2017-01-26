package com.example.jovi.whoisit.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jovi.whoisit.R;
import com.example.jovi.whoisit.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jovi on 1/24/2017.
 */

public class PersonReaderHelper extends SQLiteOpenHelper
{
    //Increment DB_Version on schema change
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "Whoisit.db";
    private static final String TABLE_NAME = PersonContract.personEntry.TABLE_NAME;

    public PersonReaderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Person> getAllPersons()
    {
        ArrayList<Person> personList = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Person p = new Person(cursor.getInt(6), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                personList.add(p);
            } while (cursor.moveToNext());
        }
        return personList;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(PersonContract.SQL_CREATE_PERSONS);
        seedDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(PersonContract.SQL_DELETE_PERSONS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void seedDb(SQLiteDatabase db)
    {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(23, "Silke Venneman",R.drawable.silkevenneman,"Java", "Topics", "Tennis"));
        personList.add(new Person(22, "Alexander Van Damme",R.drawable.alexandervandamme,"Java", "Computernetwerken I", "Chicks aantrekken"));
        personList.add(new Person(21, "Angelo Lebon",R.drawable.angelolebon,"Java", "Analyse", "Lopen"));
        personList.add(new Person(23, "Dennis Noens",R.drawable.dennisnoens,"Java", "Computernetwerken I", "Fotografie"));
        personList.add(new Person(25, "Dries Meert",R.drawable.driesmeert,"Java", "ITalent", "Skateboarding"));
        personList.add(new Person(24, "Elien Callens",R.drawable.eliencallens,"Java", "Computernetwerken I", "Reizen"));
        personList.add(new Person(25, "Eline Snyers", R.drawable.elinesnyers,"Swift", "Webapplicaties", "Tennis"));
        personList.add(new Person(23, "Jeroen Hoste", R.drawable.jeroenhoste,"Java", "i3Talent", "Boardgames"));
        personList.add(new Person(21, "Jonas Walays", R.drawable.jonaswallays,"C#", "Partim Topics", "Badminton"));
        personList.add(new Person(24, "Joris Boschmans", R.drawable.jorisboschmans,"C#", "Financieel Management", "Gitaar"));
        personList.add(new Person(21, "Sofie De Plus", R.drawable.sofiedeplus,"Java", "Analyse", "Voetbal"));
        personList.add(new Person(21, "Robin Malfait", R.drawable.robinmalfait,"Javascript", "Bedrijfsmanagement", "Programmeren"));
        personList.add(new Person(23, "Michaël Verhaeghe", R.drawable.michaelverhaeghe,"Python", "Financieel Management", "Weerkunde"));
        personList.add(new Person(19, "Matthias Seghers", R.drawable.matthiasseghers,"C#", "Partim Topics", "Fitness"));
        personList.add(new Person(23, "Mathias Smet", R.drawable.mathiassmet,"Java", "Databanken", "Voetbal"));
        personList.add(new Person(20, "Manu Schoenmakers", R.drawable.manuschoenmakers,"Java", "ITalent", "Gitaar"));
        personList.add(new Person(19, "Maarten Van Meersche", R.drawable.maartenvanmeersche,"Java", "ITalent", "Gamen"));
        personList.add(new Person(19, "Lennert Hofman", R.drawable.lennerthofman,"Java", "ITalent", "Badminton"));
        personList.add(new Person(21, "Laurens Lavaert", R.drawable.laurenslavaert,"Javascript", "Windows server", "Programmeren"));
        personList.add(new Person(20, "Laura Snyers", R.drawable.laurasnyers,"Java", "ITalent", "Viool"));
        personList.add(new Person(23, "Kas De Durpel", R.drawable.kasdedurpel,"Java", "Computernetwerken", "Volleybal"));
        personList.add(new Person(24, "Joris Boschmans", R.drawable.jorisboschmans,"C#", "Financieel Management", "Gitaar"));

        for (Person p : personList) {
            db.execSQL("INSERT INTO " + TABLE_NAME +" ("
                    + PersonContract.personEntry.COLUMN_NAME_NAME + ", "
                    + PersonContract.personEntry.COLUMN_NAME_PHOTO + ", "
                    + PersonContract.personEntry.COLUMN_NAME_COURSE + ", "
                    + PersonContract.personEntry.COLUMN_NAME_LANGAUGE + ", "
                    + PersonContract.personEntry.COLUMN_NAME_AGE + ", "
                    + PersonContract.personEntry.COLUMN_NAME_HOBBY + ")"
                    + " values (\""
                    + String.valueOf(p.name)
                    + "\", \"" + Integer.valueOf(p.picture)
                    + "\", \"" + String.valueOf(p.hateCourse) + "\", \""
                    + String.valueOf(p.favoLanguage) + "\", \""
                    + Integer.valueOf(p.age) + "\", \""
                    + String.valueOf(p.hobby) + "\");");
        }
    }
}
