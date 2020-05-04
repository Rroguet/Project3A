package com.example.project3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.example.project3a.R;
import com.example.project3a.presentation.controller.MainController;
import com.example.project3a.presentation.model.Galaxie;
import com.google.gson.GsonBuilder;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MainController controller;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this,
            new GsonBuilder()
                .setLenient()
                .create(),
            getSharedPreferences("application_Galaxies", Context.MODE_PRIVATE));
        controller.onStart();
    }

    public void showList(List<Galaxie> galaxieList){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter mAdapter = new ListAdapter(galaxieList);
        recyclerView.setAdapter(mAdapter);
    }

    public void toastListSave(){
        Toast.makeText(this, "List saved", Toast.LENGTH_SHORT).show();
    }

    public void showErreur(){
        Toast.makeText(this, "Connection failure", Toast.LENGTH_SHORT).show();
    }
}