package com.zanacode.colombianslang.ui.country;

import com.zanacode.colombianslang.data.database.CountryEntry;
import com.zanacode.colombianslang.data.database.SlangEntry;
import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CountryFragmentViewModel extends ViewModel {

    private LiveData<List<CountryEntry>> countries;
    SpanishSlangRepository repository;

    CountryFragmentViewModel(SpanishSlangRepository repository) {
        this.repository = repository;
        countries = repository.getAllCountires();
    }

    public LiveData<List<CountryEntry>> getSlangEntries() {
        return countries;
    }
}
