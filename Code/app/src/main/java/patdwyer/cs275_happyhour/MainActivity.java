package patdwyer.cs275_happyhour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Bar> bars = new ArrayList<Bar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetBarsTask gbt = new GetBarsTask(bars);
        gbt.execute();


    }

}
