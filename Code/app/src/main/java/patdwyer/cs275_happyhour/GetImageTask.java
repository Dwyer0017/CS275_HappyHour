package patdwyer.cs275_happyhour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by patrickdwyer on 8/25/15.
 */
public class GetImageTask extends AsyncTask<Void, Void, Void> {

    private BarListAdapter adapter;
    private ArrayList<Bar> bars;

    public GetImageTask(BarListAdapter adapter, ArrayList<Bar> bars) {
        this.adapter = adapter;
        this.bars = bars;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        // For all the data in forecasts ...
        for (int i = 0; i < bars.size(); i++) {
            try {
                // Gather the image from the URL
                URL url = new URL(bars.get(i).getImageURL());
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                InputStream stream = (InputStream)request.getContent();
                Bitmap myBitmap = BitmapFactory.decodeStream(stream);
                // Save the bitmap image to our data
                bars.get(i).setPic(myBitmap);
            } catch (Exception e) {
                Log.e("exception", e.getMessage());
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        // When complete, tell the adapter we are ready to load
        adapter.notifyDataSetChanged();

    }
}
