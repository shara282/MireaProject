package ru.mirea.zavarzin.mireaproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String story;

    public long getId() {
        return id;
    }

    public String getStory() {
        return story;
    }
}
