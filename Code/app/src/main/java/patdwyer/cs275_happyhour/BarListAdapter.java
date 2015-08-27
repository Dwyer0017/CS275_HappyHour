package patdwyer.cs275_happyhour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class BarListAdapter extends ArrayAdapter<Bar> {

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
        //ImageView barPicView = (ImageView) convertView.findViewById(R.id.pic);
        TextView locationView = (TextView) convertView.findViewById(R.id.location);
        TextView distanceView = (TextView) convertView.findViewById(R.id.distance);
        TextView ratingView = (TextView) convertView.findViewById(R.id.rating);
        final TextView userRatingView = (TextView) convertView.findViewById(R.id.userRating);
        SeekBar ratingBar = (SeekBar) convertView.findViewById(R.id.ratingBar);

        nameView.setText(this.getItem(position).getName());
        //barPicView.setImageBitmap(this.getItem(position).getPic());
        locationView.setText(this.getItem(position).getAddress() + ", " + this.getItem(position).getCity() + ", " + this.getItem(position).getState());
        distanceView.setText("Distance: " + Integer.toString(this.getItem(position).getDistance()) + " meters");
        ratingView.setText("Yelp Rating: " + Integer.toString(this.getItem(position).getRating()) + "/5");
        userRatingView.setText("My Rating: 0" +"/5");// TODO set database number here
        ratingBar.setProgress(0); // TODO set slider value based off of database number here
        ratingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userRatingView.setText("My Rating: " + Integer.toString(progress)+ "/5");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return convertView;

    }

}
