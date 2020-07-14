package com.example.seniorapp.API;

import com.example.seniorapp.Models.CaregiversObject;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Models.TestMmseObject;
import com.example.seniorapp.Patterns.Patient;
import com.example.seniorapp.Utils.LevelGame;
import com.example.seniorapp.Utils.NameGame;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {

    @GET("caregivers/get")
    Call<CaregiversObject> caregiverInfoGetter(@Query("login") String login, @Query("password") String password);

    @GET("patients/login/get")
    Call<PatientsObject> getPatient(@Query("login") String login, @Query("password") String password);

    @GET("patients/get")
    Call<PatientsObject> getPatientInfo(@Query("patient id") int id);

    @GET("patients/get/all")
    Call<List<PatientsObject>> getPatientList();

    @GET("games/get/all")
    Call<List<GamesObject>> getGames(@Query("Patient id") int id, @Query("Game name") NameGame nameGame);

    @GET("testMmse/get/all")
    Call<List<TestMmseObject>> getMmseResults(@Query("patient id") int id);

    @POST("caregivers/add")
    Call<CaregiversObject> createCaregiver(@Body CaregiversObject caregiversObject);

    @POST("patients/add")
    Call<PatientsObject> addPatient(@Body PatientsObject patientsObject);

    @POST("testMmse/add")
    Call<TestMmseObject> addMMSE(@Body TestMmseObject testMmseObject);

    @POST("games/add")
    Call<GamesObject> addGame(@Body GamesObject gamesObject);

    @PUT("patients/put/update")
    Call<PatientsObject> updatePatientData(@Body PatientsObject patientsObject);

    @PUT("patients/delete")
    Call<PatientsObject> deletePatient(@Query("patient id") int id);

    @PUT("patients/put/updateLevelFromMMSE")
    Call<PatientsObject> updateLevelMMSE(@Query("patient id") int id, @Query("level Of MMSE") boolean mmseToLvl, @Query("level of Game") LevelGame levelGame);
}
