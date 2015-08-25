package patdwyer.cs275_happyhour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView locationView = (TextView) convertView.findViewById(R.id.location);
        TextView distanceView = (TextView) convertView.findViewById(R.id.distance);
        TextView ratingView = (TextView) convertView.findViewById(R.id.rating);

        nameView.setText(this.getItem(position).getName());
        locationView.setText(this.getItem(position).getAddress() + ", " + this.getItem(position).getCity() + ", " + this.getItem(position).getState());
        distanceView.setText(Double.toString(this.getItem(position).getDistance()));
        ratingView.setText(Integer.toString(this.getItem(position).getRating()));

        return convertView;

    }

}
