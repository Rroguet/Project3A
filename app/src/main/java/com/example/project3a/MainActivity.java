package com.example.project3a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> inputTitle = new ArrayList<>();
        List<String> inputDescription = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            inputTitle.add("Test" + i);
            inputDescription.add("Description n " + i);
        }// define an adapter
        mAdapter = new ListAdapter(inputTitle, inputDescription);
        recyclerView.setAdapter(mAdapter);
    }
}
