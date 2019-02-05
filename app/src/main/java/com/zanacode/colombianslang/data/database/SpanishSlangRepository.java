package com.zanacode.colombianslang.data.database;


import com.zanacode.colombianslang.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SpanishSlangRepository {

    private static SpanishSlangRepository instance;
    private static final Object LOCK = new Object();
    private final SlangDao slangDao;
    private final AppExecutors executors;

    private SpanishSlangRepository(SlangDao slangDao, AppExecutors executors) {
        this.slangDao = slangDao;
        this.executors = executors;
    }

    public static SpanishSlangRepository getInstance(SlangDao slangDao, AppExecutors executors){
        if (instance == null){
            synchronized (LOCK) {
                instance = new SpanishSlangRepository(slangDao, executors);
            }
        }
        return instance;
    };

    public LiveData<List<SlangEntry>> getAllSlangs() {
        return slangDao.getAllSlangs();
    }

}
