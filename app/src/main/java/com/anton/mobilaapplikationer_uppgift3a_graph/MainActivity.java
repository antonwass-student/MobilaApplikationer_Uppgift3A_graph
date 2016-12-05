package com.anton.mobilaapplikationer_uppgift3a_graph;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;

public class MainActivity extends AppCompatActivity {

    private GraphView graph;
    private SharedPreferences sharedPref;
    private SensorInterpreter sensors;
    private RealTimeGraph rtg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        graph = (GraphView)findViewById(R.id.graph);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String sensorPref = sharedPref.getString("pref_sensor",null);

        rtg = new RealTimeGraph(graph);
        sensors = new SensorInterpreter(this, rtg, Sensor.TYPE_ACCELEROMETER);

        sensors.changeSensor(sensorPref);

        sharedPref.registerOnSharedPreferenceChangeListener(spChanged);
    }

    SharedPreferences.OnSharedPreferenceChangeListener spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            sensors.changeSensor(sharedPref.getString(key,null));
        }
    };

}
