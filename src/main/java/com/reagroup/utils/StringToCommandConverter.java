package com.reagroup.utils;

import com.reagroup.commands.*;
import com.reagroup.constants.COMMANDS;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class StringToCommandConverter {

    public static Command convert(String commandString) throws Exception {
        if (commandString.trim().toUpperCase().startsWith(COMMANDS.PLACE.name())) {
            return new Place(commandString);
        } else if (commandString.trim().toUpperCase().startsWith(COMMANDS.MOVE.name())) {
            return new Move(commandString);
        } else if (commandString.trim().toUpperCase().startsWith(COMMANDS.LEFT.name())) {
            return new Left(commandString);
        } else if (commandString.trim().toUpperCase().startsWith(COMMANDS.RIGHT.name())) {
            return new Right(commandString);
        } else if (commandString.trim().toUpperCase().startsWith(COMMANDS.REPORT.name())) {
            return new Report(commandString);
        } else {
            throw new Exception("Please provide a valid command");
        }
    }

}