package com.example.project3a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Rroguet/Project3A/master/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Galaxie> galaxieList;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeAPIcall();
    }

    private void showList(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(galaxieList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeAPIcall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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

    private void showErreur(){
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }
}