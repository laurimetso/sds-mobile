package com.example.sds_exercises;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


// this is the activity which is shown when user presses a habit in the second activity
public class DetailActivity extends AppCompatActivity {

    TextView detailName;
    TextView detailCategory;
    TextView detailStreak;
    TextView detailStatus;
    Button deleteButton;

    int habitIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        // get components
        detailName = findViewById(R.id.detailName);
        detailCategory = findViewById(R.id.detailCategory);
        detailStreak = findViewById(R.id.detailStreak);
        detailStatus = findViewById(R.id.detailStatus);
        deleteButton = findViewById(R.id.deleteButton);

        // get the habit index passed from secondactivity
        habitIndex = getIntent().getIntExtra("com.example.sds_exercises.ITEM_INDEX", -1);


        // check that the index is valid before touching habit list
        if (habitIndex >= 0 && habitIndex < HabitManager.getHabits().size()) {
            Habit habit = HabitManager.getHabits().get(habitIndex);

            // populate UI fields with habit's data
            detailName.setText(habit.name);
            detailCategory.setText("Category: " + habit.category);
            detailStreak.setText("🔥 " + habit.streak + " day streak");

            // ternary operator, shows different text on whether habit is marked done or not
            detailStatus.setText(habit.completedToday ? "✅ Done today!" : "Not done yet today");
        }

        // deletion of habit
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitManager.removeHabit(habitIndex);
                Toast.makeText(DetailActivity.this, "Habit deleted", Toast.LENGTH_SHORT).show();
                finish(); // go back to secondactivity after deletion
            }
        });
    }
}