package com.example.seniorapp.API;

import com.example.seniorapp.Models.CaregiversObject;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Patterns.Patient;

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

    @POST("caregivers/add")
    Call<CaregiversObject> createCaregiver(@Body CaregiversObject caregiversObject);

    @POST("patients/add")
    Call<PatientsObject> addPatient(@Body PatientsObject patientsObject);

    @PUT("patients/put/update")
    Call<PatientsObject> updatePatientData( @Body PatientsObject patientsObject);


}
