package com.zanacode.colombianslang.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country ORDER BY code ASC")
    LiveData<List<CountryEntry>> getAllCountries();
}
