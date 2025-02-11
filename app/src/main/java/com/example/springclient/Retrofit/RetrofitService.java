package com.example.springclient.Retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {


    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://192.168.100.111:8080";


    public  RetrofitService(){
        initialiseRetrofit();
    }

    private void initialiseRetrofit() {

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
        }
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
