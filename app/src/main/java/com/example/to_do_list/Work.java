package com.example.to_do_list;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


//table or entity name
@Entity(tableName = "work_table")

public class Work {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String event_name;
    private String event_date;
    private String event_duetime;
    private int priority;


    //constructor
    public Work(String event_name, String event_date, String event_duetime, int priority) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_duetime = event_duetime;
        this.priority = priority;
    }


    //setter
    public void setId(int id) {
        this.id = id;
    }


    //getter
    public int getId() {
        return id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_duetime() {
        return event_duetime;
    }

    public int getPriority() {
        return priority;
    }

}