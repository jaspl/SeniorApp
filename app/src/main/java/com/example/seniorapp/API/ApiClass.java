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
                .baseUrl("http://senior-app-api-git-projektmagisterski2.apps.us-east-2.starter.openshift-online.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }
    public Api getApi(){
        return api;
    }
}
