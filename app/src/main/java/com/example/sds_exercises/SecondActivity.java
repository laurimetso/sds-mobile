package com.example.sds_exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


// main screen of the app, displays the full list of habits
public class SecondActivity extends AppCompatActivity {

    ListView myListView;
    HabitAdapter habitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        myListView = findViewById(R.id.myListView);

        // add default habits on first launch
        if (HabitManager.getHabits().isEmpty()) {
            HabitManager.addHabit(new Habit("Morning Run", "Health"));
            HabitManager.addHabit(new Habit("Read 20 pages", "Study"));
            HabitManager.addHabit(new Habit("Drink 2L water", "Health"));
        }

        // create the adapter and attach to listview
        habitAdapter = new HabitAdapter(this, HabitManager.getHabits());
        myListView.setAdapter(habitAdapter);

        // tap a habit row to open detail screen
        // pass the row index so detailactivity knows what habit to make
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("com.example.sds_exercises.ITEM_INDEX", i);
                startActivity(showDetail);
            }
        });

        // + button to add a new habit, opens the add habit screen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addHabit = new Intent(getApplicationContext(), AddHabitActivity.class);
                startActivity(addHabit);
            }
        });
    }

    // called every time the user returns to this screen
    // tells the adapter to re read the habit list and re draw the rows
    @Override
    protected void onResume() {
        super.onResume();
        habitAdapter.notifyDataSetChanged();
    }
}