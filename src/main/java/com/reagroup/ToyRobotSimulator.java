package com.reagroup;

import com.reagroup.core.Simulator;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class ToyRobotSimulator {

    public static void main(String[] args) {
        Simulator s = new Simulator();
        try {
            s.execute(args);
            for (String r : s.getReports()) {
                System.out.println(r);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}