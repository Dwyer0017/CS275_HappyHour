package patdwyer.cs275_happyhour.model;

import android.provider.BaseColumns;

/**
 * Created by patrickdwyer on 8/26/15.
 */
public class BarContract {

    public static final String TABLE_NAME = "bar_ratings";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "
            + BarContract.TABLE_NAME + " ("
            + BarContract.BarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BarContract.BarEntry.COLUMN_NAME_BAR_NAME + " TEXT,"
            + BarContract.BarEntry.COLUMN_NAME_BAR_RATING + " INTEGER);";

    public static abstract class BarEntry implements BaseColumns {
        public static final String COLUMN_NAME_BAR_NAME = "bar_name";
        public static final String COLUMN_NAME_BAR_RATING = "bar_rating";
    }

}
