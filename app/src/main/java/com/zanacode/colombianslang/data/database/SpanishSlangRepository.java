package com.zanacode.colombianslang.data.database;


import com.zanacode.colombianslang.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SpanishSlangRepository {

    private static SpanishSlangRepository instance;
    private static final Object LOCK = new Object();
    private final SlangDao slangDao;
    private final CountryDao countryDao;

    private SpanishSlangRepository(SlangDao slangDao, CountryDao countryDao) {
        this.slangDao = slangDao;
        this.countryDao = countryDao;
    }

    public static SpanishSlangRepository getInstance(SlangDao slangDao,
                                                     CountryDao countryDao){
        if (instance == null){
            synchronized (LOCK) {
                instance = new SpanishSlangRepository(slangDao, countryDao);
            }
        }
        return instance;
    };

    public LiveData<List<SlangEntry>> getAllSlangs() {
        return slangDao.getAllSlangs();
    }

    public LiveData<List<CountryEntry>> getAllCountires() {
        return countryDao.getAllCountries();
    }

}
