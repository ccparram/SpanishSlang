package com.zanacode.colombianslang.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "slang", indices = {@Index(value = {"title"}, unique = true)})
public class SlangEntry {

    @PrimaryKey
    @NonNull
    private final int id;
    @NonNull
    private final String title;

    public SlangEntry(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
