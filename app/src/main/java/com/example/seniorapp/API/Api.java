package com.example.seniorapp.API;

import com.example.seniorapp.Models.CaregiversObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("caregivers/get")
    Call<CaregiversObject> caregiverInfoGetter(@Query("login") String login, @Query("password") String password);

    @POST("caregivers/add")
    Call<CaregiversObject> createCaregiver(@Body CaregiversObject caregiversObject);

}
