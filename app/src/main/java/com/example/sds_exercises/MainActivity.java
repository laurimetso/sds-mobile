package com.example.sds_exercises;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // launch the habit tracker screen directly
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}