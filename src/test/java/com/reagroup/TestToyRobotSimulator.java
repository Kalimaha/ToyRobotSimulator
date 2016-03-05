package com.reagroup;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestToyRobotSimulator extends TestCase {

    public void testMain() {
        ToyRobotSimulator s = new ToyRobotSimulator();
        String[] args = {"PLACE 0,0,NORTH", "MOVE", "REPORT"};
        s.main(args);
    }

}
