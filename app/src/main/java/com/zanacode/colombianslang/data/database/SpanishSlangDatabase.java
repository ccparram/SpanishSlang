package com.zanacode.colombianslang.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {SlangEntry.class,
                                    CountryEntry.class,
                                    MeaningEntry.class}, version = 1)
public abstract class SpanishSlangDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "spanish_slang";
    private static final Object LOCK = new Object();
    private static volatile SpanishSlangDatabase instance;

    public abstract SlangDao slangDao();

    public static SpanishSlangDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, SpanishSlangDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
