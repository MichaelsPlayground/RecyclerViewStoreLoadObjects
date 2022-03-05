package de.androidcrypto.recyclerviewstoreloadobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button generateDataset;


    Intent generateDatasetIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDataset = findViewById(R.id.btnGenerateDataset);

        generateDatasetIntent = new Intent(MainActivity.this, GenerateDataset.class);


        generateDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(generateDatasetIntent);
            }
        });
    }
}