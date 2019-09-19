package com.university.app.utils;

import com.university.app.CommandParserView;

public class ConsoleParsingUtil {

    static String[] availableCommandsArray = {"Who is head of department ",
            "Show statistics of ",
            "Show the average salary for department ",
            "Show count of employee for ",
            "Global search by ",
            "quit"};

    public CommandParserView consoleCommandAvailabilityChecker(String input) {
        CommandParserView result = new CommandParserView();
        for (int i = 0; i < availableCommandsArray.length - 1; i++) {
            if (input.contains(availableCommandsArray[i])) {
                String parameter = input.substring(availableCommandsArray[i].length(), input.length());
                result.parameter = parameter;
                result.commandIndex = i;
                return result;
            }
        }
        return null;
    }
}