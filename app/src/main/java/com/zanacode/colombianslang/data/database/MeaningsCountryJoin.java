package com.zanacode.colombianslang.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class MeaningsCountryJoin {

    @Embedded
    private MeaningEntry meaning;

    @ColumnInfo(name = "code")
    private String countryCode;

    @ColumnInfo(name = "name")
    private String countryName;

    public MeaningsCountryJoin(MeaningEntry meaning, String countryCode, String countryName) {
        this.meaning = meaning;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public MeaningEntry getMeaning() {
        return meaning;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }
}
