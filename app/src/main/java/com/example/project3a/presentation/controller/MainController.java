package com.example.project3a.presentation.controller;

import android.content.SharedPreferences;

import com.example.project3a.Constants;
import com.example.project3a.Singletons;
import com.example.project3a.presentation.model.Galaxie;
import com.example.project3a.presentation.model.RestGalaxiesResponse;
import com.example.project3a.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;
    private List<Galaxie> galaxieList;

    public MainController(MainActivity view, Gson gson, SharedPreferences sharedPreferences) {
        this.view = view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        galaxieList = getDataFromCache();
        if(galaxieList != null) view.showList(galaxieList);
        else makeAPIcall();
    }

    private List<Galaxie> getDataFromCache(){
        String jsonGalaxie = sharedPreferences.getString(Constants.KEY_GALAXIE_LIST, null);

        if(jsonGalaxie == null) return null;
        else {
            Type listType = new TypeToken<List<Galaxie>>() {}.getType();
            return gson.fromJson(jsonGalaxie, listType);
        }
    }

    private void makeAPIcall(){

        Call<RestGalaxiesResponse> call = Singletons.getGalaxieAPI().getGalaxieResponse();
        call.enqueue(new Callback<RestGalaxiesResponse>() {
            @Override
            public void onResponse(Call<RestGalaxiesResponse> call, Response<RestGalaxiesResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    galaxieList = response.body().getGalaxies();
                    SaveList(galaxieList);
                    view.showList(galaxieList);
                }else{
                    view.showErreur();
                }
            }

            @Override
            public void onFailure(Call<RestGalaxiesResponse> call, Throwable t) {
                view.showErreur();
            }
        });
    }

    private void SaveList(List<Galaxie> galaxies){
        String jsonString = gson.toJson(galaxies);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GALAXIE_LIST, jsonString)
                .apply();
        view.toastListSave();
    }


    public void onItemClick(Galaxie galaxie){

    }
}
