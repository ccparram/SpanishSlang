package com.zanacode.colombianslang.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface SlangDao {

    @Insert
    void insert(SlangEntry slangEntry);

    @Insert
    void bulkInsert(List<SlangEntry> slangEntries);

    @Query("SELECT * FROM slang")
    LiveData<List<SlangEntry>> getAllSlangs();

    @Query("SELECT * FROM slang WHERE id IN (SELECT slangId FROM meaning where countryCode = :code)")
    LiveData<List<SlangEntry>> getAllSlangByCountry(String code);

    @Query("SELECT title FROM slang")
    LiveData<String[]> getSlangSuggestions();

    @Transaction
    @Query("SELECT * FROM meaning JOIN country ON meaning.countryCode = country.code WHERE slangId = :id ")
    LiveData<List<MeaningsCountryJoin>> getMeaningSlangCountryJoinById(int id);

    @Transaction
    @Query("SELECT id FROM slang WHERE title = :title")
    LiveData<Integer> getSlangIdByTitle(String title);

    @Update()
    void updateSlang(SlangEntry slang);

    @Query("UPDATE slang SET isFavorite = NOT isFavorite WHERE id = :slangId")
    void toggleIsFavoriteBySlandId(int slangId);

    @Query("SELECT isFavorite FROM slang WHERE id = :slangId")
    LiveData<Integer> isSlangFavoriteById(int slangId);
}
