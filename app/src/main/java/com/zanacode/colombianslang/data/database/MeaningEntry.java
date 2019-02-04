package com.zanacode.colombianslang.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "meaning", primaryKeys = {"id_slang", "code_country"})
public class MeaningEntry {

    @NonNull
    private final String id_slang;
    @NonNull
    private final String code_country;
    private final String description;

    public MeaningEntry(String id_slang, String code_country, String description) {
        this.id_slang = id_slang;
        this.code_country = code_country;
        this.description = description;
    }

    public String getId_slang() {
        return id_slang;
    }

    public String getCode_country() {
        return code_country;
    }

    public String getDescription() {
        return description;
    }
}
