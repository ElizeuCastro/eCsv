package com.ecsv.run.command;

import com.ecsv.run.domain.City;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandCountDistinct implements Command<Integer> {

    private String property;

    CommandCountDistinct(final String property) {
        this.property = property;
    }

    @Override
    public Integer execute(final List<City> cities) {
        City.Properties.HEADER.checkProperty(property);
        return cities.stream().map(mapByField(property)).collect(Collectors.toSet()).size();
    }

    @Override
    public void print(final Integer count) {
        System.out.println(count);
    }

    private Function<City, Object> mapByField(final String property) {
        return object -> {
            try {
                final Field f = object.getClass().getDeclaredField(property);
                f.setAccessible(true);
                return f.get(object);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        };
    }
}
