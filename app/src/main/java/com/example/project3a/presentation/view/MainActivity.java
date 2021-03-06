package com.example.project3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project3a.Constants;
import com.example.project3a.R;
import com.example.project3a.Singletons;
import com.example.project3a.presentation.controller.MainController;
import com.example.project3a.presentation.model.Galaxie;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MainController controller;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this, Singletons.getGson(),Singletons.getSharedPreferences(getApplicationContext()));
        controller.onStart();
    }

    public void showList(List<Galaxie> galaxieList){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter mAdapter = new ListAdapter(galaxieList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Galaxie item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void toastListSave(){
        Toast.makeText(this, "List saved", Toast.LENGTH_SHORT).show();
    }

    public void showErreur(){
        Toast.makeText(this, "Connection failure", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Galaxie galaxie) {
        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
        intent.putExtra(Constants.EXTRA_GALAXIE, Singletons.getGson().toJson(galaxie));
        this.startActivity(intent);
    }
}