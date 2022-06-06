package ru.mirea.zavarzin.mireaproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.zavarzin.mireaproject.Story;
import ru.mirea.zavarzin.mireaproject.StoryDAO;

@Database(entities = {Story.class}, version = 1)
public abstract class StoriesDatabase extends RoomDatabase {

    public abstract StoryDAO storyDAO();
}
