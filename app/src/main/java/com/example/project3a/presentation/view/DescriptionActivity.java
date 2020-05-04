package com.example.project3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project3a.Constants;
import com.example.project3a.R;
import com.example.project3a.Singletons;
import com.example.project3a.presentation.controller.DescriptionController;
import com.example.project3a.presentation.controller.MainController;
import com.example.project3a.presentation.model.Galaxie;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    private TextView titre;
    private TextView description;
    private ImageView icon;
    private Button button;
    private DescriptionController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        titre = findViewById(R.id.titre);
        description = findViewById(R.id.textDescription);
        icon = findViewById(R.id.imageView);
        button = findViewById(R.id.buttonBackList);

        Intent intent = getIntent();
        String galaxieJson = intent.getStringExtra(Constants.EXTRA_GALAXIE);
        Galaxie galaxie = Singletons.getGson().fromJson(galaxieJson, Galaxie.class);

        controller = new DescriptionController(this, galaxie);
        controller.onStart();



        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                controller.onButtonClick();
            }
        });

    }

    public void showDetail(Galaxie galaxie){
        titre.setText(galaxie.getName());
        description.setText(galaxie.getDescription());
        Picasso.get().load(galaxie.getUrl()).fit().into(icon);
    }

    public void backToList(){
        finish();
    }

}
