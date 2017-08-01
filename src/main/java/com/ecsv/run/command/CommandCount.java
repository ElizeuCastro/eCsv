package com.ecsv.run.command;

import com.ecsv.run.domain.City;

import java.util.List;

public class CommandCount implements Command<Integer> {

    @Override
    public Integer execute(final List<City> cities) {
        return cities.size();
    }

    @Override
    public void print(final Integer execute) {
        System.out.println(execute);
    }
}
