package com.example.sds_exercises;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    EditText habitNameInput;
    Spinner categorySpinner;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_habit);

        // get components
        habitNameInput = findViewById(R.id.habitNameInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        saveButton = findViewById(R.id.saveButton);

        // set up category spinner
        String[] categories = {"Health", "Study", "Lifestyle", "Fitness", "Other"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);


        // saving feature
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get habit name and category
                String name = habitNameInput.getText().toString().trim();
                String category = categorySpinner.getSelectedItem().toString();

                // if there is no name, notify the user about it
                if (name.isEmpty()) {
                    Toast.makeText(AddHabitActivity.this, "Please enter a habit name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // call habitmanager and add habit
                HabitManager.addHabit(new Habit(name, category));
                Toast.makeText(AddHabitActivity.this, "Habit added!", Toast.LENGTH_SHORT).show();
                finish(); // go back to secondactivity
            }
        });
    }
}