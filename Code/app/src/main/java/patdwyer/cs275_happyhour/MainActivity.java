package patdwyer.cs275_happyhour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ListView l = (ListView) findViewById(R.id.listView);
        l.setAdapter(adapter);

        gbt.execute();

    }

}
