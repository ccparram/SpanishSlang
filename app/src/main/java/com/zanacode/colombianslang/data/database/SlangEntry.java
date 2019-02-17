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
    @NonNull
    private int isFavorite;

    public SlangEntry(int id, @NonNull String title, @NonNull int isFavorite) {
        this.id = id;
        this.title = title;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite == 1;
    }

    public void setIsFavorite(Boolean isFavorite){
        this.isFavorite = isFavorite ? 1 : 0;
    }
}
