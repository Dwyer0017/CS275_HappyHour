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

    public GetBarsTask(ArrayList<Bar> bars) {
        this.bars = bars;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        double lat = 39.962303;
        double lng = -75.187476;

        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        String response = yelpApi.searchForBusinessesByLocation("bars", lat, lng);

        try {
            JSONObject root = new JSONObject(response);
            JSONArray data = root.getJSONArray("businesses");
            for (int i = 0; i < data.length(); i++) {
                JSONObject thisBar = data.getJSONObject(i);
                Double distance = Double.parseDouble(thisBar.getString("distance"));
                String name = thisBar.getString("name");
                int rating = thisBar.getInt("rating");
                JSONObject location = thisBar.getJSONObject("location");
                String address = location.getString("address");
                String city = location.getString("city");
                String state = location.getString("state_code");
                Bar bar = new Bar(name, address, city, state, distance, rating);
                bars.add(bar);
            }
        } catch (Exception e) {

        }

        Log.w("Bars", bars.toString());





        return null;
    }
}
