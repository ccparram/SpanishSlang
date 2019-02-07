package com.zanacode.colombianslang.utilities;

import android.content.Context;

import com.zanacode.colombianslang.data.database.SpanishSlangDatabase;
import com.zanacode.colombianslang.data.database.SpanishSlangRepository;
import com.zanacode.colombianslang.ui.allSlang.AllSlangViewModelFactory;
import com.zanacode.colombianslang.ui.country.CountryViewModelFactory;

public class Injector {

    private static SpanishSlangRepository provideRepository(Context context) {
        SpanishSlangDatabase database = SpanishSlangDatabase.getInstance(context.getApplicationContext());
        return SpanishSlangRepository.getInstance(database.slangDao(), database.countryDao());
    }

    public static AllSlangViewModelFactory provideAllSlangViewModelFactory(Context context){
        SpanishSlangRepository repository = provideRepository(context);
        return new AllSlangViewModelFactory(repository);
    }

    public static CountryViewModelFactory provideCountryViewModelFactory(Context context){
        SpanishSlangRepository repository = provideRepository(context);
        return new CountryViewModelFactory(repository);
    }
}
