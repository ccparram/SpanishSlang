package com.zanacode.colombianslang.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "meaning",
        foreignKeys = { @ForeignKey( entity = SlangEntry.class,
                            parentColumns = "id", childColumns = "slangId",
                            onDelete = CASCADE, onUpdate = CASCADE),
                        @ForeignKey( entity = CountryEntry.class,
                            parentColumns = "code", childColumns = "countryCode",
                            onDelete = CASCADE, onUpdate = CASCADE)},
        indices={ @Index(value="slangId"), @Index(value="countryCode")})

public class MeaningEntry {

    @PrimaryKey
    private final int id;
    @NonNull
    private final int slangId;
    @NonNull
    private final String countryCode;
    @NonNull
    private final String description;
    private final String example;

    public MeaningEntry(int id, int slangId, @NonNull String countryCode, @NonNull String description, String example) {
        this.id = id;
        this.slangId = slangId;
        this.countryCode = countryCode;
        this.description = description;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public int getSlangId() {
        return slangId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDescription() {
        return description;
    }

    public String getExample() {
        return example;
    }
}
