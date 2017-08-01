package com.ecsv.run.command;

import com.ecsv.run.domain.AbstractDomain;
import com.ecsv.run.domain.City;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterCommand implements Command<List<City>> {

    private String property;
    private String value;

    FilterCommand(final String property, final String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public List<City> execute(final List<City> cities) {
        City.Properties.HEADER.checkProperty(property);
        return cities.stream()
                .filter(filterByFieldAndValue(property, value))
                .collect(Collectors.toList());
    }

    @Override
    public void print(final List<City> cities) {
        if (!cities.isEmpty()) {
            System.out.println(City.Properties.HEADER.toString());
            cities.forEach(c -> System.out.println(c.toString()));
        }
    }

    private Predicate<AbstractDomain> filterByFieldAndValue(final String property, final String value) {
        return object -> {
            try {
                final Field f = object.getClass().getDeclaredField(property);
                f.setAccessible(true);
                return f.get(object).toString().equalsIgnoreCase(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        };
    }
}
