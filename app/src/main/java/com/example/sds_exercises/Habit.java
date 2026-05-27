package com.example.sds_exercises;


// basic data model of habit
public class Habit {
    public String name;
    public String category;
    public boolean completedToday;
    public int streak;

    public Habit(String name, String category) {
        this.name = name;
        this.category = category;
        this.completedToday = false;
        this.streak = 0;
    }
}