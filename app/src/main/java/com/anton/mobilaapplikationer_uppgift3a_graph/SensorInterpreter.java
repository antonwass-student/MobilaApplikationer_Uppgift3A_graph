package com.anton.mobilaapplikationer_uppgift3a_graph;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Anton on 2016-12-05.
 */

public class SensorInterpreter {

    private RealTimeGraph graph;
    private SensorManager sensorManager;
    private MainActivity activity;
    private Sensor activeSensor;

    public SensorInterpreter(MainActivity mainActivity, RealTimeGraph graph, int sensor){
        this.activity = mainActivity;
        this.graph = graph;

        sensorManager =
                (SensorManager)activity.getSystemService(activity.getApplicationContext().SENSOR_SERVICE);

        activeSensor =
                sensorManager.getDefaultSensor(sensor);


        if(activeSensor !=null){
            sensorManager.registerListener(
                    sensorEventListener,
                    activeSensor,
                    SensorManager.SENSOR_DELAY_GAME
            );
        }
    }

    public void setSensor(int sensor){
        sensorManager.unregisterListener(sensorEventListener);

        activeSensor = sensorManager.getDefaultSensor(sensor);

        sensorManager.registerListener(
                sensorEventListener,
                activeSensor,
                SensorManager.SENSOR_DELAY_GAME
        );

    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            graph.appendData(event.values);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void changeSensor(String sensorStr){
        int sensor = Sensor.TYPE_ACCELEROMETER;
        switch(sensorStr){
            case "acc":
                sensor = Sensor.TYPE_ACCELEROMETER;
                break;
            case "gyro":
                sensor = Sensor.TYPE_GYROSCOPE;
                break;
            case "mag":
                sensor = Sensor.TYPE_MAGNETIC_FIELD;
                break;
        }
        setSensor(sensor);
    }
}
