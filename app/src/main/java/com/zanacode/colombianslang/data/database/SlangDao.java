package com.zanacode.colombianslang.data.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface SlangDao {

    @Insert
    void insert(SlangEntry slangEntry);

}
