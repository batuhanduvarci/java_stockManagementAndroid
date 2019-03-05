package com.example.agent48.termproject.RetrofitHttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Agent47 on 30.07.2017.
 */

public class ApiClient {

    public static final String BASE_URL = "Web service lol url";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        if(retrofit == null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}
