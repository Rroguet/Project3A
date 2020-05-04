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
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        TextView titre = findViewById(R.id.titre);
        TextView description = findViewById(R.id.textDescription);
        ImageView icon = findViewById(R.id.imageView);
        titre.setText(intent.getStringExtra(Constants.EXTRA_GALAXIE_NAME));
        description.setText(intent.getStringExtra(Constants.EXTRA_GALAXIE_DESCRIPTION));
        Picasso.get().load(intent.getStringExtra(Constants.EXTRA_GALAXIE_IMAGE)).fit().into(icon);

        Button b = findViewById(R.id.buttonBackList);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
