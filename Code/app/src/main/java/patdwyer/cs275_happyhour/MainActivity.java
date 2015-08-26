package patdwyer.cs275_happyhour;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Bar> bars = new ArrayList<Bar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarListAdapter adapter = new BarListAdapter(this, R.layout.list_item, bars);

        GetBarsTask gbt = new GetBarsTask(bars, adapter);

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        Log.w("MAC", macAddress);

        ListView l = (ListView) findViewById(R.id.listView);
        l.setAdapter(adapter);

        gbt.execute();

    }

}
