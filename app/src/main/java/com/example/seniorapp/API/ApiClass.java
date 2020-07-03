package com.example.seniorapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    public Api api;

    public ApiClass() {
        retrofitBuild();
    }

    public void retrofitBuild(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.102:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }
    public Api getApi(){
        return api;
    }
}
