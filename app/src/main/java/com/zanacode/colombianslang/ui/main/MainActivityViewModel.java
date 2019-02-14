package com.zanacode.colombianslang.ui.main;

import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String[]> slangSuggestionNames;
    SpanishSlangRepository repository;

    MainActivityViewModel(SpanishSlangRepository repository) {
        this.repository = repository;
        this.slangSuggestionNames = new MutableLiveData<>();

    }

    public LiveData<String[]> getSlangSuggestions() {
        return repository.getSlangSuggestions();
    }

    public LiveData<Integer> getSlangIdByTitle(String title) {
        return repository.getSlangIdByTitle(title);
    }
}
