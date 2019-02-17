package com.zanacode.colombianslang.data.database;


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
                                                     CountryDao countryDao) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new SpanishSlangRepository(slangDao, countryDao);
            }
        }
        return instance;
    }

    public LiveData<List<SlangEntry>> getAllSlangs() {
        return slangDao.getAllSlangs();
    }

    public LiveData<List<SlangEntry>> getAllSlangByCountry(String countryCode) {
        return slangDao.getAllSlangByCountry(countryCode);
    }

    public LiveData<String[]> getSlangSuggestions() {
        return slangDao.getSlangSuggestions();
    }

    public LiveData<List<CountryEntry>> getAllCountires() {
        return countryDao.getAllCountries();
    }

    public LiveData<List<MeaningsCountryJoin>> getMeaningsCountryJoinById(int id) {
        return slangDao.getMeaningSlangCountryJoinById(id);
    }

    public LiveData<Integer> getSlangIdByTitle(String title) {
        return slangDao.getSlangIdByTitle(title);
    }
}
