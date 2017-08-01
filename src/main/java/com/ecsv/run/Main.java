package com.ecsv.run;

import com.ecsv.run.command.Command;
import com.ecsv.run.command.CommandFactory;
import com.ecsv.run.domain.City;
import com.ecsv.run.parser.CsvParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Main {

    public static void main(String[] args) throws IOException {
        final List<City> cities = CsvParser.getInstance().cities(new Main().getFile());
        String option = "";
        System.out.println("operações");
        System.out.println("count *");
        System.out.println("count distinct [propriedade] -> ex: count distinct uf");
        System.out.println("filter [propriedade] [valor] -> ex: filter uf RO");
        final CommandFactory commandFactory = new CommandFactory();
        while (!option.equalsIgnoreCase("exit")) {
            option = new Scanner(System.in).nextLine();
            if (option.equalsIgnoreCase("exit")) continue;
            final Command command = commandFactory.getCommand(option);
            command.print(command.execute(cities));
        }
    }

    private InputStream getFile() {
        final InputStream resource = getClass().getClassLoader().getResourceAsStream("cidades.csv");
        if (isNull(resource)) {
            throw new IllegalArgumentException("file not found.");
        }
        return resource;
    }
}
