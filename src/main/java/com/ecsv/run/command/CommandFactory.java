package com.ecsv.run.command;

import java.util.Arrays;

public class CommandFactory {

    public Command getCommand(final String input) {
        if (input.equalsIgnoreCase("count *")) {
            return new CommandCount();
        } else if (input.toLowerCase().contains("count distinct")) {
            final String[] split = input.split(" ");
            final String property = split.length == 3 ? split[2] : "";
            return new CommandCountDistinct(property);
        } else if (input.toLowerCase().contains("filter")) {
            final String[] split = input.split(" ");
            final String property = split.length >= 3 ? split[1] : "";
            final String value = split.length >= 3 ? String.join(" ", Arrays.copyOfRange(split, 2, split.length)) : "";
            return new FilterCommand(property, value);
        } else {
            throw new IllegalArgumentException("commmand " + input + " is invalid");
        }
    }
}
