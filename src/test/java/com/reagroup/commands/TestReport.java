package com.reagroup.commands;

import com.reagroup.beans.Robot;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestReport extends TestCase {

    private Command c;

    private Robot r;

    public void setUp() {
        r = new Robot();
        c = new Report("report");
    }

    public void testMove() {
        try {
            c.execute(r);
        } catch (Exception e) {
            fail();
        }
    }

}