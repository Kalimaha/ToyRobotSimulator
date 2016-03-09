package com.reagroup.core;

import com.reagroup.beans.Position;
import com.reagroup.beans.Robot;
import com.reagroup.beans.Tabletop;
import com.reagroup.commands.Command;
import com.reagroup.constants.COMMANDS;
import com.reagroup.utils.StringToCommandConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Simulator {

    private Tabletop tabletop;

    private Robot robot;

    public Simulator() {
        this.setTabletop(new Tabletop());
        this.setRobot(new Robot());
    }

    /**
     * This is the main method of the simulator.
     * @param commands List of commands to be executed by the simulator.
     */
    public void simulate(String[] commands) throws Exception {
        Command c;
        commands = this.cleanCommandsList(commands);
        for (String commandString : commands) {
            System.out.println("Execute: " + commandString);
            c = StringToCommandConverter.convert(commandString);
            c.execute(this.getRobot());
            if (!this.isValidPosition(this.getRobot().getPosition())) {
                throw new Exception("The robot is OUT of the table! The simulation ends.");
            }
        }
        System.out.println();
    }

    /**
     * The toy robot must not fall off the table during movement. This also includes the initial
     * placement of the toy robot.
     * @param p The position of the robot.
     * @return A flag that states whether the position is within the table or not.
     */
    public boolean isValidPosition(Position p) {
        if (p.getX() >= this.getTabletop().getWidth()) {
            return false;
        } else if (p.getX() < 0) {
            return false;
        } else if (p.getY() >= this.getTabletop().getHeight()) {
            return false;
        } else if (p.getY() < 0) {
            return false;
        }
        return true;
    }

    /**
     * The first valid command to the robot is a PLACE command. The application should discard all commands in the
     * sequence until a valid PLACE command has been executed.
     * @param commands List of commands.
     * @return A sub-list of commands starting with the PLACE command.
     * @throws Exception The commands list must contain at least one PLACE command.
     */
    public String[] cleanCommandsList(String[] commands) throws Exception {
        int startIdx = -1;
        for (int i = 0; i < commands.length; i += 1) {
            if (commands[i].trim().toUpperCase().startsWith(COMMANDS.PLACE.name())) {
                startIdx = i;
                break;
            }
        }
        if (startIdx == -1) {
            throw new Exception("The commands list must contain at least one PLACE command.");
        }
        List<String> buffer = new ArrayList<>();
        for (int i = startIdx; i < commands.length; i += 1) {
            buffer.add(commands[i]);
        }
        String[] out = new String[buffer.size()];
        out = buffer.toArray(out);
        return out;
    }

    public Tabletop getTabletop() {
        return tabletop;
    }

    public void setTabletop(Tabletop tabletop) {
        this.tabletop = tabletop;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

}