package com.ecsv.run;

import com.ecsv.run.domain.AbstractDomain;
import com.ecsv.run.domain.City;
import com.ecsv.run.parser.CsvParser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static Main main = new Main();

    public static void main(String[] args) {
        final List<City> cities = CsvParser.getInstance().cities(new File("/home/esquiter/Desktop/desafio java/eCsv/src/main/resources/cidades.csv"));
        String option = "";
        System.out.println("operações");
        System.out.println("count *");
        System.out.println("count distinct [propriedade]");
        System.out.println("filter [propriedade] [valor]");
        while (!option.equalsIgnoreCase("exit")) {
            option = new Scanner(System.in).nextLine();
            if (option.equalsIgnoreCase("count *")) {
                System.out.println(cities.size());
            } else if (option.toLowerCase().contains("count distinct")) {
                String[] split = option.split(" ");
                if (split.length == 3) {
                    String property = split[2];
                    City.Properties.HEADER.checkProperty(property);
                    int count = cities.stream().map(mapByField(property)).collect(Collectors.toSet()).size();
                    System.out.println(count);
                }
            } else if (option.toLowerCase().contains("filter")) {
                String[] split = option.split(" ");
                if (split.length == 3) {
                    String property = split[1];
                    String value = split[2];
                    City.Properties.HEADER.checkProperty(property);
                    final List<Object> result = cities.stream().filter(filterByFieldAndValue(property, value)).collect(Collectors.toList());
                    System.out.println(City.Properties.HEADER.toString());
                    result.forEach(c -> System.out.println(c.toString()));
                }
            }
        }
    }

    private static Predicate<AbstractDomain> filterByFieldAndValue(final String property, final String value) {
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

    private static Function<AbstractDomain, Object> mapByField(final String property) {
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
