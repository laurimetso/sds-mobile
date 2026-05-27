package com.example.sds_exercises;

import java.util.ArrayList;


// this is a manager for the habits
// basically an arraylist which has the habits, a getter, adder and remover.
public class HabitManager {
    private static ArrayList<Habit> habits = new ArrayList<>();

    public static ArrayList<Habit> getHabits() {
        return habits;
    }

    public static void addHabit(Habit habit) {
        habits.add(habit);
    }

    public static void removeHabit(int index) {
        habits.remove(index);
    }
}