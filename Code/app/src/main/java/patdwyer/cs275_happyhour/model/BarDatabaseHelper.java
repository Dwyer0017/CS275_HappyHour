package patdwyer.cs275_happyhour.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import patdwyer.cs275_happyhour.Bar;

/**
 * Created by patrickdwyer on 8/26/15.
 */
public class BarDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bars.db";

    public BarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createBar(String barName, int rating) {

        SQLiteDatabase db = getWritableDatabase();

        // Create the database row for the bar and keep its unique identifier
        ContentValues barValues = new ContentValues();
        barValues.put(BarContract.BarEntry.COLUMN_NAME_BAR_NAME, barName);
        barValues.put(BarContract.BarEntry.COLUMN_NAME_BAR_RATING, rating);
        db.insert(BarContract.TABLE_NAME, null, barValues);

    }

    public void updateRating(String barName, int rating) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(BarContract.BarEntry.COLUMN_NAME_BAR_RATING, rating);

        db.update(BarContract.TABLE_NAME,
                newValues,
                BarContract.BarEntry.COLUMN_NAME_BAR_NAME +"=?",
                new String[] { String.valueOf(barName) });
    }

    public int getRating(String barName) {

        // Gets the database in the current database helper in read-only mode
        SQLiteDatabase db = getReadableDatabase();

        // After the query, the cursor points to the first database row
        // returned by the request
        Cursor barCursor = db.query(
                BarContract.TABLE_NAME,
                null,
                BarContract.BarEntry.COLUMN_NAME_BAR_NAME + "=?",
                new String[] { String.valueOf(barName) },
                null,
                null,
                null
        );

        barCursor.moveToNext();
        int rating = barCursor.getInt(barCursor.getColumnIndex(BarContract.BarEntry.COLUMN_NAME_BAR_RATING));

        barCursor.close();

        return rating;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database to contain the data for the projects
        db.execSQL(BarContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Logs that the database is being upgraded
        Log.i(BarDatabaseHelper.class.getSimpleName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}
