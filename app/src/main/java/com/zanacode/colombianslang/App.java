package com.zanacode.colombianslang;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zanacode.colombianslang.data.database.SpanishSlangDatabase;
import com.zanacode.colombianslang.utilities.MoveDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpanishSlangDatabase.getInstance(getApplicationContext())
                .countryDao().getAllCountries().observeForever( (countries) -> {
            if (countries.size() == 0) {
                MoveDatabase.moveDatabaseFiles(getApplicationContext());
            }
        });
        initializeLogger();
    }

    private void initializeLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .tag("Log")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
