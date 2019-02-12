package com.zanacode.colombianslang.ui.slandDetail;


import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SlangDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SpanishSlangRepository repository;

    public SlangDetailViewModelFactory(SpanishSlangRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SlangDetailFragmentViewModel(repository);
    }
}
