package patdwyer.cs275_happyhour;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LocationListener{

    private ArrayList<Bar> bars = new ArrayList<Bar>();
    private double lat;
    private double lng;
    private BarListAdapter adapter;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter= new BarListAdapter(this, R.layout.list_item, bars);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, this);

        ListView l = (ListView) findViewById(R.id.listView);
        l.setAdapter(adapter);

        double lat = 39.962303;
        double lng = -75.187476;

        GetBarsTask gbt = new GetBarsTask(bars, adapter, lat, lng, this);
        gbt.execute();


    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        GetBarsTask gbt = new GetBarsTask(bars, adapter, lat, lng, this);
        gbt.execute();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
