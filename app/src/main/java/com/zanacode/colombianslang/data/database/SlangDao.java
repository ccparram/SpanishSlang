package com.zanacode.colombianslang.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface SlangDao {

    @Insert
    void insert(SlangEntry slangEntry);

    @Insert
    void bulkInsert(List<SlangEntry> slangEntries);

    @Query("SELECT * FROM slang")
    LiveData<List<SlangEntry>> getAllSlangs();

    @Query("SELECT title FROM slang")
    LiveData<String[]> getSlangSuggestions();

    @Transaction
    @Query("SELECT * FROM meaning JOIN country ON meaning.countryCode = country.code WHERE slangId = :id ")
    LiveData<List<MeaningsCountryJoin>> getMeaningSlangCountryJoinById(int id);

    @Transaction
    @Query("SELECT id FROM slang WHERE title = :title")
    LiveData<Integer> getSlangIdByTitle(String title);
}
