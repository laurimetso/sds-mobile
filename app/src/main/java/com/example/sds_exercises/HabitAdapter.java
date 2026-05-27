package com.example.sds_exercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


// adapter that binds habits list data to each row in the listview
public class HabitAdapter extends ArrayAdapter<Habit> {

    private Context context;
    private ArrayList<Habit> habits;

    // constructor receives the activity context and list of habits
    public HabitAdapter(Context context, ArrayList<Habit> habits) {
        super(context, 0, habits);
        this.context = context;
        this.habits = habits;
    }

    // called by listview for each row, returns a view representing one habit
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //reuse an existing row view if availalble, otherwise inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_habit, parent, false);
        }

        // get habit corresponding to this row
        Habit habit = habits.get(position);

        // get components
        TextView habitName = convertView.findViewById(R.id.habitName);
        TextView habitCategory = convertView.findViewById(R.id.habitCategory);
        TextView habitStreak = convertView.findViewById(R.id.habitStreak);
        CheckBox habitCheck = convertView.findViewById(R.id.habitCheck);

        // fill row with data
        habitName.setText(habit.name);
        habitCategory.setText(habit.category);
        habitStreak.setText("🔥 " + habit.streak + " day streak");

        // clear the listener before setting the checked state to avoid accidentally
        // triggering the listener on recycled rows that already have one
        habitCheck.setOnCheckedChangeListener(null);
        habitCheck.setChecked(habit.completedToday);

        // update the habit when the user ticks or unticks the checkbox
        habitCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            habit.completedToday = isChecked;
            if (isChecked) {
                habit.streak++;
            }
            // refresh text
            habitStreak.setText("🔥 " + habit.streak + " day streak");
        });

        return convertView;
    }
}