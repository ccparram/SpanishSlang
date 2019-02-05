package com.zanacode.colombianslang.utilities;

import android.content.Context;

import com.zanacode.colombianslang.AppExecutors;
import com.zanacode.colombianslang.data.database.SlangDao;
import com.zanacode.colombianslang.data.database.SpanishSlangDatabase;
import com.zanacode.colombianslang.data.database.SpanishSlangRepository;
import com.zanacode.colombianslang.ui.allSlang.AllSlangViewModelFactory;


public class Injector {

    private static SpanishSlangRepository provideRepository(Context context) {
        SpanishSlangDatabase database = SpanishSlangDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return SpanishSlangRepository.getInstance(database.slangDao(), executors);
    }

    public static AllSlangViewModelFactory provideAllSlangViewModelFactory(Context context){
        SpanishSlangRepository repository = provideRepository(context);
        return new AllSlangViewModelFactory(repository);
    }


}
