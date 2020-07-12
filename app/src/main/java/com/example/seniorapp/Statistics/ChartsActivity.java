package com.example.seniorapp.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.AddNewPatientActivity;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.PatientListActivity;
import com.example.seniorapp.ProgressDialogClass;
import com.example.seniorapp.R;
import com.example.seniorapp.SharedPrefs;
import com.example.seniorapp.Utils.LevelGame;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        Intent intent = getIntent();
        NameGame nameGame = (NameGame) intent.getSerializableExtra("gameName");

        getAllResults(nameGame);
    }



    private void setChart( List<GamesObject> gamesObjects) {
        List<Entry> succesful = new ArrayList<>();
        List<Entry> failed = new ArrayList<>();

        for (int i = 0; i < gamesObjects.size(); i++) {
            if (!(i == 0) && !(i == gamesObjects.size() - 1)) {
                if (!(gamesObjects.get(i).getLevel().equals(gamesObjects.get(i - 1).getLevel()))) {
                    setLimitLineX(i,gamesObjects.get(i).getLevel());
                }
            }else if ( i ==0 ){
                setLimitLineX(i,gamesObjects.get(i).getLevel());
            }
            if (gamesObjects.get(i).getStatus().equals(StatusGame.SUCCESSFUL)) {
                succesful.add(new Entry(i, Float.parseFloat(gamesObjects.get(i).getTime())));
            } else
                failed.add(new Entry(i, Float.parseFloat(gamesObjects.get(i).getTime())));
        }

        LineDataSet datasetSuccessfull = new LineDataSet(succesful, "Prawidłowo");
        datasetSuccessfull.setColor(Color.BLUE);
        datasetSuccessfull.setLineWidth(4f);
        datasetSuccessfull.setValueTextSize(0f);
        datasetSuccessfull.setCircleRadius(5f);
        datasetSuccessfull.setCircleHoleRadius(2.5f);
        datasetSuccessfull.setCircleColor(Color.BLUE);

        LineDataSet datasetFailed = new LineDataSet(failed, "Błędnie");
        datasetFailed.setColor(Color.RED);
        datasetFailed.setLineWidth(0f);
        datasetFailed.setValueTextSize(0f);
        datasetFailed.setCircleRadius(5f);
        datasetFailed.setCircleHoleRadius(2.5f);
        datasetFailed.setCircleColor(Color.RED);

        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(datasetFailed);
        datasets.add(datasetSuccessfull);

        LineData lineData = new LineData(datasets);
        getLineChart().setScaleEnabled(true);
        getLineChart().setData(lineData);
    }

    private LineChart getLineChart() {
        return findViewById(R.id.chart);
    }

    private void setLimitLineX(int limitValue, LevelGame lvl) {
        LimitLine lvlBoarder = new LimitLine(limitValue, lvl.toString());
        lvlBoarder.setLineWidth(5f);
        XAxis xAxis = getLineChart().getXAxis();
        xAxis.addLimitLine(lvlBoarder);
        xAxis.setDrawGridLinesBehindData(true);
    }

    private void setChartTitle(String name) {
        Description description = new Description();
        description.setText(name);
        description.setTextSize(20);
        getLineChart().setDescription(description);
    }

    private List<GamesObject> getAllSuccessfulFromList(List<GamesObject> gamesObjects) {
        List<GamesObject> listOfSuccessfulGameObjects = new ArrayList<>();
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i).getStatus().equals(StatusGame.SUCCESSFUL)) {
                listOfSuccessfulGameObjects.add(gamesObjects.get(i));
            }
        }
        return listOfSuccessfulGameObjects;
    }

    private List<GamesObject> getAllFailedFromList(List<GamesObject> gamesObjects) {
        List<GamesObject> listOfFailedGameObjects = new ArrayList<>();
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i).getStatus().equals(StatusGame.FAILED)) {
                listOfFailedGameObjects.add(gamesObjects.get(i));
            }
        }
        return listOfFailedGameObjects;
    }

    private void getAllResults(NameGame nameGame) {
        Log.d("TAG", "getAllResults: " + nameGame);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<GamesObject> gamesObjects = (ArrayList<GamesObject>) args.getSerializable("ARRAYLIST");
        setChart(gamesObjects);
        String name = intent.getStringExtra("gameTitle");
        setChartTitle(name);
    }

}