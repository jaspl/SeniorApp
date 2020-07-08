package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Controlles.MMSEQuestionController;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Models.TestMmseObject;
import com.example.seniorapp.Patterns.MMSEQuestion;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MmseActivity extends AppCompatActivity {
    int questionCount = 0;
    int pointGetCounter = 0;
    List<MMSEQuestion> questionList = new MMSEQuestionController().getQuestionList();
    long startTime = 0;
    long endTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse);
        setButtonsOnClics();
        startTime = System.currentTimeMillis();
    }

    private void setButtonsOnClics() {
        setLayoutForQuestion(questionList.get(questionCount));
        Button nextQuestion = findViewById(R.id.MMSE_next_exercise_button);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoints();
            }
        });
        Button showImageButton = findViewById(R.id.MMSE_image_show_button);
        showImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout imageLayout = findViewById(R.id.MMSE_image_layout);
                imageLayout.setVisibility(View.VISIBLE);
                LinearLayout testLayout = findViewById(R.id.MMSE_test_layout);
                testLayout.setVisibility(View.GONE);
            }
        });
        Button hideImageButton = findViewById(R.id.MMSE_imagehide__button);
        hideImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout imageLayout = findViewById(R.id.MMSE_image_layout);
                imageLayout.setVisibility(View.GONE);
                LinearLayout testLayout = findViewById(R.id.MMSE_test_layout);
                testLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ifQuestionIs19Or20(MMSEQuestion question) {
        Button showImageButton = findViewById(R.id.MMSE_image_show_button);
        Button nextQuestion = findViewById(R.id.MMSE_next_exercise_button);
        if (question.getCount() == 19) {
            showImageButton.setVisibility(View.VISIBLE);
        } else {
            showImageButton.setVisibility(View.GONE);
        }
        if (question.getCount() == 20) {
            nextQuestion.setText("Zakończ test");
            nextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText pointGet = findViewById(R.id.MMSE_point_count);
                    TextInputLayout pointGetError = findViewById(R.id.MMSE_point_count_error);

                    int value;
                    if (!pointGet.getText().toString().equals("")) {
                        String str = pointGet.getText().toString();
                        value = Integer.parseInt(str);
                    } else {
                        value = -1;
                    }
                    if (value >= 0 && value <= questionList.get(questionCount).getMaxPointAmount()) {
                        questionCount++;
                        pointGetCounter += value;
                        endTime = System.currentTimeMillis();
                        float time = (endTime-startTime)/1000;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                        Date date=java.util.Calendar.getInstance().getTime();
                        TestMmseObject testMmseObject = new TestMmseObject(pointGetCounter,String.valueOf(time),simpleDateFormat.format(date),new SharedPrefs(getApplicationContext()).getId());
                        sendResultToSerwer(testMmseObject);
                        pointGetError.setError(null);
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(MmseActivity.this);
                        dialog.setMessage("Wynik pacjenta jest równy: " + pointGetCounter + ". \n Czy chcesz wykorzystać wynik testu w celu dobrania poziomu trudności gier?").setCancelable(false);
                        dialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //TODO zrobić aby zmieniło się ze btrac wynik mmse do doboru poziomu
                            }
                        });
                        dialog.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pointGetCounter = 0;
                                dialogInterface.cancel();
                                Intent intent = new Intent(MmseActivity.this, SelectedPatientActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog alertDialog = dialog.create();
                        alertDialog.setTitle("WYNIKI TESTU");
                        alertDialog.show();
                    } else {
                        pointGetError.setError("niewłasciwa liczba punktów");
                    }
                }
            });
        }
    }

    private void addPoints() {
        EditText pointGet = findViewById(R.id.MMSE_point_count);
        TextInputLayout pointGetError = findViewById(R.id.MMSE_point_count_error);

        int value;
        if (!pointGet.getText().toString().equals("")) {
            String str = pointGet.getText().toString();
            value = Integer.parseInt(str);
        } else {
            value = -1;
        }
        if (value >= 0 && value <= questionList.get(questionCount).getMaxPointAmount()) {
            questionCount++;
            pointGetCounter += value;
            ifQuestionIs19Or20(questionList.get(questionCount));
            setLayoutForQuestion(questionList.get(questionCount));
            pointGetError.setError(null);
        } else {
            pointGetError.setError("niewłasciwa liczba punktów");
        }
    }

    private void setLayoutForQuestion(MMSEQuestion question) {
        TextView questionCount = findViewById(R.id.MMSE_exercise_count);
        TextView questionContent = findViewById(R.id.MMSE_exercise_text);
        TextView maxPointToGet = findViewById(R.id.MMSE_max_point_to_get);
        questionCount.setText("" + question.getCount());
        questionContent.setText(question.getExerciseContent());
        maxPointToGet.setText("" + question.getMaxPointAmount());
    }

    private void sendResultToSerwer(TestMmseObject testMmseObject) {
            Api api = new ApiClass().getApi();
            ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
            progressDialog.show();
            Call<TestMmseObject> call = api.addMMSE(testMmseObject);
            call.enqueue(new Callback<TestMmseObject>() {
                @Override
                public void onResponse(Call<TestMmseObject> call, Response<TestMmseObject> response) {
                    if (!response.isSuccessful()) {
                        Log.d("code:", "" + response.code());
                        //TODO error handler when sth i wrong
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<TestMmseObject> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
    }
//TODO do id mmse set lvl
    private void setMMSETrueOrFalse(TestMmseObject testMmseObject) {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<TestMmseObject> call = api.addMMSE(testMmseObject);
        call.enqueue(new Callback<TestMmseObject>() {
            @Override
            public void onResponse(Call<TestMmseObject> call, Response<TestMmseObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    //TODO error handler when sth i wrong
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TestMmseObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
