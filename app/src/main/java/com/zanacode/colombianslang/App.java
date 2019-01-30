package com.zanacode.colombianslang;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

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
