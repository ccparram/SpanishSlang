package com.zanacode.colombianslang.ui.slandDetail;

import com.zanacode.colombianslang.data.database.MeaningsCountryJoin;
import com.zanacode.colombianslang.data.database.SpanishSlangRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SlangDetailFragmentViewModel extends ViewModel {

    private LiveData<List<MeaningsCountryJoin>> meaningsCountryJoin;
    SpanishSlangRepository repository;
    int currentSlantId;

    SlangDetailFragmentViewModel(SpanishSlangRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<MeaningsCountryJoin>> getCurrentMeaningsCountryJoin() {
        return meaningsCountryJoin;
    }

    public void setCurrentSlangId(int currentSlantId) {
        this.currentSlantId = currentSlantId;
        meaningsCountryJoin = repository.getMeaningsCountryJoinById(currentSlantId);
    }
}
