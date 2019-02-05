package com.zanacode.colombianslang.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SlangDao {

    @Insert
    void insert(SlangEntry slangEntry);

    @Insert
    void bulkInsert(List<SlangEntry> slangEntries);

    @Query("SELECT * FROM slang")
    LiveData<List<SlangEntry>> getAllSlangs();
}
