package com.zanacode.colombianslang.ui.allSlang;


import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AllSlangViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SpanishSlangRepository repository;

    public AllSlangViewModelFactory(SpanishSlangRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AllSlangFragmentViewModel(repository);
    }
}
