package com.reagroup.commands;

import com.reagroup.beans.Robot;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public abstract class Command {

    private String commandString;

    public Command(String commandString) {
        this.setCommandString(commandString.trim().toUpperCase());
    }

    public void execute(Robot robot) throws Exception {

    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

}