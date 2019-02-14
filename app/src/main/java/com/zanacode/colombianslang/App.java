package com.zanacode.colombianslang;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zanacode.colombianslang.data.database.CountryEntry;
import com.zanacode.colombianslang.data.database.SlangDao;
import com.zanacode.colombianslang.data.database.SlangEntry;
import com.zanacode.colombianslang.data.database.SpanishSlangDatabase;

import java.util.ArrayList;
import java.util.List;

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

    private void populateDB() {

        SlangDao slangDao =  SpanishSlangDatabase.getInstance(this).slangDao();

        List<SlangEntry> slangEntries = new ArrayList<>();
        slangEntries.add((new SlangEntry(111, "A balazo")));
        slangEntries.add((new SlangEntry(222, "A buenas horas mangas verdes")));
        slangEntries.add((new SlangEntry(333, "A cachete")));
        slangEntries.add((new SlangEntry(444, "A cascarla")));
        slangEntries.add((new SlangEntry(555, "A chaleco")));
        slangEntries.add((new SlangEntry(666, "A cuadros")));
        slangEntries.add((new SlangEntry(777, "A huevo")));

        slangDao.bulkInsert(slangEntries);

        List<CountryEntry> countryEntries = new ArrayList<>();
        countryEntries.add((new CountryEntry("pa", "Panamá")));
        countryEntries.add((new CountryEntry("es", "España")));
        countryEntries.add((new CountryEntry("cr", "Costa Rica")));
        countryEntries.add((new CountryEntry("co", "Colombia")));
        countryEntries.add((new CountryEntry("mx", "México")));
        countryEntries.add((new CountryEntry("cl", "Chile")));
        countryEntries.add((new CountryEntry("ec", "Ecuador")));
        countryEntries.add((new CountryEntry("hn", "Honduras")));
        countryEntries.add((new CountryEntry("ni", "Nicaragua")));
        countryEntries.add((new CountryEntry("sv", "El Salvador")));
        countryEntries.add((new CountryEntry("uy", "Uruguay")));
        countryEntries.add((new CountryEntry("gt", "Guatemala")));
    };
}
