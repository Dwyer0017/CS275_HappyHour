package patdwyer.cs275_happyhour;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import patdwyer.cs275_happyhour.model.BarDatabaseHelper;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class BarListAdapter extends ArrayAdapter<Bar> {

    private BarDatabaseHelper db = new BarDatabaseHelper(this.getContext());

    public BarListAdapter(Context context, int resource, ArrayList<Bar> bars) {
        super(context, resource, bars);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        TextView locationView = (TextView) convertView.findViewById(R.id.location);
        TextView distanceView = (TextView) convertView.findViewById(R.id.distance);
        TextView ratingView = (TextView) convertView.findViewById(R.id.rating);
        final TextView userRatingView = (TextView) convertView.findViewById(R.id.userRating);
        SeekBar ratingBar = (SeekBar) convertView.findViewById(R.id.ratingBar);

        String barName = this.getItem(position).getName();
        nameView.setText(barName);
        locationView.setText(this.getItem(position).getAddress() + ", " + this.getItem(position).getCity() + ", " + this.getItem(position).getState());
        distanceView.setText("Distance: " + Integer.toString(this.getItem(position).getDistance()) + " meters");
        ratingView.setText("Yelp Rating: " + Integer.toString(this.getItem(position).getRating()) + "/5");

        int rating = 0;

        try {
            rating = db.getRating(barName);
        } catch (Exception e) {
            Log.e("NO RECORD", e.getMessage());
            db.createBar(this.getItem(position).getName(), rating);
        }

        userRatingView.setText("My Rating: "+ rating +"/5");

        ratingBar.setProgress(rating);
        RatingBarListener listener = new RatingBarListener(barName, getContext(), db, this);
        ratingBar.setOnSeekBarChangeListener(listener);


        return convertView;

    }

}
