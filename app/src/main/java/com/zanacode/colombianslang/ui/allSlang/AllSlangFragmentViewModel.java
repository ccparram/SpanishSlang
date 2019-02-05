package com.zanacode.colombianslang.ui.allSlang;

import com.zanacode.colombianslang.data.database.SlangEntry;
import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AllSlangFragmentViewModel extends ViewModel {

    private LiveData<List<SlangEntry>> slangEntries;
    SpanishSlangRepository repository;

    AllSlangFragmentViewModel(SpanishSlangRepository repository) {
        this.repository = repository;
        slangEntries = repository.getAllSlangs();
    }

    public LiveData<List<SlangEntry>> getSlangEntries() {
        return slangEntries;
    }
}
