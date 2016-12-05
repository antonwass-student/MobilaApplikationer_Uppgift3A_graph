package com.anton.mobilaapplikationer_uppgift3a_graph;

import android.content.SharedPreferences;
import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.LinkedList;

/**
 * Created by Anton on 2016-12-05.
 */

public class RealTimeGraph {
    private GraphView graph;

    private long startTime;
    private final int MAX_VALUES = 100;

    private LineGraphSeries<DataPoint>[] series = new LineGraphSeries[3];

    public RealTimeGraph(GraphView graph){
        this.graph = graph;
        initialize();
        startTime = System.currentTimeMillis();
    }


    public void appendData(float[] newValues){
        long time = (System.currentTimeMillis() - startTime);

        for(int i = 0; i < 3; i++){
            series[i].appendData(new DataPoint(time, newValues[i]), false, MAX_VALUES);
        }
    }


    private void initialize(){
        for(int i = 0; i < 3; i++){
            series[i] = new LineGraphSeries();
            graph.addSeries(series[i]);
        }

        series[0].setColor(Color.RED);
        series[1].setColor(Color.BLUE);
        series[2].setColor(Color.GREEN);
    }
}
