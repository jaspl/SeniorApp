package com.example.seniorapp.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.Models.TestMmseObject;
import com.example.seniorapp.R;
import com.example.seniorapp.SharedPrefs;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MmseChartActivity extends AppCompatActivity {
    List<String> dateList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_chart);
        setExitButton();
        getAllResults();
        setAxisTitles();
        setPatientNameOnTitleChart();
    }
    @Override
    public void onBackPressed() {
    }
    private void setPatientNameOnTitleChart() {
        TextView textView = findViewById(R.id.ChartTitle);
        textView.setText("Pacjent : "+new SharedPrefs(this).getName());
    }
    private void setAxisTitles(){
        TextView yAxisTitle = findViewById(R.id.yAxisTitle);
        yAxisTitle.setText("Punkty");
        TextView xAxisTitle = findViewById(R.id.xAxisTitle);
        xAxisTitle.setText("Data");
    }

    private void setExitButton() {
        FloatingActionButton exitButton = findViewById(R.id.end_game_floatig_buton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MmseChartActivity.this, StatisticActivity.class));
            }
        });
    }

    private void setChart(List<TestMmseObject> testMmseObjects) {
        List<Entry> mMSE = new ArrayList<>();
        for (int i = 0; i < testMmseObjects.size(); i++) {
            mMSE.add(new Entry(i, testMmseObjects.get(i).getResult()));
            dateList.add(testMmseObjects.get(i).getDate().substring(0,10));

        }
        LineDataSet datasetSuccessfull = new LineDataSet(mMSE, "Wyniki testu MMSE");
        datasetSuccessfull.setColor(Color.BLUE);
        datasetSuccessfull.setLineWidth(4f);
        datasetSuccessfull.setValueTextSize(12f);
        datasetSuccessfull.setCircleRadius(5f);
        datasetSuccessfull.setCircleHoleRadius(2.5f);
        datasetSuccessfull.setCircleColor(Color.BLUE);


        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(datasetSuccessfull);

        LineData lineData = new LineData(datasets);
        getLineChart().setScaleEnabled(true);
        getLineChart().setData(lineData);
        getLineChart().getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(dateList));
        getLineChart().getXAxis().setGranularity(1);

        XAxis xAxis = getLineChart().getXAxis();
        xAxis.setTextSize(20);
        xAxis.setLabelRotationAngle(-90);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxisL = getLineChart().getAxisLeft();
        yAxisL.setTextSize(20);
        getLineChart().getAxisRight().setEnabled(false);
        Legend legend = getLineChart().getLegend();
        legend.setTextSize(20);
    }


    private LineChart getLineChart() {
        return findViewById(R.id.chart);
    }

    private void setChartTitle(String name) {
        Description description = new Description();
        description.setText(name);
        description.setTextSize(30);
        getLineChart().setDescription(description);
    }

    private void getAllResults() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<TestMmseObject> testMmseObjects = (ArrayList<TestMmseObject>) args.getSerializable("ARRAYLIST");
        ArrayList<TestMmseObject> testMmseObjectsRewert= new ArrayList<>() ;
        for (int i = testMmseObjects.size()-1;i>=0;i--){
            testMmseObjectsRewert.add(testMmseObjects.get(i));
        }

        setChart(testMmseObjectsRewert);
        String name = intent.getStringExtra("title");
        setChartTitle(name);
    }

}