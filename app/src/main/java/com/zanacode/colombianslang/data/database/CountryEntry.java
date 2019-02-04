package com.zanacode.colombianslang.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "country", indices = {@Index(value = {"name"}, unique = true)})
public class CountryEntry {

    @PrimaryKey
    @NonNull
    private final String code;
    private final String name;

    public CountryEntry(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
