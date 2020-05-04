package com.example.project3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project3a.Constants;
import com.example.project3a.R;
import com.example.project3a.data.GalaxieAPI;
import com.example.project3a.presentation.model.Galaxie;
import com.example.project3a.presentation.model.RestGalaxiesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Rroguet/Project3A/master/";
    private List<Galaxie> galaxieList;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_Galaxies", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        galaxieList = getDataFromCache();
        if(galaxieList != null) showList();
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

    private void showList(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter mAdapter = new ListAdapter(galaxieList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeAPIcall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GalaxieAPI galaxieAPI = retrofit.create(GalaxieAPI.class);

        Call<RestGalaxiesResponse> call = galaxieAPI.getGalaxieResponse();
        call.enqueue(new Callback<RestGalaxiesResponse>() {
            @Override
            public void onResponse(Call<RestGalaxiesResponse> call, Response<RestGalaxiesResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    galaxieList = response.body().getGalaxies();
                    SaveList(galaxieList);
                    showList();
                }else{
                    showErreur();
                }
            }

            @Override
            public void onFailure(Call<RestGalaxiesResponse> call, Throwable t) {
                showErreur();
            }
        });
    }

    private void SaveList(List<Galaxie> galaxies){
        String jsonString = gson.toJson(galaxies);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GALAXIE_LIST, jsonString)
                .apply();
        Toast.makeText(this, "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showErreur(){
        Toast.makeText(this, "Connection failure", Toast.LENGTH_SHORT).show();
    }
}