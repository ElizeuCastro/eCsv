package com.ecsv.run.command;

import com.ecsv.run.domain.City;

import java.util.List;

public interface Command<T> {

    T execute(List<City> cities);

    void print(T execute);
}
