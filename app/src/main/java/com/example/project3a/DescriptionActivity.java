package com.example.project3a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.titre);
        ImageView icon = findViewById(R.id.imageView);
        textView.setText(intent.getStringExtra(Constants.EXTRA_GALAXIE_NAME));
        Picasso.get().load(intent.getStringExtra(Constants.EXTRA_GALAXIE_IMAGE)).fit().into(icon);

    }
}
