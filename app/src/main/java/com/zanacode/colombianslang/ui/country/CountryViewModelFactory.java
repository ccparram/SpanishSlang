package com.zanacode.colombianslang.ui.country;


import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CountryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SpanishSlangRepository repository;

    public CountryViewModelFactory(SpanishSlangRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new CountryFragmentViewModel(repository);
    }
}
