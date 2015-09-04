package patdwyer.cs275_happyhour;

import android.content.Context;
import android.util.Log;
import android.widget.SeekBar;

import patdwyer.cs275_happyhour.model.BarDatabaseHelper;

/**
 * Created by patrickdwyer on 8/26/15.
 */
public class RatingBarListener implements SeekBar.OnSeekBarChangeListener {

    private String barName;
    private Context context;
    private BarDatabaseHelper db;
    private BarListAdapter adapter;

    public RatingBarListener(String barName, Context context, BarDatabaseHelper db, BarListAdapter adapter) {
        this.barName = barName;
        this.context = context;
        this.db = db;
        this.adapter = adapter;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            db.updateRating(barName, progress);
            int rating = db.getRating(barName);
            Log.w(barName + " rating: ", Integer.toString(rating));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
