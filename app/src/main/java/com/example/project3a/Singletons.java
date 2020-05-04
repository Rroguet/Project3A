package com.example.project3a;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project3a.data.GalaxieAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static GalaxieAPI galaxieApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static GalaxieAPI getGalaxieAPI(){
        if(galaxieApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            galaxieApiInstance = retrofit.create(GalaxieAPI.class);
        }
        return galaxieApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("application_Galaxies", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
