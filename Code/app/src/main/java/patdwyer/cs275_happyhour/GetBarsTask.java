package patdwyer.cs275_happyhour;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class GetBarsTask extends AsyncTask<Void, Void, Void> {

    private static final String CONSUMER_KEY = "WHoNVxaZZ3-GZexaLwI0HA";
    private static final String CONSUMER_SECRET = "eLT2rsIhK5ds0ysORd42JUk2Ugk";
    private static final String TOKEN = "3JqX83Cy89AS6ccNDW4PSfsmU_TLoYgV";
    private static final String TOKEN_SECRET = "OFwyoaEBE37XPCwC7eqRrITspH4";

    private ArrayList<Bar> bars;
    private BarListAdapter adapter;
    private double lat;
    private double lng;

    public GetBarsTask(ArrayList<Bar> bars, BarListAdapter adapter, double lat, double lng) {
        this.bars = bars;
        this.adapter = adapter;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected Void doInBackground(Void... arg0) {


        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        String response = yelpApi.searchForBusinessesByLocation("bars", lat, lng);

        try {
            JSONObject root = new JSONObject(response);
            JSONArray data = root.getJSONArray("businesses");
            for (int i = 0; i < data.length(); i++) {
                JSONObject thisBar = data.getJSONObject(i);
                int distance = (int) Math.round(Double.parseDouble(thisBar.getString("distance")));
                String name = thisBar.getString("name");
                int rating = thisBar.getInt("rating");
                JSONObject location = thisBar.getJSONObject("location");
                JSONArray temp = location.getJSONArray("address");
                int length = temp.length();
                String address[] = null;
                if (length > 0) {
                    address = new String[length];
                    for (int j = 0; j < length; j++) {
                        address[j] = temp.getString(j);
                    }
                }
                String city = location.getString("city");
                String state = location.getString("state_code");
                String imageURL = thisBar.getString("image_url");
                Bar bar = new Bar(name, address[0], city, state, distance, rating, imageURL);
                bars.add(bar);
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        // When complete, kick off a background task to gather the images for each forecast
        //GetImageTask imageTask = new GetImageTask(adapter, bars);
        //imageTask.execute();
        adapter.notifyDataSetChanged();

    }
}
