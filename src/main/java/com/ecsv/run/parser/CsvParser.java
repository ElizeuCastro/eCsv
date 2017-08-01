package com.ecsv.run.parser;

import com.ecsv.run.domain.City;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CsvParser {


    private static CsvParser instance;

    public static CsvParser getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CsvParser();
        }
        return instance;
    }

    public List<City> cities(final InputStream file) {
        final List<City> cities = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                final String[] fields = line.split(",");
                cities.add(new City(Long.valueOf(fields[0]),
                        fields[1],
                        fields[2],
                        Boolean.valueOf(fields[3]),
                        Double.valueOf(fields[4]),
                        Double.valueOf(fields[5]),
                        fields[6],
                        fields[7],
                        fields[8],
                        fields[9]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
